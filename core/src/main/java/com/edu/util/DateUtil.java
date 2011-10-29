package com.edu.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import com.edu.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Date Utility Class used to convert Strings to Dates and Timestamps
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *         Modified by <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 *         to correct time pattern. Minutes should be mm not MM (MM is month).
 */
public final class DateUtil {
	private static Log log = LogFactory.getLog(DateUtil.class);
	public static final String TIME_PATTERN = "HH:mm";
	public static final int DURATION_NO_REPEAT = 0;
	public static final int DURATION_DAYLY = 1;
	public static final int DURATION_WEEKLY = 2;
	public static final int DURATION_BI_WEEKLY = 3;
	public static final int DURATION_MONTHLY = 4;

	/**
	 * Checkstyle rule: utility classes should not have public constructor
	 */
	private DateUtil() {
	}

	/**
	 * Return default datePattern (MM/dd/yyyy)
	 *
	 * @return a string representing the date pattern on the UI
	 */
	public static String getDatePattern() {
		Locale locale = LocaleContextHolder.getLocale();
		String defaultDatePattern;
		try {
			defaultDatePattern = ResourceBundle.getBundle(Constants.BUNDLE_KEY,
					locale).getString("date.format");
		} catch (MissingResourceException mse) {
			defaultDatePattern = "MM/dd/yyyy";
		}

		return defaultDatePattern;
	}

	public static String getDateTimePattern() {
		return DateUtil.getDatePattern() + " HH:mm:ss.S";
	}

	/**
	 * This method attempts to convert an Oracle-formatted date
	 * in the form dd-MMM-yyyy to mm/dd/yyyy.
	 *
	 * @param aDate date from database as a string
	 * @return formatted string for the ui
	 */
	public static String getDate(Date aDate) {
		SimpleDateFormat df;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date/time
	 * in the format you specify on input
	 *
	 * @param aMask the date pattern the string is in
	 * @param strDate a string representation of a date
	 * @return a converted Date object
	 * @throws ParseException when String doesn't match the expected format
	 * @see java.text.SimpleDateFormat
	 */
	public static Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
		SimpleDateFormat df;
		Date date;
		df = new SimpleDateFormat(aMask);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '"
					+ aMask + "'");
		}

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			//log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	/**
	 * This method returns the current date time in the format:
	 * MM/dd/yyyy HH:MM a
	 *
	 * @param theTime the current time
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(TIME_PATTERN, theTime);
	}

	/**
	 * This method returns the current date in the format: MM/dd/yyyy
	 *
	 * @return the current date
	 * @throws ParseException when String doesn't match the expected format
	 */
	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * This method generates a string representation of a date's date/time
	 * in the format you specify on input
	 *
	 * @param aMask the date pattern the string is in
	 * @param aDate a date object
	 * @return a formatted string representation of the date
	 * @see java.text.SimpleDateFormat
	 */
	public static String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			log.warn("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date based
	 * on the System Property 'dateFormat'
	 * in the format you specify on input
	 *
	 * @param aDate A date to convert
	 * @return a string representation of the date
	 */
	public static String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	/**
	 * This method converts a String to a date using the datePattern
	 *
	 * @param strDate the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 * @throws ParseException when String doesn't match the expected format
	 */
	public static Date convertStringToDate(String strDate)
			throws ParseException {
		return convertStringToDate(getDatePattern(), strDate);
	}

	/**
	 * clear hour,minute,second,millisecond to 0, is 00:00:00.000.
	 * @param date
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	public static Calendar clearTimes(Date date) {
		int year, month, day;
		Calendar c = Calendar.getInstance();
		{
			c.setTime(date);
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH);
			day = c.get(Calendar.DAY_OF_MONTH);
		}
		{
			c.setTimeInMillis(0);
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, month);
			c.set(Calendar.DAY_OF_MONTH, day);
		}
		return c;
	}

	/**
	 * test two day
	 * @param origin
	 * @param toCompare
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	public static boolean isSameDate(Date origin, Date toCompare) {
		Calendar o = Calendar.getInstance();
		{
			o.setTime(origin);
		}
		Calendar t = Calendar.getInstance();
		{
			t.setTime(toCompare);
		}
		return o.get(Calendar.YEAR) == t.get(Calendar.YEAR)
				&& o.get(Calendar.MONTH) == t.get(Calendar.MONTH)
				&& o.get(Calendar.DAY_OF_MONTH) == t.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * test two dates are the same week day
	 * @param origin
	 * @param toCompare
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	public static boolean isWeekly(Date origin, Date toCompare) {
		Calendar o = Calendar.getInstance();
		{
			o.setTime(origin);
		}
		Calendar t = Calendar.getInstance();
		{
			t.setTime(toCompare);
		}
		return o.get(Calendar.DAY_OF_WEEK) == t.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * test two dates are the bi-weekly day
	 * @param origin
	 * @param toCompare
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	public static boolean isBiWeekly(Date origin, Date toCompare) {
		Calendar o = clearTimes(origin);
		Calendar t = clearTimes(toCompare);
		boolean isWeekly = o.get(Calendar.DAY_OF_WEEK) == t
				.get(Calendar.DAY_OF_WEEK);
		if (!isWeekly) {
			return false;
		}
		long days = (toCompare.getTime() - origin.getTime())
				/ (24 * 3600 * 1000);
		return days % 14 == 0;
	}

	/**
	 * test two dates are the same month day
	 * @param origin
	 * @param toCompare
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	public static boolean isMonthly(Date origin, Date toCompare) {
		Calendar o = Calendar.getInstance();
		{
			o.setTime(origin);
		}
		Calendar t = Calendar.getInstance();
		{
			t.setTime(toCompare);
		}
		return o.get(Calendar.DAY_OF_MONTH) == t.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * return the max date(2050/01/01)
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-28
	 */
	public static Date getMaxDate() {
		Calendar c = clearTimes(new Date());
		{
			c.set(Calendar.YEAR, 2050);
			c.set(Calendar.MONTH, 0);
			c.set(Calendar.DAY_OF_MONTH, 1);
		}
		return c.getTime();
	}

	/**
	 * return the max day(eg. 2011/01/01 12:00:00 will return 2011/01/01 23:59:59)
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-28
	 */
	public static Calendar getMaxDay(Date date) {
		Calendar c = clearTimes(date);
		{
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
			c.set(Calendar.SECOND, c.get(Calendar.SECOND) - 1);
		}
		return c;
	}

	public static Date nextDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
		return c.getTime();
	}

	public static int getMinute(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY) * 60 + c.get(Calendar.MINUTE);
	}

	public static Date getSundayDay(Date date) {
		Calendar c = clearTimes(date);
		{
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)
					- c.get(Calendar.DAY_OF_WEEK) + 1);
		}
		return c.getTime();
	}

	public static Date getSaturdayDay(Date date) {
		Calendar c = clearTimes(date);
		{
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)
					- c.get(Calendar.DAY_OF_WEEK) + 1 + 6);
		}
		return c.getTime();
	}

	public static Date getMonthFirstDay(Date date) {
		Calendar c = clearTimes(date);
		{
			c.set(Calendar.DAY_OF_MONTH, 1);
		}
		return c.getTime();
	}

	public static Date getMonthLastDay(Date date) {
		Calendar c = clearTimes(date);
		{
			c.set(Calendar.DAY_OF_MONTH, 1);
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 1);
		}
		return c.getTime();
	}

	/**
	 * return the end date by the start date and duration type and occurrent times
	 * @param start
	 * @param durationType
	 * @param occurrents
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-28
	 */
	public static Date getEndDate(Date start, int durationType, int occurrents) {
		if (occurrents < 1) {
			return start;
		}
		Date end = getMaxDate();
		switch (durationType) {
		case DURATION_NO_REPEAT: {
			break;
		}
		case DURATION_DAYLY: {
			Calendar c = clearTimes(start);
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)
					+ occurrents);
			end = c.getTime();
			break;
		}
		case DURATION_WEEKLY: {
			Calendar c = getMaxDay(start);
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)
					+ occurrents * 7);
			end = c.getTime();
			break;
		}
		case DURATION_BI_WEEKLY: {
			Calendar c = getMaxDay(start);
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)
					+ occurrents * 14);
			end = c.getTime();
			break;
		}
		case DURATION_MONTHLY: {
			Calendar c = getMaxDay(start);
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) + occurrents);
			end = c.getTime();
			break;
		}
		default:
			break;
		}
		return end;
	}
}