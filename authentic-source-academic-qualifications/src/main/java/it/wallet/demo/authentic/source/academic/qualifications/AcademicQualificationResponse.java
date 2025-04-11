package it.wallet.demo.authentic.source.academic.qualifications;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AcademicQualificationResponse {

    @JsonProperty("personal_data")
    private PersonalData personalData;

    private List<Qualification> qualifications;

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    public static class PersonalData {

        @JsonProperty("tax_code")
        private String taxCode;

        public String getTaxCode() {
            return taxCode;
        }

        public void setTaxCode(String taxCode) {
            this.taxCode = taxCode;
        }
    }

    public static class Qualification {

        @JsonProperty("institute_code")
        private String instituteCode;

        @JsonProperty("qualification_name")
        private String qualificationName;

        @JsonProperty("programme_type_code")
        private String programmeTypeCode;

        @JsonProperty("degree_course_code")
        private String degreeCourseCode;

        @JsonProperty("degree_class_code")
        private String degreeClassCode;

        @JsonProperty("academic_qualification_date")
        private String academicQualificationDate;

        @JsonProperty("qualification_grade_value")
        private String qualificationGradeValue;

        @JsonProperty("qualification_grading_scale_minimum_grade")
        private int qualificationGradingScaleMinimumGrade;

        @JsonProperty("qualification_grading_scale_maximum_grade")
        private int qualificationGradingScaleMaximumGrade;

        // Getters and Setters

        public String getInstituteCode() {
            return instituteCode;
        }

        public void setInstituteCode(String instituteCode) {
            this.instituteCode = instituteCode;
        }

        public String getQualificationName() {
            return qualificationName;
        }

        public void setQualificationName(String qualificationName) {
            this.qualificationName = qualificationName;
        }

        public String getProgrammeTypeCode() {
            return programmeTypeCode;
        }

        public void setProgrammeTypeCode(String programmeTypeCode) {
            this.programmeTypeCode = programmeTypeCode;
        }

        public String getDegreeCourseCode() {
            return degreeCourseCode;
        }

        public void setDegreeCourseCode(String degreeCourseCode) {
            this.degreeCourseCode = degreeCourseCode;
        }

        public String getDegreeClassCode() {
            return degreeClassCode;
        }

        public void setDegreeClassCode(String degreeClassCode) {
            this.degreeClassCode = degreeClassCode;
        }

        public String getAcademicQualificationDate() {
            return academicQualificationDate;
        }

        public void setAcademicQualificationDate(String academicQualificationDate) {
            this.academicQualificationDate = academicQualificationDate;
        }

        public String getQualificationGradeValue() {
            return qualificationGradeValue;
        }

        public void setQualificationGradeValue(String qualificationGradeValue) {
            this.qualificationGradeValue = qualificationGradeValue;
        }

        public int getQualificationGradingScaleMinimumGrade() {
            return qualificationGradingScaleMinimumGrade;
        }

        public void setQualificationGradingScaleMinimumGrade(int minGrade) {
            this.qualificationGradingScaleMinimumGrade = minGrade;
        }

        public int getQualificationGradingScaleMaximumGrade() {
            return qualificationGradingScaleMaximumGrade;
        }

        public void setQualificationGradingScaleMaximumGrade(int maxGrade) {
            this.qualificationGradingScaleMaximumGrade = maxGrade;
        }
    }
}
