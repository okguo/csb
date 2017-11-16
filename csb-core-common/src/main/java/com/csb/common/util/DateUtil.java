package com.csb.common.util;

/**
 * @author 
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

	public static final int YEAR = 1;
	public static final int MONTH = 2;
	public static final int DAY = 3;
	public static final int HOUR = 4;
	public static final int MINUTE = 5;
	public static final int SECOND = 6;
	private static long nz = 1000 * 24 * 60 * 60 * 7;//一周的毫秒数
	private static long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
	private static long nh = 1000 * 60 * 60;//一小时的毫秒数
	private static long nm = 1000 * 60;//一分钟的毫秒数

	// 获取日期类型
	public static final Map<String, String> DATETYPE = new HashMap<String, String>() {
		{
			put("LAST_DAY", "1");
			put("CUR_DAY", "2");
			put("NEXT_DAY", "3");
			put("LAST_WEEK", "4");
			put("CUR_WEEK", "5");
			put("NEXT_WEEK", "6");
			put("LAST_MONTH", "7");
			put("CUR_MONTH", "8");
			put("NEXT_MONTH", "9");
		}
	};

	/**
	 * 得到当前日期 2008-03-25
	 * 
	 * @return
	 */
	public static String getCurrDate() {

		return getCurrDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前时间 20:12:10
	 * 
	 * @return
	 */
	public static String getCurrTime() {
		return getCurrDate("HH:mm:ss");
	}

	/**
	 * 得到当前日期及时间 2008-03-25 20:12:10
	 * 
	 * @return
	 */
	public static String getCurrDateTime() {
		return getCurrDate("yyyy-MM-dd HH:mm:ss");
	}

	public static String getCurrDateNoSign() {
		return getCurrDate("yyyyMMdd");
	}

	public static String getCurrDateChinese() {
		String currDate = getCurrDateNoSign();
		if (currDate != null && !currDate.equals("")) {
			currDate = currDate.substring(0, 4) + "年"
					+ currDate.substring(4, 6) + "月" + currDate.substring(6, 8)
					+ "日";
		}
		return currDate;
	}

	public static String getCurrTimeNoSign() {
		return getCurrDate("HHmmss");
	}

	public static String getCurrDateTimeNoSign() {
		return getCurrDate("yyyyMMddHHmmss");
	}

	/**
	 * 得到格式化后的日期字符串,默认为格式为：yyyy-MM-dd HH:mm:ss
	 * @param strDate
	 * @return
     */
	public static String getFormatDate(String strDate) {
		if (strDate == null || strDate.equals(""))
			return "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date = null;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			return "";
		}

		if (date == null)
			return "";
		else
			return sdf.format(date);
	}

	/**
	 * 得到当前日期字符串,默认为格式为：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateFormat
	 * @return
	 */
	public static String getCurrDate(String dateFormat) {
		if (dateFormat == null)
			dateFormat = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Calendar now = Calendar.getInstance();
		return sdf.format(now.getTime());
	}

	/**
	 * 获取昨天的dateFormat格式日期
	 */
	public static String getYesterDay(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return new SimpleDateFormat(dateFormat).format(cal.getTime());

	}

	/**
	 * 获取上个月的dateFormat格式日期
	 */
	public static String getLastMonth(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return new SimpleDateFormat(dateFormat).format(cal.getTime());

	}

	/**
	 * 得到指定日期N天后的日期字符串 DateUtil.getAddDate(DateUtil.DAY,"2008-04-10",1)
	 * 
	 * @param addType
	 * @param dateStr
	 * @param n
	 * @return
	 * @throws ParseException
	 */
	public static String getAddDate(int addType, String dateStr, int n)
			throws ParseException {
		int dateType = 0;
		if (addType == YEAR)
			dateType = Calendar.YEAR;
		if (addType == MONTH)
			dateType = Calendar.MONTH;
		if (addType == DAY)
			dateType = Calendar.DATE;
		if (dateType == 0)
			return "";
		Calendar now = Calendar.getInstance();
		now.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateStr));
		now.add(dateType, n);
		return (new SimpleDateFormat("yyyy-MM-dd").format(now.getTime()));
	}

	/**
	 * 得到指定日期N天后的日期字符串 DateUtil.getAddDate(DateUtil.DAY,"2008-04-10",1)
	 * 
	 * @param addType
	 * @param dateStr
	 * @param n
	 * @return
	 * @throws ParseException
	 */
	public static String getAddDate(int addType, String dateStr,
			String dateFormat, int n) throws ParseException {
		int dateType = 0;
		if (addType == YEAR)
			dateType = Calendar.YEAR;
		if (addType == MONTH)
			dateType = Calendar.MONTH;
		if (addType == DAY)
			dateType = Calendar.DATE;
		if (dateType == 0)
			return "";
		Calendar now = Calendar.getInstance();
		now.setTime(new SimpleDateFormat(dateFormat).parse(dateStr));
		now.add(dateType, n);
		return (new SimpleDateFormat(dateFormat).format(now.getTime()));
	}

	/**
	 * 得到当前日期N天后的日期字符串
	 * 
	 * @param addType
	 * @param n
	 * @return
	 * @throws Exception
	 */
	public static String getAddDateOnCurr(int addType, int n) throws Exception {
		int dateType = 0;
		if (addType == YEAR)
			dateType = Calendar.YEAR;
		if (addType == MONTH)
			dateType = Calendar.MONTH;
		if (addType == DAY)
			dateType = Calendar.DATE;
		if (dateType == 0)
			return "";
		Calendar now = Calendar.getInstance();
		now.add(dateType, n);
		return (new SimpleDateFormat("yyyy-MM-dd").format(now.getTime()));
	}

	/**
	 * 得到当前月N月后的日期字符串（年也可）----仅限月或年
	 * 
	 * @param addType
	 * @param n
	 * @return
	 * @throws Exception
	 */
	public static String getAddMonthOnCurr(int addType, int n) throws Exception {
		int dateType = 0;
		if (addType == YEAR)
			dateType = Calendar.YEAR;
		if (addType == MONTH)
			dateType = Calendar.MONTH;
		if (dateType == 0)
			return "";
		Calendar now = Calendar.getInstance();
		now.add(dateType, n);
		return (new SimpleDateFormat("yyyyMM").format(now.getTime()));
	}

	/**
	 * 得到指定日期时间N单位后的日期时间字符串
	 * @param addType
	 * @param dateTimeStr
	 * @param n
	 * @return
	 * @throws ParseException
     */
	public static String getAddDateTime(int addType, String dateTimeStr, int n)
			throws ParseException {
		int dateType = 0;
		if (addType == YEAR)
			dateType = Calendar.YEAR;
		if (addType == MONTH)
			dateType = Calendar.MONTH;
		if (addType == DAY)
			dateType = Calendar.DATE;
		if (addType == HOUR)
			dateType = Calendar.HOUR;
		if (addType == MINUTE)
			dateType = Calendar.MINUTE;
		if (addType == SECOND)
			dateType = Calendar.SECOND;
		if (dateType == 0)
			return "";
		String dateFormatStr = "yyyy-MM-dd HH:mm:ss";
		Calendar now = Calendar.getInstance();
		now.setTime(new SimpleDateFormat(dateFormatStr).parse(dateTimeStr));
		now.add(dateType, n);
		return (new SimpleDateFormat(dateFormatStr).format(now.getTime()));
	}

	/**
	 * 判断日期时间dateTime1是否在期时间dateTime2之前,日期时间格式 2008-4-10 16:16:34
	 * 
	 * @param dateTime1
	 * @param dateTime2
	 * @return
	 * @throws Exception
	 */
	public static boolean isDateTimeBefore(String dateTime1, String dateTime2)
			throws Exception {
		DateFormat df = DateFormat.getDateTimeInstance();
		return df.parse(dateTime1).before(df.parse(dateTime2));
	}

	/**
	 * 判断日期date1是否在时间date2之前,日期格式 2008-4-10
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	public static boolean isDateBefore(String date1, String date2)
			throws Exception {
		DateFormat df = DateFormat.getDateInstance();
		return df.parse(date1).before(df.parse(date2));
	}

	/**
	 * 判断pTime日期是星期几<br>
	 * <br>
	 * 
	 * @param pTime
	 *            修要判断的时间<br>
	 * @return dayForWeek 判断结果<br>
	 * @Exception 发生异常<br>
	 */
	public static int dayForWeek(String pTime) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(pTime));
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	/**
	 * 按指定格式转换输出指定的日期
	 * 
	 * @param date
	 *            要输出的日期
	 * @param datePattern
	 *            要输出的时间格式
	 * @return 格式化后的字符串
	 */
	public static String getTime(String date, String datePattern)
			throws Exception {
		String retValue = null;
		SimpleDateFormat sf = new SimpleDateFormat(datePattern);
		Date fromDate = sf.parse(date);
		retValue = sf.format(fromDate);
		return retValue;
	}
	
	/**
	 * 按指定格式将Unix时间戳转换输出指定的日期
	 * 
	 * @param timestampString
	 *            要输出的日期
	 * @param timestampString
	 *            要输出的时间格式
	 * @return 格式化后的字符串
	 */
	public static String TimeStamp2Date(String timestampString, String formats){    
		  Long timestamp = Long.parseLong(timestampString)*1000;    
		  String date = new SimpleDateFormat(formats).format(new Date(timestamp));
		  return date;    
	} 
	/**
	 * 按指定格式转换输出指定的日期
	 * 
	 * @param date
	 *            要输出的日期
	 * @param datePattern
	 *            要输出的时间格式
	 * @return 格式化后的字符串
	 */
	public static String getTime(Date date, String datePattern) {
		String retValue = null;
		SimpleDateFormat sf = new SimpleDateFormat(datePattern);
		retValue = sf.format(date);
		return retValue;
	}

	public static long getTimeMillis(String dateStr,String datePattern){
		long timestamp = 0L;
		try{
			DateFormat df = new SimpleDateFormat(datePattern);
			Date date = df.parse(dateStr);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			timestamp = cal.getTimeInMillis();
		}catch (Exception e){
			e.printStackTrace();
		}
		return timestamp;
	}

	public static long getTimeSeconds(String dateStr,String datePattern){
		return getTimeMillis(dateStr,datePattern) / 1000;
	}

	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis() / 1000;
	}

	public static String getDateBySeconds(long seconds, String datePattern) {
		Date d = new Date(seconds * 1000);
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		return sdf.format(d);
	}

	// 获得当前日期与本周日相差的天数
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	// 获得本周一的日期
	public static String getMondayOFWeek() {
		int weeks = 0;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}

	// 获得本周星期日的日期
	public static String getCurrentWeekday() {
		int weeks = 0;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}

	// 获取当月第一天
	public static String getFirstDayOfMonth() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 计算当月最后一天,返回字符串
	public static String getDefaultDay() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天

		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 上月第一天
	public static String getPreviousMonthFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号
		// lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天

		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获得上月最后一天的日期
	public static String getPreviousMonthEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, -1);// 减一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获得下个月第一天的日期
	public String getNextMonthFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1);// 减一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获得下个月最后一天的日期
	public String getNextMonthEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1);// 加一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获得本年第一天的日期
	public static String getCurrentYearFirst() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		return years + "0101";
	}

	// 获得本年最后一天
	public static String getCurrentYearEnd() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		return years + "1231";
	}

	// 获得去年第一天 G
	public static String getPreviousYearFirst() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);
		years_value--;
		return years_value + "0101";
	}

	// 获得去年最后一天 G
	public static String getPreviousYearEnd() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);
		years_value--;
		return years_value + "1231";
	}

	// 获得本季度
	public static String getThisSeasonTime(int month) {
		int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		int season = 1;
		if (month >= 1 && month <= 3) {
			season = 1;
		}
		if (month >= 4 && month <= 6) {
			season = 2;
		}
		if (month >= 7 && month <= 9) {
			season = 3;
		}
		if (month >= 10 && month <= 12) {
			season = 4;
		}
		int start_month = array[season - 1][0];
		int end_month = array[season - 1][2];

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);

		int start_days = 1;// years+"-"+String.valueOf(start_month)+"-1";//getLastDayOfMonth(years_value,start_month);
		int end_days = getLastDayOfMonth(years_value, end_month);
		String seasonDate = years_value + "-" + start_month + "-" + start_days
				+ ";" + years_value + "-" + end_month + "-" + end_days;
		return seasonDate;

	}

	/**
	 * 获取某年某月的最后一天
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 最后一天
	 */
	private static int getLastDayOfMonth(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			return 31;
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		}
		if (month == 2) {
			if (isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		}
		return 0;
	}

	/**
	 * 是否闰年
	 * 
	 * @param year
	 *            年
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	public static void main(String args[]) throws Exception{
		String date = "2016-06-17 08:01:01";
		long s = getTimeSeconds(date,"yyyy-MM-dd HH:mm:ss");
		System.out.println(s);
	}

	/**
	 * 计算两个时间相差（天、小时、分钟、秒）
	 * @param startTime
	 * @param endTime
	 * @param format
	 * @param addType
	 * @return
	 */
	public static long dateDiff(String startTime, String endTime,
			String format, int addType) {
		long r = 0L;
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		// 获得两个时间的毫秒时间差异
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			switch(addType){
			case DAY : r = diff / nd;break;// 计算差多少天
			case HOUR : r = diff % nd / nh + day * 24;break;// 计算差多少小时
			case MINUTE : r = diff % nd % nh / nm + day * 24 * 60;break;// 计算差多少分钟
			case SECOND : r = diff % nd % nh % nm / ns;break;// 计算差多少秒
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0L;
		}
		return r;
	}

	/**
	 * 计算时间差
	 *
	 * @param create_time nowtime
	 * @return
	 */
	public static String getTimeLag(long create_time, long nowtime) {
		SimpleDateFormat format = new SimpleDateFormat("MM-dd");
		String back = null;
		long diff = (nowtime - create_time);//时间差毫秒数
		long week = diff / nz;//计算差多少周
		long day = diff / nd;//计算差多少天
		long hour = diff % nd / nh;//计算差多少小时
		long min = diff % nd % nh / nm;//计算差多少分钟
		if (week != 0) {//大于一周返回创建月日
			Date datecreateTimeStr = new Date(create_time);
			back = format.format(datecreateTimeStr);
		} else if (day != 0) {//大于一天显示几天前
			back = String.valueOf(day) + "天前";
		} else if (hour != 0) {//大于一小时
			back = String.valueOf(hour) + "小时前";//+String.valueOf(min)+"分钟前";
		} else if (min != 0) {
			back = String.valueOf(min) + "分钟前";
		} else {
			back = "刚刚";
		}
		return back;
	}
}