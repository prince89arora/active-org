package org.active.web.init.security;

import java.util.Map;

/**
 *
 */
public abstract class User {

    protected String userId;
    protected String loginToken;

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

}
