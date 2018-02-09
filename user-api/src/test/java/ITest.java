
import com.play001.cloud.support.entity.user.User;
import org.junit.Test;


public class ITest {


    @Test
    public void it()   {
        User user = new User();
        user.setUsername("sss");
        System.out.println(user.toString());

    }



}
