package project_one.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAUtil {

    private final static String KEY_ALGORITHM="RSA";

    private final static String KEY_SIGNATURE="MD5withRSA";

    private final static String KEY_PRIVATEKEY="RSAPrivateKey";

    private final static String KEY_PUBLICKEY="RSAPublicKey";

    private final static int KEY_LEN=512;

    private final static int MAX_ENCRYPT_LEN=128;

    private final static int MAX_DECRYPT_LEN=128;

    public static Map<String,Object> init(){

        Map<String,Object> map = new HashMap<String, Object>();

        try {

            KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            generator.initialize(KEY_LEN);
            KeyPair keyPair=generator.generateKeyPair();
            PrivateKey privateKey=keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            map.put(KEY_PRIVATEKEY,privateKey);
            map.put(KEY_PUBLICKEY, publicKey);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static String encodeByBase64(byte[] key){
        return new BASE64Encoder().encodeBuffer(key);
    }

    public static byte[] decodeByBase64(String key) throws IOException {
        return new BASE64Decoder().decodeBuffer(key);
    }

    public static String getPrivatekey(Map<String, Object> map) {

        Key key = (Key) map.get(KEY_PRIVATEKEY);
        return encodeByBase64(key.getEncoded());
    }

    public static String getPublicKey(Map<String, Object> map) {

        Key key = (Key) map.get(KEY_PUBLICKEY);
        return encodeByBase64(key.getEncoded());
    }

    public static String decryptByPrivateKey(String privateKey,String data){

        byte[] result={};
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String decode = "";

        try {

            byte[] dataBytes = decodeByBase64(data);
            int dataLen = dataBytes.length;
            byte[] bytes = decodeByBase64(privateKey);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey key = factory.generatePrivate(keySpec);
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE,key);

            int offset=0;
            int i=0;
            while (dataLen - offset > 0) {
                byte[] cache;
                if (dataLen - offset > MAX_DECRYPT_LEN) {
                    cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_LEN);
                } else {
                    cache = cipher.doFinal(dataBytes, offset, dataLen - offset);
                }
                out.write(cache,0,cache.length);
                i++;
                offset=i*MAX_DECRYPT_LEN;
            }

            result = out.toByteArray();
            out.close();
            decode=new String(result,"utf-8");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
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


        return decode;
    }

    public static String encryptByPublicKey(String publicKey,String data){

        byte[] result={};
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String encode = "";

        try{

            byte[] dataBytes = data.getBytes("utf-8");
            int dataLen = dataBytes.length;
            byte[] bytes = decodeByBase64(publicKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey key = factory.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, key);

            int offset=0;
            int i=0;
            while (dataLen - offset > 0) {
                byte[] cache;
                if (dataLen - offset > MAX_ENCRYPT_LEN) {
                    cache = cipher.doFinal(dataBytes, offset, MAX_ENCRYPT_LEN);
                } else {
                    cache = cipher.doFinal(dataBytes, offset, dataLen - offset);
                }
                out.write(cache,0,cache.length);
                i++;
                offset=i*MAX_ENCRYPT_LEN;
            }

            result=out.toByteArray();
            encode = new String(encodeByBase64(result));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return encode;
    }


}
