package org.active.security.util;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.Json;

public class SecurityUtil {
  
  public static String generateToken(JsonObject json, String key, String initVector) {
        System.out.println(json.toString());
        try(AESEncryption encryption = (AESEncryption) EncryptionUtil.getInstance(EncryptionUtil.Type.AES)) {
            if (key != null && !key.equals("")) {
                encryption.setKey(Base64.decodeBase64(key));
            }

            if (initVector != null && !initVector.equals("")) {
                encryption.setInitVector(Base64.decodeBase64(initVector));
            }
            return encryption.encrypt(json.toString());
        } catch (EncryptionTypeNotFound | IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
  
     public static JsonObject decryptToken(String token, String key, String initVector) {
        System.out.println("Decrypting: "+token);
        try(AESEncryption encryption = (AESEncryption) EncryptionUtil.getInstance(EncryptionUtil.Type.AES)) {
            if (key != null && !key.equals("")) {
                encryption.setKey(Base64.decodeBase64(key));
            }

            if (initVector != null && !initVector.equals("")) {
                encryption.setInitVector(Base64.decodeBase64(initVector));
            }

            String decryptedData = encryption.decrypt(token);
            return Json.parse(decryptedData).asObject();
        } catch (EncryptionTypeNotFound | IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}