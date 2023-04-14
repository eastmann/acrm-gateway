package com.rtk.acrm.gateway.utils;

public class NumberUtils {
	public static Double parseDouble(String source) {
		if(source == null) {
			return null;
		}

		String replaced = source.replaceAll(",", ".").replaceAll("\u00A0", "");

		try {
			return Double.parseDouble(replaced);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static Integer parseInteger(String source) {
		if(source == null) {
			return null;
		}

		try {
			return Integer.parseInt(source);
		} catch (NumberFormatException e) {
			return null;
		}

	}

	public static Long parseLong(String source) {
		if(source == null) {
			return null;
		}

		try {
			return Long.parseLong(source);
		} catch (NumberFormatException e) {
			return null;
		}

	}

	public static String intToString(Integer num) {
		if(num == null) {
			return null;
		}
		return Integer.toString(num);
	}

	public static String longToString(Long num) {
		if(num == null) {
			return null;
		}
		return Long.toString(num);
	}

	public static String doubleToString(Double num) {
		if(num == null) {
			return null;
		}
		return Double.toString(num);
	}
}
