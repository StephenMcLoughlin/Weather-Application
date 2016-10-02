package com.example.weatherapplication;

public class WeatherData {

	//Wind details
	private float speed;
	private float deg;
	
	//Weather details
	private int weatherID;
	private String description;
	private String condition;
	private String icon;
	
	//Temp details
	private int humidity;
	private int pressure;
	private float maxTemp;
	private float minTemp;
	private float temp;
	
	//Cloud details
	private int percentage;
	
	private String country;
	private String city;
	
	public WeatherData() {
		
	}
	
	public int getWeatherID() {
		return weatherID;
	}

	public void setWeatherID(int weatherID) {
		this.weatherID = weatherID;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public float getDeg() {
		return deg;
	}
	
	public void setDeg(float deg) {
		this.deg = deg;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public int getPressure() {
		return pressure;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public float getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(float maxTemp) {
		this.maxTemp = maxTemp;
	}

	public float getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(float minTemp) {
		this.minTemp = minTemp;
	}

	public float getTemp() {
		return temp;
	}

	public void setTemp(float temp) {
		this.temp = temp;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCondition() {
		return condition;
	}


	public void setCondition(String condition) {
		this.condition = condition;
	}


	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}


	public int getPercentage() {
		return percentage;
	}


	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	
	
	
}
