import org.junit.Test;

public class ITest {

    @Test
    public void Itest() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class c1 = Class.forName("com.play001.cloud.support.entity.Product$Specification");
        c1.newInstance();
    }



}

class T{
    T() {
        System.out.println("测试");
    }
}