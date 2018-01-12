package com.play001.cloud.cms.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Objects;

public class CommonUtil {


    //通过IP获取地址 返回示例:中国-四川省-成都市
    public static String getLocationByIp(String ip){
        String json;
        try {
            json = httpGet("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
            JsonElement je = new JsonParser().parse(json);
            if(je.getAsJsonObject().get("code").getAsInt() != 0) return "未知";
            je = je.getAsJsonObject().get("data");
            StringBuilder location = new StringBuilder();
            String country = je.getAsJsonObject().get("country").getAsString();
            location.append(country);
            String region = je.getAsJsonObject().get("region").getAsString();
            location.append("-").append(region);
            String city = je.getAsJsonObject().get("city").getAsString();
            location.append("-").append(city);
            return location.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "未知";
        }
    }

    /**
     * 获取文件名后缀
     * @param fileName 文件名
     * @return 成功返回后缀,失败返回null
     */
    public static String getFileExt(String fileName){
        String []fileNameArr = fileName.split("\\.");
        if(fileNameArr.length > 1){
            return fileNameArr[fileNameArr.length-1];
        }
        return null;
    }

    /**
     * 判断文件后缀是否符合要求
     * @param ext 文件名后缀
     */
    public static boolean checkFileExt(String ext){
        //支持的文件类型
        String []supportExts = {"jpg","jpeg", "png", "ico", "gif", "bmp"};
        for(String supportExt : supportExts){
            if(Objects.equals(supportExt, ext.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    /**
     * 裁剪图片
     * @param file 源文件
     * @param  avatarData json格式的参数
     */
    public static InputStream cutImage(MultipartFile file, String avatarData){
        try {

            // 创建JSONObject对象
            JsonElement je = new JsonParser().parse(avatarData);
            // 用户经过剪辑后的图片的大小
            int x = je.getAsJsonObject().get("x").getAsInt();
            int y = je.getAsJsonObject().get("y").getAsInt();
            int width = je.getAsJsonObject().get("width").getAsInt();
            int height = je.getAsJsonObject().get("height").getAsInt();
            BufferedImage src = ImageIO.read(file.getInputStream()); // 读入文件
            BufferedImage bufferedImage = src.getSubimage(x > 0 ? x : 0, y > 0 ? y : 0, width, height);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage,"jpg" , os);
            return new ByteArrayInputStream(os.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * HTTP GET
     */
    private static String httpGet(String reqUrl) throws Exception {
        InputStream inputStream = null;
        try{
            URL realUrl = new URL(reqUrl);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            connection.setDoOutput(true);
            // 定义 BufferedReader输入流来读取URL的响应
            inputStream = connection.getInputStream();
            return IOUtils.toString(inputStream, "UTF-8");
        }catch (Exception e){
            throw  e;
        }finally {
            if(inputStream != null) inputStream.close();

        }
    }
}
