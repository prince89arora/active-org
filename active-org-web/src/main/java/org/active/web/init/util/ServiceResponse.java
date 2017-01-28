package org.active.web.init.util;

import com.eclipsesource.json.JsonObject;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;

import static org.active.web.init.util.ServiceConstant.RESPONSE_ERROR_KEY;
import static org.active.web.init.util.ServiceConstant.RESPONSE_STATUS_KEY;

/**
 *
 */
public class ServiceResponse {

    private static final Logger log = Logger.getLogger(ServiceResponse.class);

    public static Response ok() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(RESPONSE_STATUS_KEY, true);
        return Response.ok().entity(jsonObject.toString()).build();
    }

    public static Response error() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(RESPONSE_STATUS_KEY, false);
        return Response.ok().entity(jsonObject.toString()).build();
    }

    public static Response error(String error) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(RESPONSE_STATUS_KEY, true);
        jsonObject.add(RESPONSE_ERROR_KEY, error);
        return Response.ok().entity(jsonObject.toString()).build();
    }
}
