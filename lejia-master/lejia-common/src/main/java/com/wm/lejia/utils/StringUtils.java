package com.wm.lejia.utils;

public class StringUtils {
	
	public static boolean isEmptyStr(String tpoo) {
		if (tpoo == null || "null".equalsIgnoreCase(tpoo.trim()) || "".equals(tpoo.trim())
				|| "NaN".equalsIgnoreCase(tpoo.trim())) {
			return true;
		}
		return false;
	}
}
