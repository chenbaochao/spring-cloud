
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ITest {

    @Test
    public void iTest() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        System.out.println(System.currentTimeMillis());
    }


}
