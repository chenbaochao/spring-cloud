package com.play001.cloud.common.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * @return return for example 1997
     */
    public static String getYear(){
        return new SimpleDateFormat("yyyy").format(new Date());
    }
    /**
     * @return return for example 02
     */
    public static String getMonth(){
        return new SimpleDateFormat("MM").format(new Date());
    }
    /**
     * @return return for example 05
     */
    public static String getDay(){
        return new SimpleDateFormat("dd").format(new Date());
    }
    /**
     * @return return for example 1997-01-01 02:02:02
     */
    public static String getTime(){ return  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());}
}
