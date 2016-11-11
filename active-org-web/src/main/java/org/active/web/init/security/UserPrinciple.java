package org.active.web.init.security;

import java.security.Principal;

/**
 *
 */
public class UserPrinciple implements Principal {

    private String name;

    public UserPrinciple(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object another) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }
}
