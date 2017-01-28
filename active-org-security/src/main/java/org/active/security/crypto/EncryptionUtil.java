package org.active.security.crypto;

import java.security.SecureRandom;

/**
 * @author princearora
 */
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
  
   public static byte[] generateKey(int size) {
     SecureRandom random = new SecureRandom();
     byte[] bytes = new byte[size];
     random.nextBytes(bytes); 
     return bytes;
   }
}
