package com.jt.test.service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @className:DateFormat.java
 * @classDescription:
 * @author:xing.zhao
 * @createTime:2013-8-8 下午7:37:37
 */
public class DateFormat {
	private static final Logger logger = LoggerFactory.getLogger(DateFormat.class);
	private static Map<String, String> monthMap = new HashMap<String, String>();
	static{
		monthMap.put("01", "JAN");
		monthMap.put("02", "FEB");
		monthMap.put("03", "MAR");
		monthMap.put("04", "APR");
		monthMap.put("05", "MAY");
		monthMap.put("06", "JUN");
		monthMap.put("07", "JUL");
		monthMap.put("08", "AUG");
		monthMap.put("09", "SEP");
		monthMap.put("10", "OCT");
		monthMap.put("11", "NOV");
		monthMap.put("12", "DEC");
	}
	private static final SimpleDateFormat[] formatYYYY_MM_DD = createDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat[] formatYYYY_MM_DD_HH_MM = createDateFormat("yyyy-MM-dd HH:mm");
	private static final SimpleDateFormat[] formatYYYYMMDD_HHMM = createDateFormat("yyyyMMdd HHmm");
	private static int currentIndex = 0; // 不需要考虑多线程问题，节省性能开销。
	private static final int INSTANCE_COUNT = 20;
	private static SimpleDateFormat[] createDateFormat(String fmt) {
    	SimpleDateFormat[] ret = new SimpleDateFormat[INSTANCE_COUNT];
    	for (int i = 0; i < ret.length; i++) {
    		ret[i] = new SimpleDateFormat(fmt);
    		ret[i].setLenient(false);
    	}
		return ret;
	}
	private final static int getIndex() {
    	int n = currentIndex++;
    	if (n < 0) { // 处理溢出
    		currentIndex = 0;
    		n = 0;
    	}
    	return n % INSTANCE_COUNT;
    }
	public static Date convertDate(String strDate, DateFormatType dateFormatType) {
		if (strDate == null || strDate.indexOf("null") >= 0)
			return null;
		strDate  = StringUtils.replace(strDate, "-", "");
		Date date = null;
		try {
			SimpleDateFormat fmt = null;
			if(dateFormatType.equals(dateFormatType.YYYY_MM_DD_HH_MM)){
				fmt = formatYYYY_MM_DD_HH_MM[getIndex()];
			}else if(dateFormatType.equals(dateFormatType.YYYY_MM_DD)){
				fmt = formatYYYY_MM_DD[getIndex()];
			}else{
				fmt = formatYYYYMMDD_HHMM[getIndex()];
			}
			synchronized(fmt){
				date = fmt.parse(strDate);
			}
		} catch (Exception e) {
			logger.error("convertLong error: date="+strDate, e);
			return null;
		}

		return date;
	}
	public static String date2Str(Date date, DateFormatType dateFormatType) {
    	SimpleDateFormat fmt = null;
    	if(dateFormatType.equals(dateFormatType.YYYY_MM_DD_HH_MM)){
			fmt = formatYYYY_MM_DD_HH_MM[getIndex()];
		}else if(dateFormatType.equals(dateFormatType.YYYY_MM_DD)){
			fmt = formatYYYY_MM_DD[getIndex()];
		}else{
			fmt = formatYYYYMMDD_HHMM[getIndex()];
		}
    	synchronized(fmt){
    		return fmt.format(new Date());
    	}
    }
	
	public static String formatDDMMMYY(Date date) {
		return String.format(Locale.US, "%1$td%1$tb%1$ty", date);
	}
	public static String formatDDMMMYY(String date){
//		Date dt = DateFormat.convertDate(date, DateFormatType.YYYY_MM_DD);
//		return formatDDMMMYY(dt);
		date = StringUtils.replace(date, "-", "");
		return StringUtils.substring(date, 6, 8) + monthMap.get(StringUtils.substring(date, 4, 6))+StringUtils.substring(date, 2, 4);

	}
	public enum DateFormatType{
		YYYY_MM_DD("yyyy-MM-dd"),
		YYYYMMDD_HHMM("yyyyMMdd HHmm"),
		YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm");
		private String formate;
		private DateFormatType(String format){
			this.formate = format;
		}
	}
	
	public static String getUSDateFormat(String dateStr){
		dateStr = StringUtils.replace(dateStr, "-", "");
		return dateStr.substring(6) + monthMap.get(dateStr.substring(4, 6));
	}
	public static void main(String[] args) {
		//System.out.println(formatDDMMMYY("2013-08-12"));
		String date = "2013-04-05";
		date = StringUtils.replace(date, "-", "");
		date= StringUtils.substring(date, 6, 8) + monthMap.get(StringUtils.substring(date, 4, 6))+StringUtils.substring(date, 2, 4);
		System.out.println(date);
		System.out.println(formatDDMMMYY("20130405"));
	}
	
}
