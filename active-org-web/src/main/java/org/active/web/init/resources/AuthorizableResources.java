package org.active.web.init.resources;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.active.model.entity.User;
import org.active.model.util.CommonUtils;
import org.active.services.core.ServiceContext;
import org.active.services.ref.impl.UserService;
import org.active.web.init.commons.ApplicationContextFactory;
import org.active.web.init.util.ServiceResponse;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        return Response.ok().entity("{status: true}").build();
    }

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Context HttpServletRequest request) {
        boolean status = false;
        String loginToken = "";
        if (request.getParameter("username") != null &&
                request.getParameter("password") != null) {
            UserService service = ServiceContext.getContext().getService(UserService.class);
            User user = service.getUserByUsername(request.getParameter("username"));

            if (CommonUtils.getHash(request.getParameter("password")).equals(user.getPassword())) {
                status = true;
                loginToken = UUID.randomUUID().toString();
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
        return ServiceResponse.ok();
    }

    @GET
    @Path("addUser")
    @Produces(MediaType.TEXT_HTML)
    public Response addAdminUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setFirst_name("Admin");
        user.setLast_name("Administrator");
        user.setEmail("admin@mail.com");

        UserService service = ServiceContext.getContext().getService(UserService.class);
        service.save(user);

        return Response.ok().build();
    }

}
