package it.wallet.demo.issuer;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class IssuerMetadataResourceTest {

    @Test
    void testIssuerMetadataIsExposed() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/.well-known/openid-credential-issuer")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("credential_issuer", equalTo("https://walletdemo.fugerit.org/issuer-service"))
                .body("credential_endpoint", equalTo("https://walletdemo.fugerit.org/issuer-service/credential"))
                .body("jwks_uri", equalTo("https://walletdemo.fugerit.org/issuer-service/.well-known/jwks.json"))
                .body("credentials_supported", notNullValue())
                .body("credentials_supported[0].format", equalTo("sd-jwt"))
                .body("credentials_supported[0].types", hasItem("VerifiableCredential"));
    }
}
