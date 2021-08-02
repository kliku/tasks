package pl.damian.backend.infrastructure.weather.weatherapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import pl.damian.backend.domain.weather.model.current.CurrentWeather;
import pl.damian.backend.domain.weather.model.forecast.ForecastWeather;
import pl.damian.backend.domain.weather.model.history.HistoryWeather;
import pl.damian.backend.domain.weather.WeatherInformation;

import java.util.Optional;

public class WeatherInformationAPI implements WeatherInformation {
    private static final String weatherURL = "http://api.weatherapi.com/v1";
    private static final String weatherCurrentURL = weatherURL + "/current.json";
    private static final String weatherForecastURL = weatherURL + "/forecast.json";
    private static final String weatherHistoryURL = weatherURL + "/history.json";
    private static final String token = "";

    @Override
    public Optional<CurrentWeather> getCurrentWeather(String city) {
        try {
            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet( weatherCurrentURL + "?key=" + token + "&q=" + city + "&aqi=no");
            CloseableHttpResponse response = closeableHttpClient.execute(request);
            String responseJson = EntityUtils.toString(response.getEntity());
            ObjectMapper objectMapper = new ObjectMapper();
            return Optional.of(objectMapper.readValue(responseJson, CurrentWeather.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ForecastWeather> getForecastWeather(String city, int days) {
        try {
            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet(weatherForecastURL + "?key=" + token + "&q=" + city + "&days=" + days +
                    "&aqi=no&alerts=no");
            CloseableHttpResponse response = closeableHttpClient.execute(request);
            String responseJson = EntityUtils.toString(response.getEntity());
            ObjectMapper objectMapper = new ObjectMapper();
            return Optional.of(objectMapper.readValue(responseJson, ForecastWeather.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<HistoryWeather> getHistoryWeather(String city, String date) {
        try {
            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet(weatherHistoryURL + "?key=" + token + "&q=" + city + "&dt=" + date);
            CloseableHttpResponse response = closeableHttpClient.execute(request);
            String responseJson = EntityUtils.toString(response.getEntity());
            ObjectMapper objectMapper = new ObjectMapper();
            return Optional.of(objectMapper.readValue(responseJson, HistoryWeather.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
