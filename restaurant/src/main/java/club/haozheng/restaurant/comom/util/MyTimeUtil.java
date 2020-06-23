package club.haozheng.restaurant.comom.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MyTimeUtil {
    //获取系统当前时间，字符串类型
    public static String getStrDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置为东八区
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date newDate = new Date();
        String dateStr = sdf.format(newDate);
        return dateStr;
    }

    //获取系统当前时间Date类型，需要将字符串类型转成时间
    public static Date getDaDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置为东八区
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date date = new Date();
        String dateStr = sdf.format(date);
        System.out.println(dateStr);

        //将字符串转成时间
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newDate = null;
        try {
            newDate = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

    //计算时间分钟差
    public static int Minute(Date time){
        long from3 = time.getTime();
        long to3 = new Date().getTime();
        int minutes = (int) ((to3 - from3) / (1000 * 60));
        System.out.println("两个时间之间的分钟差为：" + minutes);
        return minutes;
    }

}
