package pl.damian.backend.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.damian.backend.domain.exception.DomainException;
import pl.damian.backend.domain.task.TaskService;
import pl.damian.backend.infrastructure.repository.TaskRepositoryInFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherIntegrationTest {
    private final ApplicationAPI applicationAPI = new ApplicationAPI(new TaskService(new TaskRepositoryInFile()));

    @Test
    void whenCurrentWeather_withValidCity_shouldReturnWeather() {
        //GIVEN
        String city = "Lublin";
        //WHEN
        String weatherData = applicationAPI.checkCurrentWeather(city);
        //THEN
        Assertions.assertNotNull(weatherData);
        Assertions.assertTrue(weatherData.contains("Miasto: Lublin"));
    }

    @Test
    void whenCurrentWeather_withInvalidCity_shouldThrowDomainException() {
        //GIVEN
        String city = "XYZ";
        //WHEN //THEN
        Assertions.assertThrows(DomainException.class, () -> applicationAPI.checkCurrentWeather(city));
    }

    @Test
    void whenForecastWeather_withValidData_shouldReturnWeather() {
        //GIVEN
        String city = "Lublin";
        int days = 2;
        //WHEN
        String weatherData = applicationAPI.checkForecastWeather(city, days);
        //THEN
        Assertions.assertNotNull(weatherData);
        Assertions.assertEquals(days, countWordsInText(weatherData, "Dzień"));
    }

    @Test
    void whenForecastWeather_withDaysAbove3_shouldReturnWeatherWith3Days() {
        //GIVEN
        String city = "Lublin";
        int requestDays = 4;
        int maxDays = 3;
        //WHEN
        String weatherData = applicationAPI.checkForecastWeather(city, requestDays);
        //THEN
        Assertions.assertNotNull(weatherData);
        Assertions.assertEquals(maxDays, countWordsInText(weatherData, "Dzień"));
    }

    @Test
    void whenForecastWeather_withDaysBelow1_shouldReturnWeatherWith1Days() {
        //GIVEN
        String city = "Lublin";
        int requestDays = 0;
        int minDays = 1;
        //WHEN
        String weatherData = applicationAPI.checkForecastWeather(city, requestDays);
        //THEN
        Assertions.assertNotNull(weatherData);
        Assertions.assertEquals(minDays, countWordsInText(weatherData, "Dzień"));
    }

    @Test
    void whenForecastWeather_withInvalidCity_shouldThrowDomainException() {
        //GIVEN
        String city = "XYZ";
        int days = 2;
        //WHEN //THEN
        Assertions.assertThrows(DomainException.class, () -> applicationAPI.checkForecastWeather(city, days));
    }

    @Test
    void whenHistoryWeather_withValidData_shouldReturnWeather() {
        //GIVEN
        String city = "Lublin";
        String date = "2021-08-08";
        //WHEN
        String weatherData = applicationAPI.checkHistoryWeather(city, date);
        //THEN
        Assertions.assertNotNull(weatherData);
        Assertions.assertTrue(weatherData.contains("Miasto: " + city));
        Assertions.assertTrue(weatherData.contains("Dzień: " + date));
    }

    @Test
    void whenHistoryWeather_withInvalidCity_shouldThrowDomainException() {
        //GIVEN
        String invalidCity = "Xyz";
        String date = "2021-07-07";
        //WHEN//THEN
        Assertions.assertThrows(DomainException.class, () -> applicationAPI.checkHistoryWeather(invalidCity, date));
    }

    @Test
    void whenHistoryWeather_withInvalidDate_shouldThrowDomainException() {
        //GIVEN
        String city = "Lublin";
        String invalidDate = "2021-01-01";
        //WHEN//THEN
        Assertions.assertThrows(DomainException.class, () -> applicationAPI.checkHistoryWeather(city, invalidDate));
    }

    @Test
    void whenHistoryWeather_withInvalidFormatDate_shouldThrowDomainException() {
        //GIVEN
        String city = "Lublin";
        String invalidDate = "2021-01";
        //WHEN//THEN
        Assertions.assertThrows(DomainException.class, () -> applicationAPI.checkHistoryWeather(city, invalidDate));
    }

    private static int countWordsInText(String text, String word) {
        int counts = 0;
        Pattern pattern = Pattern.compile(word);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            counts++;
        }
        return counts;
    }
}
