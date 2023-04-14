package com.rtk.acrm.gateway.utils;

public class UrlUtils {

	public static String joinUrl(String... parts) {
		if(parts == null || parts.length == 0) {
			return null;
		}

		StringBuilder result = new StringBuilder();

		for(int i = 0; i < parts.length; i++) {
			String part = parts[i];

			if(part == null) { continue; }

			if(i > 0) {
				result.append("/");
			}

			if(part.endsWith("/")) {
				part = StringUtils.removeSymbolsFromEnd(part, 1);
			}
			if(part.startsWith("/")) {
				part = StringUtils.removeSymbolsFromStart(part, 1);
			}
			result.append(part);

		}

		return result.toString();
	}
}
