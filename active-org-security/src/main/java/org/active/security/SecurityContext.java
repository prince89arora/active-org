package org.active.security;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;
import org.active.security.util.SecurityUtil;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.Json;

/**
 *
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
        //todo check header token
        return false;
    }

    public String getLoginToken(HttpServletRequest request) {
        //todo get login token from header
        return null;
    }

    public void login(String username, String clientIp, String key, String initVector) {
        JsonObject jsonObject = Json.object();
        jsonObject.set("username", username);
        jsonObject.set("clientIp", clientIp);
       
        String token = SecurityUtil.generateToken(jsonObject, String key, String initVector);
      
        User user = new ApplicationUser(username, token, clientIp);
        this.provider.addUser(user);
    }
}
