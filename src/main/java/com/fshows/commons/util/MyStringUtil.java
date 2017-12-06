package com.fshows.commons.util;

public class MyStringUtil {

	public static boolean isNullStr(String str) {
		if (null == str || str.length() == 0) {
			return true;
		}
		str = str.trim().toLowerCase();
		if ("null".equalsIgnoreCase(str)) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotNull(String str) {
		return !isNullStr(str);
	}
}
