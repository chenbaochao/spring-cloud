import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.junit.Test;

public class ITest {

    @Test
    public void Itest(){
        System.out.println(DigestUtils.md5Hex("123456"));
    }
}
