package org.active.web.init.util;

import com.eclipsesource.json.JsonObject;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;

import static org.active.web.init.util.ServiceConstant.RESPONSE_STATUS;

/**
 *
 */
public class ServiceResponse {

    private static final Logger log = Logger.getLogger(ServiceResponse.class);

    public static Response ok() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(RESPONSE_STATUS, true);
        return Response.ok().entity(jsonObject.toString()).build();
    }
}
