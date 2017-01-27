package org.active.security;

import java.util.HashMap;
import java.util.Map;
import org.active.security.User;

public class UserStateCache {

    private static Map<String, Object> loginUsers = new HashMap<>();

    private static UserStateCache provider = null;

    private UserStateCache() {}

    public UserStateCache getProvider() {
        if (provider != null) {
            return provider;
        }
        return new UserStateCache();
    }

    public static void addUser(User use) {
        loginUsers.put(user.getLoginToken(), user);
    }

    public static void removeUser(String loginToken) {
        if (loginToken != null && loginUsers.containsKey(loginToken)) {
            loginUsers.put(loginToken, null);
        }
    }

}