package org.active.security.crypto;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 *
 */
public class AESEncryption implements Encryption {

    private static AESEncryption encryption = null;

    private String key;

    private Cipher cipher;

    private String initVector;

    private IvParameterSpec ivParameterSpec;

    private SecretKeySpec keySpec;

    private static final String CIPHER = "AES/CBC/PKCS5PADDING";

    private AESEncryption() {
        try {
            this.cipher = Cipher.getInstance(CIPHER);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public static AESEncryption instance() {
        if (encryption != null) {
            return encryption;
        }
        return new AESEncryption();
    }

    @Override
    public String encrypt(String text) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
            byte[] bytes = cipher.doFinal(text.getBytes());
            return Base64.encodeBase64String(bytes);
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException |
                InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String decrypt(String text) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
            byte[] bytes = cipher.doFinal(Base64.decodeBase64(text));
            return new String(bytes);
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException |
                InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setKey(String key) {
        this.key = key;
        this.keySpec = new SecretKeySpec(this.key.getBytes(),
                EncryptionUtil.Type.AES.toString());
    }

    public void setInitVector(String initVector) {
        this.initVector = initVector;
        this.ivParameterSpec = new IvParameterSpec(this.initVector.getBytes());
    }
}
