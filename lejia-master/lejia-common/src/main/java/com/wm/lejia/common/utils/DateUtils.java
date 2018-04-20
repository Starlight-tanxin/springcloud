package com.wm.lejia.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class DateUtils {

	/** 日期格式1 yyyy-MM-dd */
	public static final String FORMAT_1 = "yyyy-MM-dd";
	/** 日期格式2 yyyy/MM/dd */
	public static final String FORMAT_2 = "yyyy/MM/dd";
	/** 日期格式3 yyyy-MM-dd HH:mm:ss */
	public static final String FORMAT_3 = "yyyy-MM-dd HH:mm:ss";
	/** 日期格式4 yyyy/MM/dd HH:mm:ss */
	public static final String FORMAT_4 = "yyyy/MM/dd HH:mm:ss";
	/** 日期格式5 yyyyMMdd */
	public static final String FORMAT_5 = "yyyyMMdd";
	/** 日期格式6 yyyyMMddHHmmss */
	public static final String FORMAT_6 = "yyyyMMddHHmmss";
	/** 日期格式7 HH:mm:ss */
	public static final String FORMAT_7 = "HH:mm:ss";

	public static Date getDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s = sdf.format(date);
		Date data = null;
		try {
			data = sdf.parse(s);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * 格式化日期成str
	 * 
	 * @author tanxin
	 * @param date
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String dateString = sdf.format(date);
		return dateString;
	}

	/**
	 * 获取本月开始时间
	 * 
	 * @author tanxin
	 * @param date
	 *            yyyy-MM-dd
	 * @return yyyy-MM-dd
	 */
	public static String getDateStr(String date) {
		String reg = "^\\d{4}\\D+\\d{2}\\D+\\d{2}$";
		boolean b = Pattern.compile(reg).matcher(date).matches();
		if (b) {
			String newDate = date.substring(0, date.length() - 2);
			return newDate + "01";
		}
		return null;
	}

	/**
	 * 获取时间戳
	 * 
	 * @author tanxin
	 * @return
	 */
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

	/**
	 * 获取时间 Date-->String yyyyMMddHHmmss
	 * 
	 * @author tanxin
	 * @return
	 */
	public static String getDateTimeLong(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.FORMAT_6);
		String dateString = sdf.format(date);
		return dateString;
	}

	/**
	 * 获取时间 Date-->String yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateTimeLo(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.FORMAT_3);
		String dateString = sdf.format(date);
		return dateString;
	}

	/**
	 * 获取时间 Date-->String yyyy-MM-dd
	 */
	public static String getDateStr(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(DateUtils.FORMAT_1);
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 字符串转date
	 * 
	 * @author tanxin
	 * @param dateStr
	 *            字符串
	 * @param format
	 *            时间格式
	 * @return
	 */
	public static Date getDate(String dateStr, String format) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获得开始时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayStartTime(Date date) {
		Date start = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.FORMAT_1);
			SimpleDateFormat formater = new SimpleDateFormat(DateUtils.FORMAT_3);
			start = formater.parse(sdf.format(date) + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return start;
	}

	/**
	 * 获得结束时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayEndTime(Date date) {
		Date end = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.FORMAT_1);
			SimpleDateFormat formater = new SimpleDateFormat(DateUtils.FORMAT_3);
			end = formater.parse(sdf.format(date) + " 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return end;
	}

	/**
	 * 获取当前时间后面几天的时间
	 * 
	 * @author tanxin
	 * @param date
	 * @param days
	 *            后面第几天
	 * @return
	 */
	public static Date getDaysAfterDate(Date date, Integer days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, days);
		Date afterDate = c.getTime();
		return afterDate;
	}

	/**
	 * 获取当前时间后几分钟的时间
	 * 
	 * @author tanxin
	 * @param date
	 * @param mins
	 *            后面几分钟
	 * @return
	 */
	public static Date getMinAfterDate(Date date, Integer mins) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, mins);
		Date afterDate = c.getTime();
		return afterDate;
	}

	/**
	 * 获取当前时间后几个月的时间
	 * 
	 * @author tanxin
	 * @param date
	 * @param month
	 *            后面几个月
	 * @return
	 */
	public static Date getMonthAfterDate(Date date, Integer month) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, month);
		Date afterDate = c.getTime();
		return afterDate;
	}

	public static String getStartDateStr(String dateStr) {
		return dateStr + " 00:00:00";
	}

	public static String getEndDateStr(String dateStr) {
		return dateStr + " 23:59:59";
	}

	static Map<String, String> dateRegFormat = new HashMap<String, String>();

	static {
		dateRegFormat.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$",
				"yyyy-MM-dd-HH-mm-ss");// 2014年3月12日 13时5分34秒，2014-03-12 //
										// 12:05:34，2014/3/12 12:5:34
		dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd-HH-mm");// 2014-03-12
		dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd-HH");// 2014-03-12
		dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd");// 2014-03-12
		dateRegFormat.put("^\\d{4}\\D+\\d{2}$", "yyyy-MM");// 2014-03
		dateRegFormat.put("^\\d{4}$", "yyyy");// 2014
		dateRegFormat.put("^\\d{14}$", "yyyyMMddHHmmss");// 20140312120534
		dateRegFormat.put("^\\d{12}$", "yyyyMMddHHmm");// 201403121205
		dateRegFormat.put("^\\d{10}$", "yyyyMMddHH");// 2014031212
		dateRegFormat.put("^\\d{8}$", "yyyyMMdd");// 20140312
		dateRegFormat.put("^\\d{6}$", "yyyyMM");// 201403
		dateRegFormat.put("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$", "yyyy-MM-dd-HH-mm-ss");// 13:05:34
		dateRegFormat.put("^\\d{2}\\s*:\\s*\\d{2}$", "yyyy-MM-dd-HH-mm");// 13:05
		dateRegFormat.put("^\\d{2}\\D+\\d{1,2}\\D+\\d{1,2}$", "yy-MM-dd");// 14.10.18(年.月.日)
		dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}$", "yyyy-dd-MM");// 30.12(日.月)
		dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}\\D+\\d{4}$", "dd-MM-yyyy");// 12.21.2013(日.月.年)
	}

	@SuppressWarnings("finally")
	public static String FormatDate(String dateStr) {
		String curDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat formatter2;
		String dateReplace;
		String strSuccess = "";
		try {
			for (String key : dateRegFormat.keySet()) {
				if (Pattern.compile(key).matcher(dateStr).matches()) {
					formatter2 = new SimpleDateFormat(dateRegFormat.get(key));
					if (key.equals("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$") || key.equals("^\\d{2}\\s*:\\s*\\d{2}$")) {// 13:05:34//
																														// 或
																														// 13:05
																														// 拼接当前日期
						dateStr = curDate + "-" + dateStr;
					} else if (key.equals("^\\d{1,2}\\D+\\d{1,2}$")) {// 21.1//
																		// (日.月)
																		// //
																		// 拼接当前年份
						dateStr = curDate.substring(0, 4) + "-" + dateStr;
					}
					dateReplace = dateStr.replaceAll("\\D+", "-");
					strSuccess = formatter1.format(formatter2.parse(dateReplace));
					break;
				}
			}
		} catch (Exception e) {
			System.err.println("-----------------日期格式无效:" + dateStr);
			throw new Exception("日期格式无效");
		} finally {
			return strSuccess;
		}
	}

	/**
	 * jdk 8 获取时间localDate
	 * 
	 * @author tanxin
	 * @param dateStr
	 *            yyyy-MM-dd
	 * @return
	 */
	public static LocalDate getLocalDate(String dateStr) {
		String str[] = dateStr.split("-");
		int year = Integer.parseInt(str[0].trim());
		int month = Integer.parseInt(str[1].trim());
		int dayOfMonth = Integer.parseInt(str[2].trim());
		LocalDate toDate = LocalDate.of(year, month, dayOfMonth);
		return toDate;
	}

	/**
	 * 将毫秒数转换为时间
	 * 
	 * @author tanxin
	 * @param time
	 * @return
	 */
	public static Date Long2Date(long time) {
		Date date = new Date();
		date.setTime(time);
		return date;
	}

	public static void main(String[] args) {
		String[] dateStrArray = new String[] { "2014-03-12 12:05:34", "2014-03-12 12:05", "2014-03-12 12", "2014-03-12",
				"2014-03", "2014", "20140312120534", "2014/03/12 12:05:34", "2014/3/12 12:5:34", "2014年3月12日 13时5分34秒",
				"201403121205", "1234567890", "20140312", "201403", "2000 13 33 13 13 13", "30.12.2013", "12.21.2013",
				"21.1", "13:05:34", "12:05", "14.1.8", "14.10.18" };
		for (int i = 0; i < dateStrArray.length; i++) {
			System.out.println(
					dateStrArray[i] + "------------------------------".substring(1, 30 - dateStrArray[i].length())
							+ FormatDate(dateStrArray[i]));
		}
		
		System.out.println(DateUtils.toDate(42934.4653587963d));
		// System.out.print( DateUtils.getDateStr("2017-02-09"));
		// String to = "2017-11-01";
		// String dateStr[] = to.split("-");
		// int year = Integer.parseInt(dateStr[0].trim());
		// int month = Integer.parseInt(dateStr[1].trim());
		// int dayOfMonth = Integer.parseInt(dateStr[2].trim());
		// LocalDate toDate = LocalDate.of(year, month, dayOfMonth);
		// LocalDate date = toDate.plus(-1, ChronoUnit.DAYS);
		// int len = date.lengthOfMonth();
		// System.out.println(toDate);
		// System.out.println(date);
		// System.out.print(len);

		// Double d = 34500d;
		// Double d2 = 34500.00d;
		// System.out.println(d.equals(d2));
		// System.out.println(DateUtils.Long2Date(1510303330000L));
	}

	public static String getYear(Date date) {
		// Date date = new Date();
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		// System.out.println(yearFormat.format(date));
		return yearFormat.format(date);

	}

	public static String getMonth(Date date) {
		// Date date = new Date();
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		// System.out.println(yearFormat.format(date));
		return monthFormat.format(date);

	}

	public static String getDay(Date date) {
		// Date date = new Date();
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
		// System.out.println(yearFormat.format(date));
		return dayFormat.format(date);

	}

	private static final int SECONDS_PER_MINUTE = 60;
	private static final int MINUTES_PER_HOUR = 60;
	private static final int HOURS_PER_DAY = 24;
	private static final int SECONDS_PER_DAY = (HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE);
	/**
	 * 一天的毫秒数
	 **/
	private static final long DAY_MILLISECONDS = SECONDS_PER_DAY * 1000L;

	/**
	 * 转换方法
	 * 
	 * @parma numberString 要转换的浮点数
	 * @parma format 要获得的格式 例如"hh:mm:ss"
	 **/
	public static Date toDate(double numberString) {
		int wholeDays = (int) Math.floor(numberString);
		int millisecondsInday = (int) ((numberString - wholeDays) * DAY_MILLISECONDS + 0.5);
		Calendar calendar = new GregorianCalendar();
		setCalendar(calendar, wholeDays, millisecondsInday, false);
		return calendar.getTime();

	}

	private static void setCalendar(Calendar calendar, int wholeDays, int millisecondsInDay, boolean use1904windowing) {
		int startYear = 1900;
		int dayAdjust = -1; // Excel thinks 2/29/1900 is a valid date, which it
							// isn't
		if (use1904windowing) {
			startYear = 1904;
			dayAdjust = 1; // 1904 date windowing uses 1/2/1904 as the first day
		} else if (wholeDays < 61) {
			// Date is prior to 3/1/1900, so adjust because Excel thinks
			// 2/29/1900 exists
			// If Excel date == 2/29/1900, will become 3/1/1900 in Java
			// representation
			dayAdjust = 0;
		}
		calendar.set(startYear, 0, wholeDays + dayAdjust, 0, 0, 0);
		calendar.set(GregorianCalendar.MILLISECOND, millisecondsInDay);
	}
	
	

}
