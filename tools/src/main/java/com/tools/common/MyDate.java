package com.tools.common;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Locale;
import java.util.Calendar;
/**
 *
 * <p>Title: Server Frame</p>
 *
 * <p>Description: 时间和日期处理类</p>
 *
 * <p>Copyright: Copyright (c) 2016</p>
 *
 * <p>Company: CITIC</p>
 *
 * @author liushuai
 * @version 1.0
 */
public class MyDate extends GregorianCalendar
{
    public final static char ALIGN_LEFT = 'l'; //左对齐
    public final static char ALIGN_RIGHT = 'r'; //右对齐
    public final static char ALIGN_MIDDLE = 'm'; //中间对齐

    /**
     * 以传入参数的时区和地点构造本类.
     * @param timezone TimeZone
     * @param local Locale
     */
    public MyDate(TimeZone timezone, Locale local)
    {
        super(timezone, local);
    }

    /**
     *以缺省时区和地点构造本类
     */
    public MyDate()
    {
        this(TimeZone.getDefault(), Locale.CHINA);
    }

    /**
     * 以缺省时区和地点,以及传入的字符串构造构造本类
     * @param strDate String 指定格式的日期
     * @param format 日期格式，如："yyyyMMddHHmmssSSS" 或"yyyyMMdd"
     *  @throws ParseException 格式错误
     */
    public MyDate(String date, String format) throws ParseException
    {
        this();

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        setTime(sdf.parse(date));
    }

    /**
     * 构造方法
     * @param date long 从1970-1-1起的时间，ms
     */
    public MyDate(long date)
    {
        this();
        setTimeInMillis(date);
    }

    /**
     * 获取年份
     * @return the year
     */
    public int getYear()
    {
        return get(Calendar.YEAR);
    }

    /**
     * 获取月份
     * @return the month
     */
    public int getMonth()
    {
        return get(Calendar.MONTH) + 1;
    }

    /**
     * 获取一月中的日
     * @return the day
     */
    public int getDay()
    {
        return get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取一周中的日
     * @return the day of a week
     */
    public int getDayOfWeek()
    {
        return get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取小时
     * @return the hour
     */
    public int getHour()
    {
        return get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取millisecond
     * @return the time count by millisecond
     */
    public long getMilliSecond()
    {
        return getTime().getTime();
    }

    /**
     * 获取分钟
     * @return the minute
     */
    public int getMinute()
    {
        return get(Calendar.MINUTE);
    }

    /**
     * 获取秒数
     * @return the second
     */
    public int getSecond()
    {
        return get(Calendar.SECOND);
    }

    /**
     * 将时间戳格式化为 "yyyy:mm:dd hh:mm:ss"
     * @return String --a new formated time stamp
     */
    public String formatDateTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
        return sdf.format(getTime());
    }

    /**
     * 将日期格式化为 yyyymmdd
     * @return String --a new formated time string
     */
    public String formatDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(getTime());
    }

    /**
     * 将时间格式化为 HH:mm:ss
     * @return String --a new formated time string
     */
    public String formatTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(getTime());
    }

    /**
     * 将时间格式化为 HHmmss
     * @return String --a new formated time string
     */
    public String formatShortTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        return sdf.format(getTime());
    }

    /**
     * 获取格式日期时间字符串
     * @return String 形如"20050405 21:01:23 "
     */
    public String formatTimestamp()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        return sdf.format(getTime());
    }

    /**
     * 获取java.sql.Date
     * @return Date
     */
    public java.sql.Date getSqlDate()
    {
        return new java.sql.Date(getMilliSecond());
    }

    /**
     * 获取java.sql.Time
     * @return Time
     */
    public java.sql.Time getSqlTime()
    {
        return new java.sql.Time(getMilliSecond());
    }

    /**
     * 获取java.sql.Timestamp
     * @return Timestamp
     */
    public java.sql.Timestamp getSqlDateTime()
    {
        return new java.sql.Timestamp(getMilliSecond());
    }

    /*-----------------静态方法----------------*/
    /**
     * 根据输入的java.sql.Date 转换成格式日期字符串
     * @param tamp Date
     * @return String  形如"20050405"
     */
    public static String getDateString(java.sql.Date tamp)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(tamp);
    }

    /**
     * 根据输入的java.sql.Timestamp 转换成格式日期字符串
     * @param tamp Timestamp
     * @return String  形如"20050405"
     */
    public static String getDateString(java.sql.Timestamp tamp)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(tamp);
    }

    /**
     * 根据输入的java.sql.Timestamp 转换成格式时间字符串
     * @param tamp Timestamp
     * @return String 形如"21:01:23 "
     */
    public static String getTimeString(java.sql.Timestamp tamp)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(tamp);
    }

    /**
     * 根据输入的java.sql.Timestamp 转换成格式日期时间字符串
     * @param tamp Timestamp
     * @return String 形如"20050405210123 "
     */
    public static String getDateTimeString(java.sql.Timestamp tamp)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        return sdf.format(tamp);
    }

    /**
     * 根据输入的java.sql.Timestamp 转换成格式日期时间字符串
     * @param tamp Timestamp
     * @return String 形如"20050405 21:01:23 "
     */
    public static String getTimeStamptring(java.sql.Timestamp tamp)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

        return sdf.format(tamp);
    }

    /**
     * function:将日期按指定格式进行格式化
     * @param date:需要格式化的日期
     * @param pattern:格式化参数
     * @return:格式化后的日期字符串表示
     */
    public static String getFormatDate(java.util.Date date, String pattern)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * function:将当前日期按指定格式进行格式化
     * @param pattern:格式化参数
     * @return:格式化后的当前日期字符串表示
     */
    public static String getFormatDate(String pattern)
    {
        java.util.Date now = new java.util.Date();
        return getFormatDate(now, pattern);
    }

    /**
     * 检查一个字符串是否为日期或时间格式
     * @param dateTime String
     * @param format String
     * @return boolean
     */
    public static boolean isDateTime(String dateTime, String format)
    {
        boolean ret = false;

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            if (null != dateTime) {
                sdf.parse(dateTime);
                ret = true;
            }
        } catch (ParseException ex) {
        }
        return ret;
    }
    /**
     * 获取currDate前n天的日期
     * @param currDate 格式"yyyy-MM-dd"
     * @return
     * @throws AppException
     */
    public static String getPreDay(String currDate,int n) throws Exception
    {
        GregorianCalendar gc =new GregorianCalendar();
		gc.setTime(java.sql.Date.valueOf(currDate));
		gc.add(5, -n);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String tempDate = format.format(gc.getTime());
    	return tempDate;
    }
  
    
}
