package org.active.web.init.resources;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.active.web.init.commons.ApplicationContextFactory;
import org.active.web.init.security.ApplicationUser;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Principal;
import java.util.UUID;

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
        boolean status = false;
        String loginToken = "";
        if (request.getParameter("username") != null &&
                request.getParameter("password") != null) {
            if ((request.getParameter("username").equals("admin") &&
                    request.getParameter("password").equals("admin"))) {
                status = true;
                loginToken = UUID.randomUUID().toString();
                ApplicationContextFactory.INIT.addLoginUser(new ApplicationUser(request.getParameter("username"),
                        loginToken));
            }
        }
        JsonObject json = Json.object();
        json.add("status", status);
        json.add("token", loginToken);
        return Response.ok().entity(json.toString()).build();
    }

    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@Context HttpServletRequest request) {
        JsonObject json = Json.object();
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            json.add("username", principal.getName());
        }
        return Response.ok().entity(json.toString()).build();
    }

    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logOut(@Context HttpServletRequest request) {
        ApplicationContextFactory.INIT.logoutUser(ApplicationContextFactory.INIT.getSecurityContext()
                .getLoginToken(request));
        return Response.ok().entity("{status: true}").build();
    }
}
