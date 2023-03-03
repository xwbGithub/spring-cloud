package org.xwb.springcloud.utils;


import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @description 日期工具类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static final String FULL_TIME_PATTERN = "yyyyMMddHHmmss";

    public static String formatFullTime2(Date localDateTime) {
        if (localDateTime == null) {
            return "";
        }
        try {
            SimpleDateFormat datetimeFormat2 = new SimpleDateFormat(FULL_TIME_PATTERN);
            return datetimeFormat2.format(localDateTime);
        } catch (Exception e) {
            return "";
        }
    }

    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

	public static String YYYY_MM_DD_SLASH = "yyyy/MM/dd";

    public static String YYYYMMDD = "yyyyMMdd";

    public static String CHINESE_YYYY_MM_DD = "yyyy年MM月dd日";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYYMMDDHHMMSSS = "yyyyMMddHHmmsss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static String[] parsePatterns =
            {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss",
                    "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static String getDateMonth() {
        return dateTimeNow(YYYY_MM);
    }


    public static final String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(String s, Object str) throws ParseException {
        if (str == null) {
            return null;
        }
        return parseDate(str.toString(), parsePatterns);
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    public static int getTimeDifference(Date first, Date second) {
        return (int) ((first.getTime() - second.getTime()) / (1000 * 3600 * 24));
    }

    /**
     * @Description: 根据当前时间获取优惠券标识
     * @Date: 2022/3/15 14:47
     * @return: java.lang.String
     **/
    public static final String getCouponCollectionNum() {
        return getCollectionNum("YHQ");
    }

    /**
     * @Description: 根据当前时间获取开票编号
     * @Date: 2022/3/15 14:47
     * @return: java.lang.String
     **/
    public static final String getInvoiceCollectionNum() {
        return getCollectionNum("KP");
    }

    /**
     * @Description: 根据当前时间获取收款编号
     * @Date: 2022/3/4 17:00
     * @return: java.lang.String
     **/
    public static final String getSKCollectionNum() {
        return getCollectionNum("SK");
    }

    /**
     * @Description: 根据当前时间获取付款编号
     * @Date: 2022/3/4 17:00
     * @return: java.lang.String
     **/
    public static final String getFKCollectionNum() {
        return getCollectionNum("FK");
    }

    /**
     * 根据当前时间获取记账编号
     *
     * @return 记账编号
     */
    public static String getJZCollectionNum() {
        return getCollectionNum("JZ");
    }

    /**
     * @Description: 根据当前时间获取汽配进货单标识
     * @Date: 2022/7/6 15:40
     * @return: java.lang.String
     **/
    public static final String getAutoPartsJHDOrderCollectionNum() {
        return getCollectionNum("QPJHD");
    }

    /**
     * @Description: 根据当前时间获取汽配商户预付充值标识
     * @Date: 2022/7/13 10:07
     * @return: java.lang.String
     **/
    public static final String getAutoPartsCZOrderCollectionNum() {
        return getCollectionNum("QPYFCZ");
    }

	/**
	 * @Description: 根据当前时间获取汽配司机下单标识
	 * @Date: 2022/7/19 15:33
	 * @return: java.lang.String
	 **/
	public static final String getAutoPartsXDOrderCollectionNum() {
		return getCollectionNum("QPXD");
	}

	/**
	 * @Description: 根据当前时间获取客户计划标识
	 * @Date: 2022/8/23 15:39
	 * @return: java.lang.String
	 **/
	public static final String getCustomerPlanCollectionNum() {
		return getCollectionNum("JH");
	}

    /**
     * @Description: 根据名称生成编号
     * @Date: 2022/3/4 17:00
     * @return: java.lang.String
     **/
    public static final String getCollectionNum(String name) {
        return name + DateUtils.parseDateToStr(YYYYMMDDHHMMSSS,
                new Date()) + (int) (Math.random() * (999 - 100 + 1) + 100);
    }

    /**
     * @param date: 请求时间
     * @Description: 获取请求时间月份是否小于当前时间月份
     * @Date: 2022/3/24 9:27
     * @return: java.lang.String
     **/
    public static final Boolean checkMonthSize(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int reqMonth = cal.get(Calendar.MONTH) + 1;
        cal.setTime(new Date());
        int month = cal.get(Calendar.MONTH) + 1;
        return reqMonth < month;
    }

    /**
     * @param oldDate:
     * @param newDate:
     * @Description: 比较时间大小
     * @Date: 2022/3/24 10:06
     * @return: java.lang.Boolean
     **/
    public static final Boolean compareTime(Date oldDate, Date newDate) {
        return oldDate.getTime() > newDate.getTime();
    }

    /**
     * @param date: 日期
     * @Description: 获取日期下一年今天的时间
     * @Date: 2022/3/24 10:06
     * @return: java.lang.Boolean
     **/
    public static final Date nextYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        return calendar.getTime();
    }

    /**
     * @param date: 日期
     * @Description: 获取日期下一月今天的时间
     * @Date: 2022/3/24 10:06
     * @return: java.lang.Boolean
     **/
    public static final Date nextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * @param date: 日期
     * @Description: 获取日期下一月今天的时间
     * @Date: 2022/3/24 10:06
     * @return: java.lang.Boolean
     **/
    public static final Date nextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * @param date: 日期
     * @Description: 获取日期下一个时长年份今天的时间
     * @Date: 2022/3/24 10:06
     * @return: java.lang.Boolean
     **/
    public static final Date durationYear(Date date, Integer duration) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, duration);
        return calendar.getTime();
    }

    /**
     * @param date: 日期
     * @Description: 获取日期下一个时长月份今天的时间
     * @Date: 2022/3/24 10:06
     * @return: java.lang.Boolean
     **/
    public static final Date durationMonth(Date date, Integer duration) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, duration);
        return calendar.getTime();
    }


    /**
     * @param date: 日期
     * @Description: 获取日期下一个时长今天的时间
     * @Date: 2022/3/24 10:06
     * @return: java.lang.Boolean
     **/
    public static final Date durationDay(Date date, Integer duration) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, duration);
        return calendar.getTime();
    }

    /**
     * 在日期上增加日
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }


    /**
     * 使用参数Format格式化Date成字符串
     */
    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    //把日期从字符弄转成日期型
    public static Date convertStringToDate(String pattern, String strDate) throws ParseException {
        return parse(strDate, pattern);
    }

    public static Date parse(String strDate, String pattern) throws ParseException {
        return new SimpleDateFormat(pattern).parse(strDate);
    }

    public static String daysBetween(Date startDate, Date endDate) {
        float d = endDate.getTime() - startDate.getTime();
        float dd = d / 86400000f;
        int ddd = (int) dd;

        float hh = (dd - ddd) * 24;
        int hhh = (int) hh;

        float mm = (hh - hhh) * 60;
        int mmm = (int) mm;

        float ss = (mm - mmm) * 60;
        int sss = (int) ss;
        return ddd + "天" + hhh + "小时" + mmm + "分" + sss + "秒";
    }

    /**
     * 使用参数Format格式化Date成字符串
     */
    public static Date parseDateToDate(String format, Date date) {
        try {
            return parse(parseDateToStr(format, date), format);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回增加后的时间
     *
     * @param date    需要添加的原始时间
     * @param addTime 增加的时间(增加多少分钟)
     * @return 返回增加后的时间
     */
    public static Date addHourOfDate(Date date, int addTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, addTime);
        return c.getTime();
    }

    /**
     * @param beforeDate: 之前日期
     * @param afterDate:  之后日期
     * @Description: 获取两个时间差额天数
     * @Date: 2022/5/9 15:34
     * @return: java.lang.Long
     **/
    public static Long getCompareDateDay(Date beforeDate, Date afterDate) {
        long beforeTime = beforeDate.getTime();
        long afterTime = afterDate.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    public static Date getDateByDateformat(String dateStr) {
        SimpleDateFormat sf = new SimpleDateFormat(YYYY_MM_DD);
        try {
            return sf.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
	 * 获取指定时间段内的每一天（字符串日期）
	 *
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return 字符串结果集
	 **/
	public static List<String> getBetweenDateEveryDayList(String start,String end) {
		return getBetweenDateEveryDayList(getDateByDateformat(start),getDateByDateformat(end));
	}

	/**
	 * 获取指定时间段内的每一天（字符串日期）
	 *
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return date结果集
	 **/
	public static List<Date> getBetweenDateEveryDayDateList(String start,String end) {
		List<String> dayList = getBetweenDateEveryDayList(getDateByDateformat(start), getDateByDateformat(end));
		List<Date> dateList = new ArrayList<>(dayList.size());
		dayList.forEach(day -> dateList.add(getDateByDateformat(day)));
		return dateList;
	}

	/**
	 * 获取指定时间段内的每一天
	 *
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return 字符串结果集
	 **/
	public static List<String> getBetweenDateEveryDayList(Date start,Date end) {
		List<String> results = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		while (calendar.getTime().compareTo(end) <= 0) {
			results.add(format(calendar.getTime(), YYYY_MM_DD));
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		return results;
	}
}
