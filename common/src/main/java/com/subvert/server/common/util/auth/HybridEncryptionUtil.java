package com.subvert.server.common.util.auth;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * @author xujianguo
 * @date 2025/3/28
 * @description
 */

public class HybridEncryptionUtil {

    private HybridEncryptionUtil() {
        throw new AssertionError("HybridEncryptionUtil cannot be instantiated");
    }

    // 生成RSA密钥对
    public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    // 生成AES密钥
    public static SecretKey generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }

    // 使用AES密钥加密数据
    public static byte[] encryptDataWithAES(byte[] data, SecretKey aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        return cipher.doFinal(data);
    }

    // 使用AES密钥解密数据
    public static byte[] decryptDataWithAES(byte[] encryptedData, SecretKey aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        return cipher.doFinal(encryptedData);
    }

    // 使用RSA公钥加密AES密钥
    public static byte[] encryptAESKeyWithRSA(SecretKey aesKey, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(aesKey.getEncoded());
    }

    // 使用RSA私钥解密AES密钥
    public static SecretKey decryptAESKeyWithRSA(byte[] encryptedAesKey, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decodedKey = cipher.doFinal(encryptedAesKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    // 将字节数组转换为Base64编码的字符串
    public static String encodeBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    // 将Base64编码的字符串转换为字节数组
    public static byte[] decodeBase64(String base64Data) {
        return Base64.getDecoder().decode(base64Data);
    }
}
