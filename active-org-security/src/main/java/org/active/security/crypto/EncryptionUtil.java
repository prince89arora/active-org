package org.active.security.crypto;

public class EncryptionUtil {

    public static Encryption getInstance(Type type) throws EncryptionTypeNotFound {
        switch (type) {
            case AES:
                return AESEncryption.instance();
            default:
                throw new EncryptionTypeNotFound();
        }
    }
  
    public static enum Type {
        AES
    }
}
