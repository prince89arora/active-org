package org.active.security.util;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.Json;
import org.active.security.crypto.AESEncryption;
import org.active.security.crypto.EncryptionTypeNotFound;
import org.active.security.crypto.EncryptionUtil;
import org.apache.log4j.Logger;

/**
 * @author princearora
 */
public class SecurityUtil {

    private static final Logger log = Logger.getLogger(SecurityUtil.class);

    public static String generateToken(JsonObject json) {
        System.out.println(json.toString());
        try {
            AESEncryption encryption = (AESEncryption) EncryptionUtil.getInstance(EncryptionUtil.Type.AES);
            return encryption.encrypt(json.toString());
        } catch (EncryptionTypeNotFound ex) {

        }
        return null;
    }

    public static JsonObject decryptToken(String token) {
        System.out.println("Decrypting: " + token);
        try {
            AESEncryption encryption = (AESEncryption) EncryptionUtil.getInstance(EncryptionUtil.Type.AES);
            String decryptedData = encryption.decrypt(token);
            return Json.parse(decryptedData).asObject();
        } catch (EncryptionTypeNotFound ex) {

        }
        return null;
    }
}