package client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.WeatheModel;
import domain.WeatherResponse;

public class WeatherClient {
	public static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&APPID=26f026d26f85477f9940fec6dd0b75ff";
	
	public WeatherResponse getWeatherForCity(String city) {
		Response response = ClientBuilder
				.newClient()
				.target(String.format(URL, city))
				.request(MediaType.APPLICATION_JSON)
				.get();
		
		if(response.getStatus() == 200) {
			WeatheModel apiResponse = response.readEntity(WeatheModel.class);
			return new WeatherResponse(apiResponse.getClouds().getAll(), 
					apiResponse.getMain().getTemp(), apiResponse.getMain().getPressure(), apiResponse.getWind().getSpeed());
		}
		return null;
	}
}
