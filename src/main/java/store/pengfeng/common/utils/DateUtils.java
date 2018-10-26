package store.pengfeng.common.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author pengfeng
 * Email: pengfeng@rayootech.com
 */
public class DateUtils {

    public static Date birthdayFormatter(Date birthday) {
        if (birthday == null) {
            return null;
        }
        LocalDate localDate = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 日期类转换成指定日期格式的字符串
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return the string 日期字符串
     */
    public static String dateToString(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
