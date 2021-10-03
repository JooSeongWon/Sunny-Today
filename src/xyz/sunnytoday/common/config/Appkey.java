package xyz.sunnytoday.common.config;

import java.util.HashMap;
import java.util.Map;

public class Appkey {
    private final String name;
    private final Map<String, String> keys;

    public Appkey(String name) {
        this.name = name;
        keys = new HashMap<>();
    }

    public String getName() {
        return this.name;
    }

    public void setKey(String name, String key) {
        this.keys.put(name, key);
    }

    public String getKey(String name) {
        return keys.get(name);
    }
}
