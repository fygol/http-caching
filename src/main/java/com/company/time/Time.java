package com.company.time;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Time {

    private static final TimeZone GMT_TIME_ZONE = TimeZone.getTimeZone("GMT");
    private static final SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'");
    static {
        format.setTimeZone(GMT_TIME_ZONE);
    }

    public static String formatToHttpDate(Date date) {
        format.setTimeZone(GMT_TIME_ZONE);
        return format.format(date);
    }

    public static String formatToHttpDate(long millis) {
        return Time.formatToHttpDate(new Date(millis));
    }

    public static Date parseFromHttpDate(String date) {
        try {
            return format.parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Date addSeconds(Date date, int seconds) {
    	return new Date(date.getTime() + seconds * 1000);
    }
    
    public static Date addMinutes(Date date, int minutes) {
    	return Time.addSeconds(date, minutes * 60);
    }
    
    public static Date afterSeconds(int seconds) {
    	return Time.addSeconds(new Date(), seconds);
    }
    
    public static Date afterMinutes(int seconds) {
    	return Time.addMinutes(new Date(), seconds);
    }
    
    public static void main(String[] args) {
        Date now = new Date();
        System.out.println( Time.formatToHttpDate(now) );
        System.out.println( Time.formatToHttpDate(now.getTime()) );
        System.out.println( Time.parseFromHttpDate(Time.formatToHttpDate(now)).toGMTString() );
        
        System.out.println(Time.addSeconds(now, 30).toGMTString());
        System.out.println(Time.addMinutes(now, 30).toGMTString());
        System.out.println( Time.formatToHttpDate(Time.afterSeconds(30)) );
        
    }
}
