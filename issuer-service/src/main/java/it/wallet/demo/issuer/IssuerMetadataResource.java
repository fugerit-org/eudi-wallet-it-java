package it.wallet.demo.issuer;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Map;

@Path("/.well-known/openid-credential-issuer")
public class IssuerMetadataResource {

    private static final String BASE_URL = "https://walletdemo.fugerit.org/issuer-service";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getMetadata() {
        return Map.of(
                "credential_issuer", BASE_URL,
                "credential_endpoint", BASE_URL + "/credential",
                "jwks_uri", BASE_URL + "/.well-known/jwks.json",
                "credentials_supported", List.of(
                        Map.of(
                                "format", "sd-jwt",
                                "types", List.of("VerifiableCredential", "UniversityDegreeCredential"),
                                "cryptographic_binding_methods_supported", List.of("did"),
                                "cryptographic_suites_supported", List.of("ES256"),
                                "display", List.of(
                                        Map.of(
                                                "name", "University Degree Credential",
                                                "locale", "en-US"
                                        )
                                )
                        )
                )
        );
    }
}
