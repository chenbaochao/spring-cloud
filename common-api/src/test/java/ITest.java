import com.play001.cloud.support.util.DateUtil;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ITest {

    public static void main(String[] args)
    {
        //staticFunction();
    }

    static ITest st = new ITest();

    static
    {
        System.out.println("1");
    }
    {
        System.out.println("2");
    }

    ITest()
    {
        System.out.println("3");
        System.out.println("a="+a+",b="+b);
    }

    private static void staticFunction(){
        System.out.println("4");
    }

    int a=110;
    static int b =112;

}
