package org.active.web.init.resources;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.active.model.entity.User;
import org.active.model.util.CommonUtils;
import org.active.security.SecurityContext;
import org.active.services.core.ServiceContext;
import org.active.services.ref.impl.UserService;
import org.active.web.init.commons.ApplicationContextFactory;
import org.active.web.init.util.ServiceResponse;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Principal;
import java.util.UUID;

import static org.active.security.util.SecurityCommons.LOGIN_HEADER;


/**
 *
 */
@Path("/auth")
public class AuthorizableResources {

    private static final Logger log = Logger.getLogger(AuthorizableResources.class);

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(@Context HttpServletRequest request, String requestBody) {
        boolean status = false;
        String loginToken = "";

        JsonObject jsonObject = Json.parse(requestBody).asObject();

        if (jsonObject.get("username") != null &&
                jsonObject.get("password") != null) {
            UserService service = ServiceContext.getContext().getService(UserService.class);
            User user = service.getUserByUsername(jsonObject.getString("username", ""));

            if (CommonUtils.getHash(jsonObject.getString("password", "")).equals(user.getPassword())) {
                SecurityContext securityContext = ApplicationContextFactory.INIT.getSecurityContext();
                loginToken = securityContext.login(user.getUsername(), request.getRemoteAddr());
                status = true;
            }
        }
        JsonObject json = Json.object();
        json.add("status", status);
        json.add("token", loginToken);
        return Response.ok().entity(json.toString()).build();
    }

    @GET
    @Path("/status")
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
        try {
            ApplicationContextFactory.INIT.getSecurityContext().logout(request.getHeader(LOGIN_HEADER));
            return ServiceResponse.ok();
        } catch (Exception ex) {
            return ServiceResponse.error();
        }
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
