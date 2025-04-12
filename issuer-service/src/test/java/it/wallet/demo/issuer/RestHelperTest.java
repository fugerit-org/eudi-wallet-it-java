package it.wallet.demo.issuer;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;
import org.fugerit.java.core.cfg.ConfigRuntimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
class RestHelperTest {

    @Test
    void test500() {
        Response response500 = RestHelper.handle( () -> {
            if ( Boolean.TRUE ) {
                throw new ConfigRuntimeException( "Scenario exception" );
            } else {
                return Response.ok().build();
            }
        }, "test scenario for 500" );
        Assertions.assertEquals( Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response500.getStatus() );
    }

}
