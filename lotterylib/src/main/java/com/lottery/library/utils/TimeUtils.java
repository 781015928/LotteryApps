package com.lottery.library.utils;


import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils {

    /**
     * 获取起止时间
     */
    public static String getTime(String time, int year) {
        SimpleDateFormat timeFormatFrom = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat timeFormatTo = new SimpleDateFormat("yyyy.MM.dd");
        String timestr = "";
        try {
            Date startDate = timeFormatFrom.parse(time);
            timestr = timeFormatTo.format(startDate);
            timestr += "-";
            startDate.setYear(startDate.getYear() + year);
            timestr += timeFormatTo.format(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return timestr;
        }
        return timestr;
    }

    /**
     * 将毫秒数装换为时分字符串
     *
     * @param currentTimeMillisecond 剩余毫秒数
     * @return
     */
    public static String getCurrentRemainingTime(long currentTimeMillisecond) {
        String remainingTime = null;
        //转换为时分
        long hours = (currentTimeMillisecond % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (currentTimeMillisecond % (1000 * 60 * 60)) / (1000 * 60);
        remainingTime = hours + "时" + minutes + "分";
        if (currentTimeMillisecond <= 0) {
            remainingTime = null;
        }
        return remainingTime;
    }

    /**
     * 获取剩余支付时间
     *
     * @param createtime        订单创建时间
     * @param serverCurrentTime 服务器当前时间
     * @return
     */
    public static long getRemainingTimeMillisecond(String createtime, long serverCurrentTime) {
        long remainingtime = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date createDate = sdf.parse(createtime);
            long createOrderTime = createDate.getTime();
            long oneDayTime = 1000 * 60 * 60 * 24;

            //剩余支付时间 创建订单时间 + 24H - 当前时间
            remainingtime = createOrderTime + oneDayTime - serverCurrentTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return remainingtime;
    }


    /**
     * 获取剩余支付时间 取东八区时间计算
     *
     * @param createtime
     * @return
     */
    public static String getRemainingTime(String createtime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String remainingTime = null;
        try {
            Date date = sdf.parse(createtime);
            long createOrderTime = date.getTime();
            long oneDayTime = 1000 * 60 * 60 * 24;
            SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
            String ee = dff.format(new Date());
            Date nowDate = dff.parse(ee);

            long currentTime = nowDate.getTime();
            //剩余支付时间 创建订单时间 + 24H - 当前时间
            long remainingtime = createOrderTime + oneDayTime - currentTime;

            //转换为时分
            long hours = (remainingtime % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (remainingtime % (1000 * 60 * 60)) / (1000 * 60);
            long seconds = (remainingtime % (1000 * 60)) / 1000;
            remainingTime = hours + "时" + minutes + "分";
            if (remainingtime <= 0) {
                remainingTime = null;
            }
            Date curDate = new Date(currentTime);
            String str = sdf.format(curDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return remainingTime;
    }

    /**
     * 通过开始时间获取倒计时天数
     *
     * @param surplusTimes
     * @return
     */
    public static String getCountDownDay(long surplusTimes) {
        String surplusDays = "";
        //转换为时分
        long days = (surplusTimes / (1000 * 60 * 60 * 24));
        surplusDays = days + "天";

        if (surplusTimes <= 0) {
            surplusDays = "0天";
        }

        return surplusDays;
    }

    /**
     * 获取倒计时的小时数
     *
     * @param surplusTimes
     * @return
     */
    public static String getCountDownHours(long surplusTimes) {
        String surplusHours = "";
        long minutes = (surplusTimes % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        if (minutes < 10) {
            surplusHours = "0" + minutes;
        } else {
            surplusHours = minutes + "";
        }
        if (surplusTimes <= 0) {
            surplusHours = "00";
        }
        return surplusHours;
    }

    /**
     * 获取倒计时的分钟数
     *
     * @param surplusTimes
     * @return
     */
    public static String getCountDownMinutes(long surplusTimes) {
        String surplusMinutes = "";
        long hours = (surplusTimes % (1000 * 60 * 60)) / (1000 * 60);
        if (hours < 10) {
            surplusMinutes = "0" + hours;
        } else {
            surplusMinutes = hours + "";
        }

        if (surplusTimes <= 0) {
            surplusMinutes = "00";
        }
        return surplusMinutes;
    }

    /**
     * 获取倒计时的秒数
     *
     * @param surplusTimes
     * @return
     */
    public static String getCountDownSeconds(long surplusTimes) {
        String surplusSeconds = "";
        long minutes = (surplusTimes % (1000 * 60)) / 1000;
        if (minutes < 10) {
            surplusSeconds = "0" + minutes;
        } else {
            surplusSeconds = minutes + "";
        }
        if (surplusTimes <= 0) {
            surplusSeconds = "00";
        }
        return surplusSeconds;
    }


    /**
     * 通过开始时间计算倒计时毫秒数
     *
     * @param startTime
     * @return
     */
    public static long getCountDownTotalMillisecond(String startTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long remainingtime = 0;
        try {
            Date date = sdf.parse(startTime);
            long createOrderTime = date.getTime();
            SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
            String ee = dff.format(new Date());
            Date nowDate = dff.parse(ee);
            long currentTime = nowDate.getTime();
            //剩余支付时间 开始时间  -  当前时间
            remainingtime = createOrderTime - currentTime;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return remainingtime;
    }

    public static String timeFormat(String time, String format) {//"yyyy/MM/dd"
        try {
            if(TextUtils.isEmpty(time)) {
                return "";
            }
            SimpleDateFormat sdfold = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdfnew = new SimpleDateFormat(format);
            Date date = sdfold.parse(time);
            return sdfnew.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

    }

    public static String durationFormat(String time, String format) {//"yyyy/MM/dd"
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        try {
            time = time.trim();
            SimpleDateFormat sdfold = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat sdfnew = new SimpleDateFormat(format);
            Date date = sdfold.parse(time);
            return sdfnew.format(date);
        } catch (Exception e) {
            SimpleDateFormat sdfold = new SimpleDateFormat("mm:ss");
            SimpleDateFormat sdfnew = new SimpleDateFormat(format);
            Date date = null;
            try {
                date = sdfold.parse(time);
                return sdfnew.format(date);
            } catch (Exception e1) {
                return "";
            }


        }

    }

    /**
     * @param ms
     * @return 00:00:00
     */
    public static String timeFormat(long ms) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();

        if (hour > 0) {
            if (hour < 10) {
                sb.append("0");
            }
            sb.append(hour + ":");
        } else {

            sb.append("00:");

        }
        if (minute > 0) {
            if (minute < 10) {
                sb.append("0");
            }
            sb.append(minute + ":");
        } else {
            sb.append("00:");
        }

        if (second > 0) {
            if (second < 10) {
                sb.append("0");
            }
            sb.append(second + "");
        } else {

            sb.append("00");

        }
        if (milliSecond > 0) {
            //  sb.append(milliSecond+"毫秒");
        }
        return sb.toString();
    }

    /**
     * @param ms
     * @return 00:00
     */
    public static String timeFormat2(long ms) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();

        if (hour > 0) {
            if (hour < 10) {
                //sb.append("0");
            }
            //  sb.append(hour+":");
        } else {

            // sb.append("00:");

        }
        if (minute > 0) {
            if (minute < 10) {
                sb.append("0");
            }
            sb.append(minute + ":");
        } else {
            sb.append("00:");
        }

        if (second > 0) {
            if (second < 10) {
                sb.append("0");
            }
            sb.append(second + "");
        } else {

            sb.append("00");

        }
        if (milliSecond > 0) {
            //  sb.append(milliSecond+"毫秒");
        }
        return sb.toString();
    }

    public static long getlongTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = null;
        Date sDate = null;
        try {
            date = format.parse(time);
            sDate = format.parse("00:00:00");
            return (date.getTime() - sDate.getTime());
        } catch (ParseException e) {
            SimpleDateFormat format2 = new SimpleDateFormat("mm:ss");
            try {
                date = format2.parse(time);
                sDate = format2.parse("00:00");
                return (date.getTime() - sDate.getTime());
            } catch (ParseException e1) {
                e1.printStackTrace();
                return 0;
            }
        }

    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */

    public static Date parse(String strDate, String pattern) {

        if (TextUtils.isEmpty(strDate)) {
            return null;
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用用户格式格式化日期
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return
     */

    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }
}
