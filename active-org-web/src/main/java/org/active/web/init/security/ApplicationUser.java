package org.active.web.init.security;

import java.util.Map;

/**
 *
 */
public class ApplicationUser extends User {

    public ApplicationUser(String userId, String loginToken) {
        this.loginToken = loginToken;
        this.userId = userId;
    }

    @Override
    protected Map<String, String> properties() {
        return null;
    }
}
