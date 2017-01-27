package org.active.security.crypto;

public interface Encryption extends Closeable {

    String encrypt(String text);

    String decrypt(String text);
}