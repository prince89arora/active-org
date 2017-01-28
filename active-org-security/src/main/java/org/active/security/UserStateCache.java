package org.active.security;

import java.util.HashMap;
import java.util.Map;

/**
 * Caching user status in a {@link Map}, user can be retrieved
 * using {@link #getUser(String)} based on any logintoken.
 *
 * @author princearora
 */
public class UserStateCache {

    private static Map<String, User> loginUsers = new HashMap<>();

    private static UserStateCache provider = null;

    private UserStateCache() {}

    public static UserStateCache getProvider() {
        if (provider != null) {
            return provider;
        }
        return new UserStateCache();
    }

    public void addUser(User user) {
        loginUsers.put(user.getLoginToken(), user);
    }

    public void removeUser(String loginToken) {
        if (loginToken != null && loginUsers.containsKey(loginToken)) {
            loginUsers.put(loginToken, null);
        }
    }

    public User getUser(String loginToken) {
        return loginUsers.get(loginToken);
    }

}