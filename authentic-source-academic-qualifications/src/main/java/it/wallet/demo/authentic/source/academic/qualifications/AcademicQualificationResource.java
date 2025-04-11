package it.wallet.demo.authentic.source.academic.qualifications;

import io.quarkus.runtime.util.StringUtil;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/academic-qualifications")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AcademicQualificationResource {

    @POST
    public Response getQualifications(AcademicQualificationRequest request) {
        if (request == null || StringUtil.isNullOrEmpty( request.getTaxCode() ) ) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Missing required field: tax_code\"}")
                    .build();
        }

        AcademicQualificationResponse response = new AcademicQualificationResponse();

        AcademicQualificationResponse.PersonalData personalData = new AcademicQualificationResponse.PersonalData();
        personalData.setTaxCode(request.getTaxCode());
        response.setPersonalData(personalData);

        AcademicQualificationResponse.Qualification qualification = new AcademicQualificationResponse.Qualification();
        qualification.setInstituteCode("16");
        qualification.setQualificationName("Laurea in ingegneria informatica");
        qualification.setProgrammeTypeCode("MS");
        qualification.setDegreeCourseCode("1573960");
        qualification.setDegreeClassCode("MSLM32");
        qualification.setAcademicQualificationDate("2022-07-01");
        qualification.setQualificationGradeValue("110");
        qualification.setQualificationGradingScaleMinimumGrade(66);
        qualification.setQualificationGradingScaleMaximumGrade(110);

        response.setQualifications(List.of(qualification));

        return Response.ok(response).build();
    }
}
