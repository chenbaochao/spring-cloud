import org.junit.Test;

public class ITest {

    @Test
    public void it(){
        B b = new B();
        b.b = 2;
        A a = b;
        B b1 = (B)a;
        System.out.println(b.b);

    }

    class A{
        public int a = 1;
    }
    class B extends A{
        public int b = 1;
    }
}
