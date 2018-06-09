package client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.WeatherModel;
import domain.WeatherResponse;

public class WeatherClient {
	public static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=9fbc19c720911db08b7781156aebcbd8";
	
	public WeatherResponse getWeatherForCity(String city) {
		Response response = ClientBuilder
				.newClient()
				.target(String.format(URL, city))
				.request(MediaType.APPLICATION_JSON)
				.get();
		
		if(response.getStatus() == 200) {
			WeatherModel apiResponse = response.readEntity(WeatherModel.class);
			return new WeatherResponse(apiResponse.getClouds().getAll(), 
					apiResponse.getMain().getTemp(), apiResponse.getMain().getPressure(), apiResponse.getWind().getSpeed());
		}
		return null;
	}
}
