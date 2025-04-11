package it.wallet.demo.authentic.source.academic.qualifications;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AcademicQualificationRequest {

    @JsonProperty("tax_code")
    private String taxCode;

    @JsonProperty("person_id")
    private String personId;

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "AcademicQualificationRequest{" +
                "personId='" + personId + '\'' +
                ", taxCode='" + taxCode + '\'' +
                '}';
    }

}
