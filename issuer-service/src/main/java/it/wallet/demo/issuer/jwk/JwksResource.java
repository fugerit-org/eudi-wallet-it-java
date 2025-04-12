package it.wallet.demo.issuer.jwk;

import com.nimbusds.jose.jwk.JWKSet;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Map;

@Path("/.well-known/jwks.json")
public class JwksResource {

    @Inject
    JwkProvider jwkProvider;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getJwks() {
        return this.jwkProvider.getPublicJwkSet().toJSONObject();
    }

}
