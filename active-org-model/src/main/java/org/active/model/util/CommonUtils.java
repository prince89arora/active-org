package org.active.model.util;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 */
public class CommonUtils {

    private static final Logger log = Logger.getLogger(CommonUtils.class);

    public static String getHash(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(text.getBytes());

            String finalString = "";
            for (int i = 0; i < hash.length; i++) {
                finalString = finalString + Integer.toHexString(hash[i]);
            }

            return finalString;
        } catch (NoSuchAlgorithmException e) {
            log.error("Error creating hash -> ", e);
        }
        return null;
    }

}
