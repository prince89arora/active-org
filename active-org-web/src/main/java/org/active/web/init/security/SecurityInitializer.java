package org.active.web.init.security;

import org.active.web.init.commons.ApplicationContextFactory;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class SecurityInitializer {

    private static final Logger log = Logger.getLogger(SecurityInitializer.class);

    private List<SecurityMapping> securityMappings;
    private static SecurityInitializer initializer;
    private String logoutPath;
    private String loginPath;
    private SecurityMapping rootMapping;

    private SecurityInitializer() {}

    public static SecurityBuilder init() {
        if (initializer == null) {
            initializer = new SecurityInitializer();
            initializer.securityMappings = new ArrayList<SecurityMapping>();
            return new SecurityBuilder();
        } else {
            return new SecurityBuilder();
        }
    }

    public List<SecurityMapping> getSecurityMappings() {
        return securityMappings;
    }

    public String getLogoutPath() {
        return logoutPath;
    }

    public String getLoginPath() {
        return loginPath;
    }

    public SecurityMapping getRootMapping() {
        return rootMapping;
    }

    public static class SecurityBuilder {

        public SecurityMapping.SecurityMappingBuilder url(String path) {
            return SecurityMapping.builder(path, this);
        }

        public void saveController(SecurityMapping mapping) {
            if (!mapping.getPath().startsWith("/")) {
                log.warn("Unable to add invalid mapping in security -> "+ mapping.getPath());
            } else {
                if (mapping.getPath().equals("/")) {
                    initializer.rootMapping = mapping;
                } else {
                    initializer.securityMappings.add(mapping);
                }
            }
        }

        public SecurityBuilder logoutPath(String path) {
            initializer.logoutPath = path;
            return this;
        }

        public SecurityBuilder loginPath(String path) {
            initializer.loginPath = path;
            return this;
        }

        public void secure() {
            SecurityContext securityContext = new SecurityContext(initializer);
            ApplicationContextFactory.INIT.setSecurityContext(securityContext);
        }
    }

}
