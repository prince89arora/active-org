package org.active.web.init.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

import static org.active.web.init.security.SecurityConstants.LOGIN_COOKIE;

/**
 *
 */
public class SecurityContext {

    private SecurityInitializer initializer;

    public SecurityContext(SecurityInitializer initializer) {
        this.initializer = initializer;
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
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(LOGIN_COOKIE)) {
                return true;
            }
        }
        return false;
    }
}
