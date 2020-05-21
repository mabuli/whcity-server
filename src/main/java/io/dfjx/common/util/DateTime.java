package io.dfjx.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

public class DateTime {
	Calendar calendar = new GregorianCalendar();

	public static DateTime Now() {
		return new DateTime();
	}

	public int Year() {
		return calendar.get(Calendar.YEAR);
	}

	public int Month() {
		return calendar.get(Calendar.MONTH) + 1;
	}

	public int Day() {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public int Hour() {
		return calendar.get(Calendar.HOUR);
	}

	public int Minute() {
		return calendar.get(Calendar.MINUTE);
	}

	public int Second() {
		return calendar.get(Calendar.SECOND);
	}

	public long Tick() {
		return calendar.getTime().getTime();
	}

	public DateTime addYear(int year) {
		calendar.add(Calendar.YEAR, year);
		return this;
	}

	public DateTime addMonth(int month) {
		calendar.add(Calendar.MONTH, month);
		return this;
	}

	public DateTime addDay(int day) {
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return this;
	}

	public DateTime addHour(int hour) {
		calendar.add(Calendar.HOUR_OF_DAY, hour);
		return this;
	}

	public DateTime addMinute(int min) {
		calendar.add(Calendar.MINUTE, min);
		return this;
	}

	public DateTime addSecond(int sec) {
		calendar.add(Calendar.SECOND, sec);
		return this;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(calendar.getTime());
	}

	public String toString(String formatText) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatText);
		return sdf.format(calendar.getTime());
	}

	@Override
	public int hashCode() {
		return this.calendar.getTime().hashCode();
	}

	@Override
	public boolean equals(Object datetime) {

		if (!validParam(datetime))
			return false;

		return this.calendar.getTime().equals(
				((DateTime) datetime).calendar.getTime());

		// return this.Tick() == ((DateTime) datetime).Tick();
	}

	protected boolean validParam(Object datetime) {
		if (datetime == null) {
			return false;
		}
		if (!(datetime instanceof DateTime)) {
			return false;
		}
		return true;
	}

	//
	public String getString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(calendar.getTime());
	}

	public String getShortDateString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calendar.getTime());
	}

	public String getShortTimeString() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(calendar.getTime());
	}

	public String getString(String formatText) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatText);
		return sdf.format(calendar.getTime());
	}

	public String getString(SimpleDateFormat format) {

		return format.format(calendar.getTime());

	}

	void setCalendar(Calendar cal) {
		this.calendar = cal;
	}

	public void setTime(Date date) {
		this.calendar.setTime(date);
	}


	public Date getTime() {
		return this.calendar.getTime();
	}

	public static DateTime parse(String text) throws java.text.ParseException {
		Date date = parseDate(text);
		DateTime dt = new DateTime();
		dt.setTime(date);

		return dt;
	}

	public static Date parseDate(String text) throws java.text.ParseException {
		if(StringUtil.isNullOrEmpty(text))
			return null;
		if(text.startsWith("15") && (text.length() == 11 || text.length() == 13)){
			return new Date(Long.parseLong(text));
		}
		String format = getDateTimeFormat(text);
		DateFormat sdf = new SimpleDateFormat(format);
		Date date = sdf.parse(text);
		return date;
	}

	public static DateTime tryParse(String text) {
		try {
			DateTime dt = parse(text);
			return dt;
		} catch (Exception ex) {
			System.out.println("[DateTime]无效的日期格式" + text + ",无法转换:"
					+ ex.getMessage());
			return DateTime.Now();
		}
	}

	private static String getDateTimeFormat(String date){
		String format = null;

		String yyyy_MM_dd = "\\d{4}-\\d{2}-\\d{2}";

		String yyyyMMdd = "\\d{4}\\d{2}\\d{2}";

		String yyyy_MM_dd_hh_mm_ss = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
		if (Pattern.matches(yyyy_MM_dd, date)) {
			format = "yyyy-MM-dd";
		}else if(Pattern.matches(yyyyMMdd, date)){
			format = "yyyyMMdd";
		}else if(Pattern.matches(yyyy_MM_dd_hh_mm_ss, date)){
			format = "yyyy-MM-dd HH:mm:ss";
		}else {
			format="yyyy-MM-dd HH:mm:ss.SSS";
		}

		return format;
	}
}
