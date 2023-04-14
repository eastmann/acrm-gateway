package com.rtk.acrm.gateway.utils;

import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class StringUtils {
	public static String removeQuotes(String src) {
		if(src == null) {
			return null;
		}
		return src.replaceAll("\"", "");
	}

	public static String booleanToIntString(boolean value) {
		return value ? "1" : "0";
	}

	public static boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static String strValue(Long val){
		if (val == null) return "";
		return  String.valueOf(val);
	}

	public static String strValue(String val){
		if (val == null) return "";
		return  val;
	}

	public static String strValue(LocalDate val){
		if (val == null) return "";
		return  String.valueOf(val);
	}

	public static String strValue(LocalDateTime val){
		if (val == null) return "";
		return  String.valueOf(val);
	}

	public static boolean isStrNull(String str) {
		if (str != null && !str.equals("") && !str.toUpperCase().equals("NULL"))
			return false;
		else
			return true;
	}

	public static String join(@Nullable List objects, String separator) {
		if(objects == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < objects.size(); i++) {
			if(i > 0) {
				sb.append(separator);
			}
			Object o = objects.get(i);
			sb.append(objects.get(i));
		}
		return sb.toString();
	}

	public static String removeSymbolsFromEnd(@Nullable String str, int count) {
		if(str == null) {
			return null;
		}
		return str.substring(0, str.length() - count);
	}

	public static String removeSymbolsFromStart(String str, int count) {
		if(str == null) {
			return null;
		}
		return str.substring(count);
	}

	public static String formatBillion(Long val){
		if ((val == null) || (val == 0)) return "";

		return  String.format("%,d", val / 1_000_000).replaceAll(",", " ");
	}

	public static String formatBillion(Double val){
		if ((val == null) || (val == 0)) return "";

		return  String.valueOf(val);
	}
}
