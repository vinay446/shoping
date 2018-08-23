package com.org.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;


/**
 * Common Date utility class
 * 
 * @author VINAY
 *
 */
public class DateUtil {

	private static Logger log = Logger.getLogger(DateUtil.class);
	
	//default date format
	private static final SimpleDateFormat sd = new SimpleDateFormat("dd-MMMM-yyyy hh:mm:ss a");

	/**
	 * returns date obj
	 * 
	 * @return
	 */
	public static Date getDateObj() {
		return new Date();
	}

	/**
	 *
	 * @return current date in default date format
	 */
	public static String getCurrentDate() {
		return sd.format(getDateObj());
	}

	/**
	 * 
	 * @param dateformat req date format
	 * @return current date in given date format
	 */
	public static String getCurrentDate(String dateformat) {
		SimpleDateFormat sd = new SimpleDateFormat(dateformat);
		return sd.format(getDateObj());
	}

	/**
	 * 
	 * @return current date in unix seconds
	 */
	public static long getEpoch() {
		return getDateObj().getTime() / 1000;
	}

	/**
	 * 
	 * @param date date string to be converted
	 * @param dateformat  dateformat of date
	 * @return unix seconds of given date and format
	 */
	public static long getEpoch(String date, String dateformat) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat(dateformat);
			return sf.parse(date).getTime() / 1000;
		} catch (Exception e) {
			e.printStackTrace();
			log.fatal("Error during epoch convertion "+e.getMessage());
			return 0;
		}
	}

	/**
	 * Converts epoch to date in default date format
	 * 
	 * @param date epoch seconds
	 * @return String date
	 */
	public static String epochToDate(long date) {
		return sd.format(new Date(date * 1000));
	}

	/**
	 * Converts epoch to date in given date format
	 * 
	 * @param dateformat required date format
	 * @param date epoch seconds
	 * @return String date
	 */
	public static String epochToDate(long date, String dateformat) {
		SimpleDateFormat sf = new SimpleDateFormat(dateformat);
		return sf.format(new Date(date * 1000));
	}

	/**
	 * returns midnight epoch of given epoch
	 * @param epoch current time 
	 * @return long
	 */
	public static long getmidnightepoch(long epoch) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat("dd-MMMM-yyyy");
                        String currentdate = sf.format(getDateObj());                        
			return sf.parse(currentdate).getTime()/1000;
		} catch (Exception e) {
			e.printStackTrace();
			log.fatal("Error during epoch convertion "+e.getMessage());
			return 0;
		}
	}
	
	public static void main(String []args) {
		System.out.println("testing...");
                System.out.println("get date obj "+getDateObj());
                System.out.println("getCurrentDate "+getCurrentDate());
                System.out.println("getCurrentDate "+getCurrentDate("dd-MMMM-yyyy"));
                System.out.println("getEpoch "+getEpoch());
                System.out.println("getepoch "+getEpoch("9-9-99999999999999 09:09:09", "dd-MM-yyyy HH:mm:ss"));
                System.out.println("epochtodate "+epochToDate(315507342944349L));
                System.out.println("epocjtodate "+epochToDate(15555, "dd-MMMM-yyyy hh:mm:ss a"));
                System.out.println("getmidnightepoch "+getmidnightepoch(0454654654));
	}
}
