package project_one.shiro.security;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import java.security.Key;

public class ShirosecurityUtil {

    public String Base64Encode(String str){
        return Base64.encodeToString(str.getBytes());
    }

    public String Base64decode(String str){
        return Base64.decodeToString(str.getBytes());
    }

    public String Hexencode(String str){
        return Hex.encodeToString(str.getBytes());
    }

    public String Hexdecode(String str){
        return new String(Hex.decode(str.getBytes()));
    }

    public String MD5(String str,String salt){
        return new Md5Hash(str,salt).toString();
    }

    public String SHA256(String str,String salt){
        return new Sha256Hash(str,salt).toString();
    }

    /**
     * Simple hash string.
     *
     * 内部使用messagedigest
     *
     * @param str  the str
     * @param salt the salt
     * @return the string
     */
    public String SimpleHash(String str,String salt){
        return new SimpleHash("SHA-1",str,salt).toString();
    }

    /**
     * Hash service string.
     *
     * @param alname     the alname
     * @param publicSalt the public salt
     * @return the string
     *
    1、首先创建一个DefaultHashService，默认使用SHA-512算法；
    2、可以通过hashAlgorithmName属性修改算法；
    3、可以通过privateSalt设置一个私盐，其在散列时自动与用户传入的公盐混合产生一个新盐；
    4、可以通过generatePublicSalt属性在用户没有传入公盐的情况下是否生成公盐；
    5、可以设置randomNumberGenerator用于生成公盐；
    6、可以设置hashIterations属性来修改默认加密迭代次数；
    7、需要构建一个HashRequest，传入算法、数据、公盐、迭代次数。
     *
     */
    public String HashService(String alname,String publicSalt){
        DefaultHashService hashService=new DefaultHashService();
        hashService.setHashAlgorithmName(alname);
        hashService.setPrivateSalt(new SimpleByteSource(publicSalt));
        hashService.setGeneratePublicSalt(true);
        hashService.setHashIterations(1);

        HashRequest request=new HashRequest.Builder().setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("Hello")).setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();
        return hashService.computeHash(request).toHex();
    }

    /**
     * Genrandom string.
     *
     * 生成随机数
     *
     * @param str the str
     * @return the string
     */
    public String genrandom(String str){
        SecureRandomNumberGenerator secureRandomNumberGenerator=new SecureRandomNumberGenerator();
        secureRandomNumberGenerator.setSeed("123".getBytes());
        return secureRandomNumberGenerator.nextBytes().toHex();
    }

    /**
     * Ae sencode string.
     *
     * aes加密
     *
     * @param str the str
     * @return the string
     */
    public String AESencode(String str){
        AesCipherService aesCipherService=new AesCipherService();
        aesCipherService.setKeySize(128);
        Key key=aesCipherService.generateNewKey();
        return aesCipherService.encrypt(str.getBytes(),key.getEncoded()).toHex();
    }

    /**
     * Ae sdecode string.
     *
     * aes解密
     *
     * @param str the str
     * @param key the key
     * @return the string
     */
    public String AESdecode(String str,Key key){
        AesCipherService aesCipherService=new AesCipherService();
        return aesCipherService.decrypt(str.getBytes(),key.getEncoded()).toHex();
    }
    
}
