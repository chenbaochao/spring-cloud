import com.play001.cloud.cms.controller.rest.CommonRestController;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.junit.Test;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class ITest {

    public int i = 1;
    @Test
    public void Itest(){
       byte b = 1;
       byte a = 2;

    }
    private int get(){
        try {
            throw new Exception();
        }catch (Exception e){
            return get1();
        }finally {
            return get2();
        }
    }

    private int get1(){
        System.out.println("get1--------------");
        return i++;
    }
    private int get2(){
        System.out.println("get2--------------");
        return i++;
    }
}
