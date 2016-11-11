package org.active.web.init.security;

import org.active.web.init.commons.HttpMethod;

/**
 *
 */
public class SecurityMapping {

    private String path;
    private String[] roles;
    private HttpMethod[] httpMethods;
    private String[] permissions;
    private boolean authorized = false;

    private SecurityMapping(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String[] getRoles() {
        return roles;
    }

    public HttpMethod[] getHttpMethods() {
        return httpMethods;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public static SecurityMappingBuilder builder(String path, SecurityInitializer.SecurityBuilder securityBuilder) {
        return new SecurityMappingBuilder(new SecurityMapping(path), securityBuilder);
    }

    public static class SecurityMappingBuilder {

        private SecurityMapping controller;
        private SecurityInitializer.SecurityBuilder securityBuilder;

        public SecurityMappingBuilder(SecurityMapping controller, SecurityInitializer.SecurityBuilder securityBuilder) {
            this.controller = controller;
            this.securityBuilder = securityBuilder;
        }

        public SecurityMappingBuilder roles(String[] roles) {
            this.controller.roles = roles;
            return this;
        }

        public SecurityMappingBuilder permissions(String[] permissions) {
            this.controller.permissions = permissions;
            return this;
        }

        public SecurityMappingBuilder httpMethods(HttpMethod[] httpMethods) {
            this.controller.httpMethods = httpMethods;
            return this;
        }

        public SecurityMappingBuilder authorized(boolean authorized) {
            this.controller.authorized = authorized;
            return this;
        }

        public SecurityInitializer.SecurityBuilder add() {
            this.securityBuilder.saveController(this.controller);
            return this.securityBuilder;
        }

    }
}
