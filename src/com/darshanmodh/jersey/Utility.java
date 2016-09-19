package com.darshanmodh.jersey;

import org.codehaus.jettison.json.JSONObject;

public class Utility {
	
	public static boolean isNotNull(String txt) {
		return txt != null && txt.trim().length() >= 0 ? true : false;
	}
	
	public static String constructJSON(String tag, boolean status) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj.toString();
	}
	
	public static String constructJSON(String tag, boolean status, String err_msg) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", status);
			obj.put("error_msg", err_msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj.toString();
	}

}
