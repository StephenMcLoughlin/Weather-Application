package com.example.weatherapplication;

import org.json.*;


public class JSONWeatherParser {
	
	
	public JSONWeatherParser() {
		
	}
	
	public JSONObject getObject(String tagName, JSONObject jObj) throws JSONException {
		
		JSONObject subObj = jObj.getJSONObject(tagName);
		return subObj;
	}
	
	public String getString(String tagName, JSONObject jObj) throws JSONException {
		return jObj.getString(tagName);
	}
	
	public int getInt(String tagName, JSONObject jObj) throws JSONException {
		
		return jObj.getInt(tagName);
	}
	
	public float getFloat(String tagName, JSONObject jObj) throws JSONException {
		return (float) jObj.getDouble(tagName);
	}
}
