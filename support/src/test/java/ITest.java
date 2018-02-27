
import java.util.Random;

public class ITest {

    public void IT() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c1 = Class.forName("com.play001.cloud.support.entity.Product.Specification");
        c1.newInstance();

    }

    private int a(){
        try {
            throw new Exception("1");
        }catch (Exception e){
            return 2;
        }
    }
}
