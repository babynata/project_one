import base.BaseTest;
import org.junit.Test;
import project_one.util.RSAUtil;

import java.io.IOException;
import java.util.Map;

public class RSAUtilTest extends BaseTest {

    private final static String KEY_PRIVATE="RSAPrivateKey";

    private final static String KEY_PUBLIC="RSAPublicKey";

    @Test
    public void testRSA() throws IOException {
        String data="root1234";
        Map<String, Object> map = RSAUtil.init();
        String privateKey = RSAUtil.getPrivatekey(map);
        String publicKey = RSAUtil.getPublicKey(map);
        System.out.println("private key: " + privateKey);
        System.out.println("public key: " + publicKey);

        String encode = RSAUtil.encryptByPublicKey(publicKey, data);
        System.out.println("encode: " + encode);
        String decode = RSAUtil.decryptByPrivateKey(privateKey, encode);
        System.out.println("decode: "+decode);
    }
}
