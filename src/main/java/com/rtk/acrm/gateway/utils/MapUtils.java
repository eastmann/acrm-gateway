package com.rtk.acrm.gateway.utils;

import java.util.Map;

public class MapUtils {
	public static <T> T getOrCreateNew(Map<String, T> map, String key, Class<T> cl) {
		T existing = map.get(key);

		if(existing == null) {
			try {
				T newObj = cl.newInstance();
				map.put(key, newObj);

				return newObj;
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
		return existing;
	}

	public static String getStringOrNull(Map<String, Object> map, String key) {
		Object value = map.get(key);

		if(value != null) {
			return value.toString();
		}
		return null;
	}
}
