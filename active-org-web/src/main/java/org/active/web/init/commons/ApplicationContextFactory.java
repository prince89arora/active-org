package org.active.web.init.commons;


import org.active.security.SecurityContext;
import org.active.security.User;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Application context factory to contain application level
 * information and configurations used to initialise
 * application.
 *
 * @author princearora
 */
public enum ApplicationContextFactory {

    INIT;

    private ServletContext servletContext;
    private SecurityContext securityContext;

    private Map<String, User> loginUsers;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public ServletContext getServletContext() {
        return this.servletContext;
    }

    public SecurityContext getSecurityContext() {
        return securityContext;
    }

    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    public void addLoginUser(User user) {
        if (this.loginUsers == null) {
            this.loginUsers = new HashMap<>();
        }

        this.loginUsers.put(user.getLoginToken(), user);
    }

    public User getLoginUser(String loginToken) {
        return (this.loginUsers != null) ? this.loginUsers.get(loginToken) : null;
    }

    public void logoutUser(String loginToken) {
        if (this.loginUsers.containsKey(loginToken) && loginToken != null) {
            this.loginUsers.put(loginToken, null);
        }
    }
}
