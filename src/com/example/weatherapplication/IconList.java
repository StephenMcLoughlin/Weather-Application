package com.example.weatherapplication;

import java.util.HashMap;
import java.util.Map;

public class IconList {
	Map<String, Integer> map;
	

	public IconList() {
		
		this.map = new HashMap<String, Integer>();
		
		this.map.put("01d", R.drawable.sun_icon_big);
		this.map.put("02d", R.drawable.partly_cloudly_icon);
		this.map.put("03d", R.drawable.cloud_icon);
		this.map.put("04d", R.drawable.cloud_icon);
		this.map.put("09d", R.drawable.rain_icon);
		this.map.put("10d", R.drawable.light_rain_icon);
		this.map.put("11d", R.drawable.lightning_icon);
		this.map.put("12d", R.drawable.snow_icon);
		this.map.put("50d", R.drawable.mist_icon);
		this.map.put("01n", R.drawable.clear_night_icon);
		this.map.put("02n", R.drawable.cloudy_night_icon);
		this.map.put("03n", R.drawable.cloud_icon);
		this.map.put("04n", R.drawable.cloud_icon);
		this.map.put("09n", R.drawable.rain_icon);
		this.map.put("10d", R.drawable.light_rain_icon);
		this.map.put("11d", R.drawable.lightning_icon);
		this.map.put("12d", R.drawable.snow_icon);
		this.map.put("50n", R.drawable.mist_icon);
		
	}
}
