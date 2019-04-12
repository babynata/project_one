package project_one.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AESUtil {

    private final static String RULE="";

    public static String AESencode(String data) {

        try {
            byte[] dataBytes = data.getBytes("utf-8");

            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(128, new SecureRandom(RULE.getBytes()));
            SecretKey original_key = generator.generateKey();
            byte[] raw = original_key.getEncoded();
            SecretKey key = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] resultBytes = cipher.doFinal(dataBytes);
            String result = new String(new BASE64Encoder().encode(resultBytes));
            return result;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static String AESdecode(String data) {

        try {
            byte[] dataBytes = new BASE64Decoder().decodeBuffer(data);

            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(128, new SecureRandom(RULE.getBytes()));
            SecretKey original_key = generator.generateKey();
            byte[] raw = original_key.getEncoded();
            SecretKey key = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] resultBytes=cipher.doFinal(dataBytes);
            String result = new String(resultBytes, "utf-8");
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args){
        System.out.println("".length());

    }
}
