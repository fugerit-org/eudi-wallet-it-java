package it.wallet.demo.issuer.credential;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
class CredentialResourceTest {

    private static final Logger log = LoggerFactory.getLogger(CredentialResourceTest.class);

    @Test
    void testIssueCredentialSuccess() throws ParseException {
        String jwt = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("""
                {
                  "format": "sd-jwt",
                  "credential_type": "UniversityDegreeCredential",
                  "subject_syntax_type": "did",
                  "subject_syntax": "did:example:123"
                }
                """)
                .when()
                .post("/credential")
                .then()
                .statusCode(200).extract().body().asString();
        log.info( "jwt result : {}", jwt );
        JWT parsedJwt = JWTParser.parse( jwt );
        Map<String, Object> jsonObject = parsedJwt.getJWTClaimsSet().toJSONObject();
        log.info( "jsonObject result : {}", jsonObject );
        Assertions.assertTrue( jsonObject.containsKey( "_sd" ) );
    }

    @Test
    void testUnsupportedFormat() {
        given()
                .contentType(ContentType.JSON)
                .body("""
                {
                  "format": "jwt-vc",
                  "credential_type": "UniversityDegreeCredential"
                }
                """)
                .when()
                .post("/credential")
                .then()
                .statusCode(400);
    }
}
