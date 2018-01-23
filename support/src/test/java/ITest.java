
import java.util.Random;

public class ITest {
    public void IT(){
        for(int i = 0;i <200;i++){
            System.out.println( new Random().nextInt(8999)+1000);
        }

    }

    private int a(){
        try {
            throw new Exception("1");
        }catch (Exception e){
            return 2;
        }
    }
}
