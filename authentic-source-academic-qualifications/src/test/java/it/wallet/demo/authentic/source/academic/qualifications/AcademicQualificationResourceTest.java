package it.wallet.demo.authentic.source.academic.qualifications;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class AcademicQualificationResourceTest {

    @Test
    public void testAcademicQualificationSuccess() {
        String requestBody = """
            {
                "tax_code": "RSSMRA80A01F205D",
                "person_id": "12345678A"
            }
            """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/academic-qualifications")
                .then()
                .statusCode(200)
                .body("personal_data.tax_code", equalTo("RSSMRA80A01F205D"))
                .body("qualifications", hasSize(1))
                .body("qualifications[0].qualification_name", containsString("ingegneria"));
    }

    @Test
    public void testMissingParameters() {
        String requestBody = """
            {
                "person_id": "12345678A"
            }
            """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/academic-qualifications")
                .then()
                .statusCode(400)
                .body("error", notNullValue());
    }
}
