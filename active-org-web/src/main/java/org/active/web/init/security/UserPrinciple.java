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
        return "User -> { "+this.name+" }";
    }

    @Override
    public String getName() {
        return this.name;
    }
}
