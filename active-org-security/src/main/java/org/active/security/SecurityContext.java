package org.active.security;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;
import org.active.security.util.SecurityUtil;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.Json;

import static org.active.security.util.SecurityCommons.LOGIN_HEADER;

/**
 * @author princearora
 */
public class SecurityContext {

    private SecurityInitializer initializer;
    private UserStateCache provider;

    public SecurityContext(SecurityInitializer initializer) {
        this.initializer = initializer;
        this.provider = UserStateCache.getProvider();
    }

    public SecurityInitializer getInitializer() {
        return initializer;
    }

    public boolean isSecure(String path) {
        return this.findMapping(path).isAuthorized();
    }

    public SecurityMapping findMapping(String path) {
        if (path == null || path.equals("/")) {
            return this.initializer.getRootMapping();
        } else {
            for (SecurityMapping securityMapping : this.initializer.getSecurityMappings()) {
                Pattern pattern = Pattern.compile(securityMapping.getPath());
                if (pattern.matcher(path).lookingAt()) {
                    return securityMapping;
                }
            }
        }
        return null;
    }

    public boolean validateRequest(HttpServletRequest request) {
        if (this.isSecure(request.getRequestURI())) {
            if (request.getHeader(LOGIN_HEADER) != null &&
                    this.provider.getUser(request.getHeader(LOGIN_HEADER)) != null) {
                return true;
            }
            return false;
        }
        return true;
    }

    public User getUserFromRequest(HttpServletRequest request) {
        return this.provider.getUser(request.getHeader(LOGIN_HEADER));
    }

    public String login(String username, String clientIp) {
        JsonObject jsonObject = Json.object();
        jsonObject.set("username", username);
        jsonObject.set("clientIp", clientIp);
       
        String token = SecurityUtil.generateToken(jsonObject);
      
        User user = new ApplicationUser(username, token, clientIp);
        this.provider.addUser(user);
        return token;
    }

    public void logout(String loginToken) {
        this.provider.removeUser(loginToken);
    }
}
