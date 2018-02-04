package com.play001.cloud.cms.util;

import java.io.*;

public class SerializableUtil {

    //序列化
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()){
            oos = new ObjectOutputStream(byteArrayOutputStream);
            oos.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(oos != null ){
                try {
                    oos.close();
                }catch (Exception e){
                     e.printStackTrace();
                }
            }
        }
        return null;
    }
    //反序列化
    public static Object unSerialize(byte[] bytes){
        ObjectInputStream objectInputStream = null;
        try (ByteArrayInputStream byteArrayIutputStream = new ByteArrayInputStream(bytes);){
            objectInputStream = new ObjectInputStream(byteArrayIutputStream);
            return objectInputStream.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(objectInputStream != null ){
                try {
                    objectInputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
