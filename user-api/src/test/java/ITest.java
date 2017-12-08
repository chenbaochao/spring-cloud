import com.google.gson.Gson;
import com.play001.cloud.userapi.entity.Response;
import com.play001.cloud.userapi.util.Captcha;
import org.hibernate.validator.internal.constraintvalidators.bv.size.SizeValidatorForArraysOfByte;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ITest {


    @Test
    public void it() throws IOException {
        String code = Captcha.randCaptchaCode();
        String uuid = UUID.randomUUID().toString();

        BufferedImage bi = Captcha.createCaptchaImg(code);

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        ImageIO.write(bi, "JPG", os);

        byte []imageByte = os.toByteArray();//图片验证码字节长度不定
        byte []codeByte = uuid.getBytes();//验证码的cookie长度为36,

        byte []data = new byte[36+imageByte.length];
        for(int i = 0;i < imageByte.length;i++){
            data[i] = imageByte[i];
        }
        for(int i= imageByte.length;i < data.length; i++){
            data[i] = codeByte[i-imageByte.length];
        }
        Response<byte[]> response = new Response<>(Response.SUCCESS);
        response.setMessage(data);
        System.out.println(new Gson().toJson(response));
        //os.write(data);
        os.flush();
        os.close();

    }



}
