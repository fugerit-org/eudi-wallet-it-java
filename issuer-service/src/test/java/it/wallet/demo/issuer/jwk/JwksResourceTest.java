package it.wallet.demo.issuer.jwk;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class JwksResourceTest {

    @Test
    void testJwksEndpointExposesPublicKey() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/.well-known/jwks.json")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("keys", notNullValue())
                .body("keys.size()", is(1))
                .body("keys[0].kty", equalTo("EC"))
                .body("keys[0].crv", equalTo("P-256"))
                .body("keys[0].kid", equalTo("it-wallet-demo-issuer-key-1"));
    }
}
