import com.play001.cloud.support.util.DateUtil;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ITest {

    private static final BASE64Decoder base64Decoder = new BASE64Decoder();
    private static final BASE64Encoder base64Encoder  = new BASE64Encoder();
    @Test
    public void iTest() throws IOException {
        byte b = -1;
        System.out.println(b);
    }

    public static void main(String[] args) {
        for(int i= 0;i<100;i++){
            for(int j= 0;j<100;j++){
                new Thread(()->{
                    System.out.println( DateUtil.getTime());
                }
                ).start();
            }

        }
    }
}
