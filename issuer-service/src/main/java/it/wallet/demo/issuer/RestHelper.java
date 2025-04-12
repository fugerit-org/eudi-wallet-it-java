package it.wallet.demo.issuer;

import jakarta.ws.rs.core.Response;
import org.fugerit.java.core.function.UnsafeSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestHelper {

    private RestHelper() {}

    private static final Logger log = LoggerFactory.getLogger(RestHelper.class);

    public static Response handle(UnsafeSupplier<Response, Exception> fun, String logMessage) {
        try {
            return fun.get();
        } catch (Exception e) {
            String errorMessage = logMessage+" : "+e;
            log.error( errorMessage , e );
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
