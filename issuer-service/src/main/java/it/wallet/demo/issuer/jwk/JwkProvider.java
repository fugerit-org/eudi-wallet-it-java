package it.wallet.demo.issuer.jwk;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.*;
import com.nimbusds.jose.jwk.gen.ECKeyGenerator;
import jakarta.enterprise.context.ApplicationScoped;
import org.fugerit.java.core.function.SafeFunction;

/**
 * For demonstration, we'll use ES256 (ECDSA with P-256 curve), which is explicitly supported by:
 *
 * SD-JWT (IETF draft)
 * Authlete sd-jwt
 * JWK standards (RFC 7517)
 *
 */
@ApplicationScoped
public class JwkProvider {

    private static final ECKey ecKey;

    static {
        ecKey = SafeFunction.get( () ->  new ECKeyGenerator(Curve.P_256)
                .keyID("it-wallet-demo-issuer-key-1") // used in 'kid'
                .algorithm(JWSAlgorithm.ES256)
                .generate() );
    }

    public ECKey getEcKey() {
        return ecKey;
    }

    public JWKSet getPublicJwkSet() {
        return new JWKSet(ecKey.toPublicJWK());
    }
}
