package org.active.security.crypto;

public interface Encryption {

    String encrypt(String text);

    String decrypt(String text);
}
