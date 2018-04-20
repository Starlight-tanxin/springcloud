package com.wm.lejia.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapUtils {
	
	/**
	 * clone一个map
	 * @param map
	 * @author TanXin 17/1/11
	 * @return
	 */
	public static Map<String, Object> cloneMap(Map<String, Object> map){
		Map<String, Object> cloneMap = new HashMap<>();
		Set<Entry<String, Object>> entrySet = map.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			cloneMap.put(entry.getKey(), entry.getValue());
		}
		return cloneMap;
	}
	
	/**
	 * 泛型化
	 * @author tanxin
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List<Map<String, Object>> conversionList(List<Map> list) {
		List<Map<String, Object>> toList = new ArrayList<Map<String, Object>>();
		for (Map map : list) {
			Map<String, Object> m = new HashMap<>();
			@SuppressWarnings("unchecked")
			Set<Entry<String, Object>> entrySet = map.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				if ("createdTime".equals(entry.getKey())) {
					long timeLong = (long) entry.getValue();
					m.put(entry.getKey(), DateUtils.Long2Date(timeLong));
				} else {
					m.put(entry.getKey(), entry.getValue());
				}
			}
			toList.add(m);
		}
		return toList;
	}
}
