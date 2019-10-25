package cn.pxy.ysframework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 格式化时间
 */
public class DateTime {

   public String Now;

   public DateTime(){
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       this.Now = sdf.format(new Date());
   }

    /**
     * 返回此刻之后多少秒的时间
     * @param afterTime 将返回此刻+afterTime
     */
    public DateTime(long afterTime){
        long expireTime = System.currentTimeMillis() + afterTime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.Now = sdf.format(expireTime);
    }

    @Override
    public String toString() {
        return Now;
    }
}