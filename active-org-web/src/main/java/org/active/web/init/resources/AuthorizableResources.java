package org.active.web.init.resources;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 */
@Path("/auth")
public class AuthorizableResources {

    private static final Logger log = Logger.getLogger(AuthorizableResources.class);

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Context HttpServletRequest request) {
        log.debug("Login request for: "+ request.getParameter("username"));
        JsonObject json = Json.object();
        json.add("status", true);
        return Response.ok().entity(json.toString()).build();
    }
}
