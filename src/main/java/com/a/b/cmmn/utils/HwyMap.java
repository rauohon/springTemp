package com.a.b.cmmn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.collections.map.ListOrderedMap;

public class HwyMap extends ListOrderedMap {
	
	private static final long serialVersionUID = 2126960460518450008L;

	public HwyMap() {

	}
	
	@Override
	public Object put(Object key, Object value) {
		if (value instanceof Date) {
			SimpleDateFormat app_ = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			super.put(CamelUtil.convert2CamelCase("app_"+(String)key), app_.format(value));
			
			SimpleDateFormat app2_ = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			super.put(CamelUtil.convert2CamelCase("app2_"+(String)key), app2_.format(value));
		}
		
		Object resultObject = null;
		resultObject = super.put(CamelUtil.convert2CamelCase("app2_"+(String)key), value);
		
		return resultObject;
	}

}
