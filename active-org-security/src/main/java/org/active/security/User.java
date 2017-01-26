package org.active.security;

import java.util.Map;

/**
 *
 */
public abstract class User {

    protected String userId;
    protected String loginToken;
    protected String origin;

    public String getUserId() {
        return userId;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    protected abstract Map<String, String> properties();

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
