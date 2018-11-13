package main.java.com.mydao.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String getAfterDateByDays(Date date, int days)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY_MM_dd");
        try
        {
            Calendar now = Calendar.getInstance();
            now.setTime(date);
            now.add(Calendar.DAY_OF_YEAR,days);
            return sdf.format(now.getTime());
        }
        catch(Exception er)
        {
            return sdf.format(date);
        }
    }
}
