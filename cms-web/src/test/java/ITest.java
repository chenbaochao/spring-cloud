import com.play001.cloud.cms.controller.rest.CommonRestController;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.junit.Test;

public class ITest {

    @Test
    public void Itest(){
        String sort = "showPrice";
        switch (sort){
            case "showPrice":
            case "soldNumber":
            case "createTime":
            case "status":
                break;
            default:
                sort = "createTime";

        }
        System.out.println(sort);
    }
}
