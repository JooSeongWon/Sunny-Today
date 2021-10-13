package xyz.sunnytoday.common.config;

import xyz.sunnytoday.common.repository.ForecastTemperature;
import xyz.sunnytoday.common.repository.ForecastWeather;
import xyz.sunnytoday.common.repository.Region;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegionParser {
    private final String filePath;
    private final Map<String, List<ForecastWeather>> forecastWeatherMap = new HashMap<>();
    private final Map<String, List<ForecastTemperature>> forecastTemperatureMap = new HashMap<>();

    public RegionParser(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, Region> parseRegions() throws IOException {
        Map<String, Region> regions = new HashMap<>();
        FileInputStream input = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String str;
        while ((str = bufferedReader.readLine()) != null) {
            String[] data = str.split("/");
            Region region = new Region(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), data[4], data[5]);

            regions.put(region.getR1() + region.getR2(), region);
            if(!forecastWeatherMap.containsKey(data[4])) {
                forecastWeatherMap.put(data[4], null);
            }
            if(!forecastTemperatureMap.containsKey(data[5])) {
                forecastTemperatureMap.put(data[5], null);
            }
        }

        return regions;
    }

    public Map<String, List<ForecastWeather>> getForecastWeatherMap() {
        return forecastWeatherMap;
    }

    public Map<String, List<ForecastTemperature>> getForecastTemperatureMap() {
        return forecastTemperatureMap;
    }

}
