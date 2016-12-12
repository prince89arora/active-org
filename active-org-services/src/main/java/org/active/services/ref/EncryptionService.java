package org.active.services.ref;

import org.active.services.annotations.Service;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 */
@Service
public class EncryptionService {

    private static final Logger log = Logger.getLogger(EncryptionService.class);

    public String getHash(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes());
            return new String(hash);
        } catch (NoSuchAlgorithmException e) {
            log.error("Error creating hash -> ", e);
        }
        return null;
    }
}
