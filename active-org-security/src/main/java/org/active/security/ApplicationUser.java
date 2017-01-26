package org.active.security;

import java.util.Map;

/**
 *
 */
public class ApplicationUser extends User {

    public ApplicationUser(String userId, String loginToken, String origin) {
        this.loginToken = loginToken;
        this.userId = userId;
        this.origin = origin;
    }

    @Override
    protected Map<String, String> properties() {
        return null;
    }
}
