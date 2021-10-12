package xyz.sunnytoday.common.config;

import xyz.sunnytoday.common.repository.Region;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RegionParser {
    private final String filePath;

    public RegionParser(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, Region> parseRegions() throws IOException {
        Map<String, Region> regions = new HashMap<>();
        FileInputStream input = new FileInputStream(filePath);
        InputStreamReader reader=new InputStreamReader(input, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String str;
        while ((str = bufferedReader.readLine()) != null) {
            String[] data = str.split("/");
            Region region = new Region("kr", data[0], data[1], data[2], Integer.parseInt(data[3]),Integer.parseInt(data[4]));

            regions.put(region.getR1() + region.getR2(), region);
        }

        return regions;
    }

}
