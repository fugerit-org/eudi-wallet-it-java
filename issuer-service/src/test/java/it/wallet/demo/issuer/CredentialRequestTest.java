package it.wallet.demo.issuer;

import io.quarkus.test.junit.QuarkusTest;
import it.wallet.demo.issuer.credential.CredentialRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
class CredentialRequestTest {

    @Test
    void beanTest() {
        CredentialRequest bean = new CredentialRequest();
        bean.setCredentialType( "vc" );
        bean.setSubjectSyntax( "test" );
        bean.setSubjectSyntaxType( "test-type" );
        Assertions.assertEquals( "test-type", bean.getSubjectSyntaxType() );
        Assertions.assertEquals( "test", bean.getSubjectSyntax() );
        Assertions.assertEquals( "vc", bean.getCredentialType() );
    }

}
