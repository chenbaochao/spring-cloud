import com.play001.cloud.userapi.util.Captcha;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

public class ITest {


    @Test
    public void it() throws FileNotFoundException {
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));

    }



}
