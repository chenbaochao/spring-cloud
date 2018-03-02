import org.junit.Test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ITest {

    @Test
    public void Itest() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        SoftReference<String> sr = new SoftReference<>(new String("hello"));
       // LinkedBlockingQueue
        System.out.println(sr.get());
        System.gc();                //通知JVM的gc进行垃圾回收
        System.out.println(sr.get());
    }



}

class T{
    T() {
        System.out.println("测试");
    }
}