package org.active.security.crypto;

public class EncryptionTypeNotFound extends RuntimeException {

    public EncryptionTypeNotFound(String message) {
        super(message);
    }

    public EncryptionTypeNotFound() {
        super();
    }
}
