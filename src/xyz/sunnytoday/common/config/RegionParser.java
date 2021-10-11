package xyz.sunnytoday.common.config;

import xyz.sunnytoday.common.repository.Region;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegionParser {
    private final String filePath;

    public RegionParser(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, Region> parseRegions() throws IOException {
        Map<String, Region> regions = new HashMap<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

        String str;
        while ((str = bufferedReader.readLine()) != null) {
            String[] data = str.split("/");
            Region region = new Region("kr", data[0], data[1], data[2], Integer.parseInt(data[3]),Integer.parseInt(data[4]));

            regions.put(region.getR1() + region.getR2(), region);
        }

        return regions;
    }

}
