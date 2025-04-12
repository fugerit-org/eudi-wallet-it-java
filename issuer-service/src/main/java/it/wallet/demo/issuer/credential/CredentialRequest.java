package it.wallet.demo.issuer.credential;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CredentialRequest {

    @JsonProperty("format")
    private String format;

    @JsonProperty("credential_type")
    private String credentialType;

    @JsonProperty("subject_syntax_type")
    private String subjectSyntaxType;

    @JsonProperty("subject_syntax")
    private String subjectSyntax;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCredentialType() {
        return credentialType;
    }

    public void setCredentialType(String credentialType) {
        this.credentialType = credentialType;
    }

    public String getSubjectSyntaxType() {
        return subjectSyntaxType;
    }

    public void setSubjectSyntaxType(String subjectSyntaxType) {
        this.subjectSyntaxType = subjectSyntaxType;
    }

    public String getSubjectSyntax() {
        return subjectSyntax;
    }

    public void setSubjectSyntax(String subjectSyntax) {
        this.subjectSyntax = subjectSyntax;
    }
}
