package org.active.security;

/**
 *
 */
public class SecurityMapping {

    private String path;
    private boolean authorized = false;

    private SecurityMapping(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
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
