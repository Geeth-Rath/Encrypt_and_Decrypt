package org.example;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

//Using ARS algorithum
public class AES {
    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = "1234567891234567".getBytes();

    public static void main(String[] args) throws Exception {
        Key key = generateKey();
        String encriptedValue = encrypt("Geethma", key);
        String decriptedValue = decrypt(encriptedValue, key);
        System.out.println("encripted Value: " + encriptedValue);
        System.out.println("decripted Value: " + decriptedValue);
    }

    private static Key generateKey() {
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        return key;

    }

    public static String encrypt(String valueToEnc, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        // Perform encryption
        byte[] encryptedValue = cipher.doFinal(valueToEnc.getBytes());
        // Convert to Base64 and print
        String base64Encoded = Base64.getEncoder().encodeToString(encryptedValue);
        return base64Encoded;
    }

    public static String decrypt(String valueToDec, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decrypBase64Encoded = Base64.getDecoder().decode(valueToDec.getBytes(StandardCharsets.UTF_8));
        byte[] decryptValue = cipher.doFinal(decrypBase64Encoded);
        return new String(decryptValue);
    }

}
