package com.example.weatherapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView cityTxt;
	TextView tempTxt;
	TextView condTxt;
	TextView humidTxt;
	TextView windSpeedTxt;
	TextView maxTempTxt;
	TextView minTempTxt;
	TextView pressureTxt;
	TextView enterCityTxt;
	ImageView image;
	EditText search;
	Button btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		
		//Display 
		cityTxt = (TextView) findViewById(R.id.cityTxt);
		tempTxt = (TextView) findViewById(R.id.tempTxt);
		condTxt = (TextView) findViewById(R.id.condTxt);
		humidTxt = (TextView) findViewById(R.id.humidityTxt);
		pressureTxt = (TextView) findViewById(R.id.pressureTxt);
		windSpeedTxt = (TextView) findViewById(R.id.windSpeedTxt);
		maxTempTxt = (TextView) findViewById(R.id.maxTempTxt);
		minTempTxt = (TextView) findViewById(R.id.minTempTxt);
		enterCityTxt = (TextView) findViewById(R.id.enterCityTxt);
		image = (ImageView) findViewById(R.id.image);
		search = (EditText) findViewById(R.id.cityEditTxt);
		btn = (Button) findViewById(R.id.submitBtn);
		
		//City search on Enter Key
		search.setOnKeyListener(new OnKeyListener() {
		      public boolean onKey(View v, int keyCode, KeyEvent event) {
		        if (event.getAction() == KeyEvent.ACTION_DOWN)
		          if (keyCode == KeyEvent.KEYCODE_ENTER) {
		        	  //Remove spaces if any
		        	  enterText(search.getText().toString().replaceAll("\\s",""));
		            return true;
		          }
		        return false;
		      }

		});
		
		
		//City search on Button Click
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Remove spaces if any
				enterText(search.getText().toString().replaceAll("\\s",""));
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class HttpAsyncTask extends AsyncTask<String, Void, WeatherData> {
        @Override
        protected WeatherData doInBackground(String... urls) {
        	
        	String data = (new WeatherHttpClient().GET(urls[0]));
        	String status;
        	JSONWeatherParser jPars = null;
        	WeatherData weather = new WeatherData();
            	
    		//Put data in JSON Object
			JSONObject jObj = null;
			try {
				jObj = new JSONObject(data);
				jPars = new JSONWeatherParser();
				
				status = jPars.getString("cod", jObj);
				
				if(status.equals("200")) {
					try {
						weather.setCity(jPars.getString("name", jObj));
						JSONObject sysObj = jPars.getObject("sys", jObj);
	    				weather.setCountry(jPars.getString("country", sysObj));
	    				
	    				//Weather details 
	    				JSONArray jArrWeather = jObj.getJSONArray("weather");
	    				JSONObject JSONWeather = jArrWeather.getJSONObject(0);
	    				weather.setWeatherID(jPars.getInt("id", JSONWeather));
	    				weather.setDescription(jPars.getString("description", JSONWeather));
	    				weather.setCondition(jPars.getString("main", JSONWeather));
	    				weather.setIcon(jPars.getString("icon", JSONWeather));
	    				
	    				//Temp Details
	    				JSONObject mainObj = jPars.getObject("main", jObj);
	    				weather.setTemp(jPars.getFloat("temp", mainObj));
	    				weather.setPressure(jPars.getInt("pressure", mainObj));
	    				weather.setHumidity(jPars.getInt("humidity", mainObj));
	    				weather.setMinTemp(jPars.getFloat("temp_min", mainObj));
	    				weather.setMaxTemp(jPars.getFloat("temp_max", mainObj));
	    				
	    				//Wind details
	    				JSONObject wObj = jPars.getObject("wind", jObj);
	    				weather.setDeg(jPars.getFloat("deg", wObj));		
	    				weather.setSpeed(jPars.getFloat("speed", wObj));
	    				
	    				//Clouds
	    				JSONObject cObj = jObj.getJSONObject("clouds");
	    				weather.setPercentage(jPars.getInt("all", cObj));
	    			
	    				return weather;
	    				
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} 

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    				
			return null;		
        }
        
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(WeatherData weather) {
        	super.onPostExecute(weather);
           
          if(weather == null) {
        	  image.setImageResource(R.drawable.weather_icon);
        	  tempTxt.setText("Invalid!");
          } else {
        	 
        	  image.setImageResource(new IconList().map.get(weather.getIcon()));
	          
	          cityTxt.setText(weather.getCity() + ", " + weather.getCountry());    
	          tempTxt.setText(floatToString(weather.getTemp()) + " \u2103");
	          condTxt.setText(weather.getCondition() + ", " + weather.getDescription());
	          humidTxt.setText("Humidity " + intToString(weather.getHumidity()) + "%");
	          pressureTxt.setText("Pressure " +intToString(weather.getPressure()) + "pa");  
	          windSpeedTxt.setText("Wind Speed: " + floatToString(weather.getSpeed()));
	          maxTempTxt.setText("Max Temp: " + floatToString(weather.getMaxTemp()));
	          minTempTxt.setText("Min Temp: " + floatToString(weather.getMaxTemp()));
	             
          }
   
        } 
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private String floatToString(float number) {
		String Strnumber = Float.toString(number);
		return Strnumber;
	}
	
	private String intToString(int number) {
		String Strnumber = Integer.toString(number);
		return Strnumber;
	}
	
	private void enterText(String entry) {
		//Empty textfield
		if(entry.equals("")) {
			invalid();

		} else {
			//Run AsyncTask to URL 
			new HttpAsyncTask().execute("http://api.openweathermap.org/data/2.5/weather?q=" + entry + "&units=metric&APPID=eca0ac170e019a7d734df76985ca8ed9");
			search.setText("");
		}
	}
	
	//Flash Invalid Entry
	private void invalid() {
		
		new CountDownTimer(1000, 500) {

		     public void onTick(long millisUntilFinished) {
		    	 enterCityTxt.setText("Invalid Entry!");
		     }

		     public void onFinish() {
		         enterCityTxt.setText("Get Weather:");
		     }
		  }.start();
	}
}
