package com.chuyx.util;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.*;

/**
 * 时间处理工具类
 */
@Slf4j
public class DateUtils {

    /**
     * 获取当前时间戳，11位
     *
     * @return
     */
    public static int currentTimeSeconds() {
        return Integer.valueOf(String.valueOf(System.currentTimeMillis() / 1000));
    }


    /***
     * 在当前日期上加日期
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static long getTodayStartTime2SetTime(int day, int hour, int minute, int second) {
        return getDayStartTime() + (day * 24 * 3600) + (hour * 3600) + (minute * 60) + second;
    }

    /***
     * 在指定日期上加日期
     * @param time
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static long getAssignDayStartTime2SetTime(long time, int day, int hour, int minute, int second) {
        return getAssignDayTime(time) + (day * 24 * 3600) + (hour * 3600) + (minute * 60) + second;
    }

    /**
     * 获取当天的开始时间戳
     */
    public static long getDayStartTime() {
        Calendar todayStart = Calendar.getInstance();

        todayStart.set(Calendar.HOUR_OF_DAY, 0);

        todayStart.set(Calendar.MINUTE, 0);

        todayStart.set(Calendar.SECOND, 0);

        todayStart.set(Calendar.MILLISECOND, 0);

        return todayStart.getTime().getTime() / 1000;
    }

    /**
     * 获取当月开始的时间戳
     *
     * @return
     */
    public static Integer getMonthStartTime(Integer time) {
        Calendar calendar = Calendar.getInstance();
        long longTimeStamp = time * 1000L;
        Date date = new Date(longTimeStamp);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return (int) (calendar.getTimeInMillis() / 1000);
    }


    public static long getDayStartTimeForMicrosecond() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime().getTime();
    }

    /**
     * 获取指定日期的时间戳
     */
    public static long getAssignDayTime(long time) {
        Calendar dayStart = Calendar.getInstance();

        dayStart.setTimeInMillis(time * 1000);

        dayStart.set(Calendar.HOUR_OF_DAY, 0);

        dayStart.set(Calendar.MINUTE, 0);

        dayStart.set(Calendar.SECOND, 0);

        dayStart.set(Calendar.MILLISECOND, 0);

        return dayStart.getTime().getTime() / 1000;
    }

    /**
     * 获取所需多少天前后的时间点，单位：秒。
     *
     * @days 天数。 正数：之后多少天的时间点； 负数：之前多少天的时间点
     */
    public static int getDaysTimeSeconds(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(DAY_OF_YEAR, days);
        cal.set(HOUR_OF_DAY, 0);
        cal.set(MINUTE, 0);
        cal.set(SECOND, 0);
        return (int) (cal.getTimeInMillis() / 1000);
    }

    /**
     * 获取今天是星期几
     *
     * @return
     */
    public static Integer getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(DAY_OF_WEEK) - 1;
    }

    /**
     * 获取指定时间戳是星期几
     *
     * @param time
     * @return
     */
    public static Integer getDayOfWeekByTime(Integer time) {
        Calendar calendar = Calendar.getInstance();
        long longTimeStamp = time * 1000L;
        Date date = new Date(longTimeStamp);
        calendar.setTime(date);
        return calendar.get(DAY_OF_WEEK) - 1;
    }


    /**
     * 获取当前时间的小时
     *
     * @return 小时   0-23
     */
    public static int getHourOfDay() {
        Calendar cal = Calendar.getInstance();
        return cal.get(HOUR_OF_DAY);
    }

    /**
     * 获取n个月前的时间
     *
     * @param time   当前时间戳
     * @param amount 偏移量，-3则向前三个月，3则向后三个月
     * @return 偏移后的时间戳
     */
    public static Integer getTimeByMonthBefore(Integer time, Integer amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000L);
        calendar.add(Calendar.MONTH, amount);
        return (int) (calendar.getTimeInMillis() / 1000);
    }

    /**
     * 获取n个天前的时间
     *
     * @param time   当前时间戳
     * @param amount 偏移量，-3则向前三天，3则向后三天
     * @return 偏移后的时间戳
     */
    public static Integer getTimeByDayBefore(Integer time, Integer amount) {
        return time + (86400 * amount);
    }

    /**
     * 将时间戳转为字符串
     *
     * @param time    时间戳
     * @param pattern 字符串格式
     * @return 字符串
     */
    public static String getDateFormat(Integer time, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date(time * 1000L));
    }

    /**
     * 讲时间戳传为对应日期Date
     *
     * @param time 时间戳
     * @return 时间类型
     */
    public static Date getDateForTime(Integer time) {
        long dateTime = time * 1000L;
        return new Date(dateTime);
    }

    /**
     * 得到两个日期间的天数
     *
     * @param start
     * @param end
     * @return
     */
    public static Integer dateDiff(Integer start, Integer end) {
        long inter = (end - start) * 1000L;
        if (inter < 0) {
            return 0;
        }
        long dateMillSec = 24 * 60 * 60 * 1000L;
        long dateCnt = inter / dateMillSec;
        long remainder = inter % dateMillSec;
        if (remainder != 0) {
            dateCnt++;
        }
        return Integer.parseInt(String.valueOf(dateCnt));
    }

    /**
     * 时间戳转为当日0点时间戳
     *
     * @param time 时间戳
     * @return
     */
    public static Integer getDayStartTime(Integer time) {
        return time - (time + 8 * 3600) % 86400;
    }

    public static String getCurrentDate(String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(new Date());
    }

    public static Date getDateByString(String date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            log.error("DateUtils:{}", Throwables.getStackTraceAsString(e));
            return null;
        }
    }

    /**
     * 获取时间的秒
     *
     * @param date
     * @param dateFormat
     * @return
     */
    public static Integer getCurrByDate(String date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            Date parse = sdf.parse(date);
            return (int) (parse.getTime() / 1000L);
        } catch (ParseException e) {
            log.error("DateUtils:{}", Throwables.getStackTraceAsString(e));
            return null;
        }
    }

    public static int getFirstDayOfMonth(int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        if (year > 0) {
            cal.set(Calendar.YEAR, year);
        }
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (int) (cal.getTimeInMillis() / 1000);
    }

    public static int getLastDayOfMonth(int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        if (year > 0) {
            cal.set(Calendar.YEAR, year);
        }
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int firstDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return (int) (cal.getTimeInMillis() / 1000);
    }

}
