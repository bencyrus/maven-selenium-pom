package com.qac.maven_selenium_pom.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
	
	private static String runTimeStamp = "RUN_TIME_STAMP";
	
	private static void storeRunTimeStamp() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   runTimeStamp =  dtf.format(now); 
	}
	
	public static String getRunTimeStamp() {
		if (runTimeStamp.equals("RUN_TIME_STAMP")) {
			storeRunTimeStamp();
		}
		return runTimeStamp;
	}
	
	public static String getTimestampInFormat(String _format) {
		// time stamp code
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern(_format);  
		   LocalDateTime now = LocalDateTime.now();  
		   return dtf.format(now); 
	}
	
	public static String getTimestamp() {
		// time stamp code
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   return dtf.format(now); 
	}

}
