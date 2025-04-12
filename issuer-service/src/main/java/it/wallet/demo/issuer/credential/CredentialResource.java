package it.wallet.demo.issuer.credential;

import com.authlete.sd.SDObjectBuilder;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import it.wallet.demo.issuer.jwk.JwkProvider;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.interfaces.ECPrivateKey;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Path("/credential")
@Consumes(MediaType.APPLICATION_JSON)
@Produces("application/jwt")
public class CredentialResource {

    private static final String SALT = "_56bc4LM-ac6q2DI6cBW5es";

    private static final Logger log = LoggerFactory.getLogger( CredentialResource.class );

    public CredentialResource(JwkProvider jwkProvider) {
        this.jwkProvider = jwkProvider;
    }

    private JwkProvider jwkProvider;

    @POST
    public Response issueCredential(CredentialRequest request) {
        if (!"sd-jwt".equalsIgnoreCase(request.getFormat())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        try {
            String alg = "sha-256";
            // SD-JWT disclosure claims
            Map<String, String> claimsPersonData = Map.of(
                    "given_name", "Maria",
                    "family_name", "Rossi",
                    "date_of_birth", "1990-05-10"
            );
            Map<String, String> degreeData = Map.of(
                    "degree", "Laurea in ingegneria informatica",
                    "degree_level", "Laurea Triennale"
            );
            // Create an SDObjectBuilder instance.
            SDObjectBuilder builder = new SDObjectBuilder(alg);
            builder.putSDClaim( SALT, "person_data", claimsPersonData );
            builder.putSDClaim( SALT, "degree_data", List.of( degreeData ) );
            // SD-JWT traditional claims
            builder.putClaim("iss", "https://walletdemo.fugerit.org/issuer-service");
            builder.putClaim("sub", "U29nZWkgUERORA==");
            builder.putClaim("issuing_authority", "Fugerit Organization");
            builder.putClaim("issuing_country", "IT");
            builder.putClaim("status", Map.of( "status_assertion", Map.of( "credential_hash_alg", alg ) ) );
            ZonedDateTime futureTime = ZonedDateTime.now(ZoneOffset.UTC).plusHours(2);
            builder.putClaim( "exp", futureTime.toEpochSecond() );
            // Create a Map instance.
            Map<String, Object> claims = builder.build();
            log.info( "sd-jwt claims : {}", claims );

            // sign
            JWSHeader header =
                    new JWSHeader.Builder(JWSAlgorithm.ES256)
                            .type(new JOSEObjectType("vc+sd-jwt")).build();
            JWTClaimsSet claimsSet = JWTClaimsSet.parse(claims);
            // Create a credential JWT. (not signed yet)
            SignedJWT jwt = new SignedJWT(header, claimsSet);
            // Use the ECKey from our JwkProvider
            ECKey ecKey = this.jwkProvider.getEcKey();
            ECPrivateKey privateKey = ecKey.toECPrivateKey();
            // Create a signer that signs the credential JWT with the private key.
            JWSSigner signer = new ECDSASigner(privateKey);
            // Let the signer sign the credential JWT.
            jwt.sign(signer);
            String result = jwt.serialize();
            log.info( "result : {}", result );
            return Response.ok( result ).build();

        } catch (Exception e) {
            return Response.serverError().entity(Map.of(
                    "error", "Failed to issue credential",
                    "details", e.getMessage()
            )).build();
        }
    }
}
