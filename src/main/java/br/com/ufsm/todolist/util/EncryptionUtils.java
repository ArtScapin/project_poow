package br.com.ufsm.todolist.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionUtils {
    private static final String SECRET_KEY = "4f60e4d2a05e783c";

    public static String encrypt(String data) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedData = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String encryptedData) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            return new String(decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
