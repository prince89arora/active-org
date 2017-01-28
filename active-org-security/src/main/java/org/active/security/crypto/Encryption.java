package org.active.security.crypto;

import java.io.Closeable;

/**
 * @author princearora
 */
public interface Encryption extends Closeable {

    String encrypt(String text);

    String decrypt(String text);
}