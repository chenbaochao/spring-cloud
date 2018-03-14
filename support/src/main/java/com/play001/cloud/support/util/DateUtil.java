package com.play001.cloud.support.util;


import sun.util.resources.LocaleData;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    private static final DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private DateUtil() {
    }

    /**
     * @return return for example 1997
     */
    public static int getYear(){
        return LocalDate.now().getYear();
    }
    /**
     * @return return for example 02
     */
    public static int getMonth(){
        return LocalDate.now().getMonthValue();
    }
    /**
     * @return return for example 05
     */
    public static int getDay(){
        return LocalDate.now().getDayOfMonth();
    }
    /**
     * @return return for example 1997-01-01 02:02:02
     */
    public static String getTime(){
        return  formatter.format(LocalDateTime.now());
    }
    /**
     * @param time 当前时间戳
     * @return return for example 1997-01-01 02:02:02
     */
    public static String getTime(Long time) {
        Instant instant = Instant.ofEpochSecond(System.currentTimeMillis()/1000);
        return formatter.format(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));
    }
}
