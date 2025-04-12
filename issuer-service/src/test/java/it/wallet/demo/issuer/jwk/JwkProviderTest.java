package it.wallet.demo.issuer.jwk;

import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.JWKSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwkProviderTest {

    @Test
    void testEcKeyIsGenerated() {
        ECKey ecKey = new JwkProvider().getEcKey();
        assertNotNull(ecKey, "EC key should not be null");
        assertEquals("P-256", ecKey.getCurve().getName());
        assertEquals("EC", ecKey.getKeyType().getValue());
        assertNotNull(ecKey.getKeyID());
    }

    @Test
    void testPublicJwkSetContainsOneKey() {
        JWKSet jwkSet = new JwkProvider().getPublicJwkSet();
        assertNotNull(jwkSet);
        assertEquals(1, jwkSet.getKeys().size(), "JWK set should contain exactly one public key");
    }
}
