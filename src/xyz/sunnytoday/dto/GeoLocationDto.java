package xyz.sunnytoday.dto;

public class GeoLocationDto {
    private String country;
    private String code;
    private String r1;
    private String r2;
    private String r3;
    private float lat;
    private float lon;

    public GeoLocationDto(String country, String code, String r1, String r2, String r3, float lat, float lon) {
        this.country = country;
        this.code = code;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.lat = lat;
        this.lon = lon;
    }

    public String getCountry() {
        return country;
    }

    public String getCode() {
        return code;
    }

    public String getR1() {
        return r1;
    }

    public String getR2() {
        return r2;
    }

    public String getR3() {
        return r3;
    }

    public float getLat() {
        return lat;
    }

    public float getLong() {
        return lon;
    }

    @Override
    public String toString() {
        return "geoLocationDto{" +
                "country='" + country + '\'' +
                ", code='" + code + '\'' +
                ", r1='" + r1 + '\'' +
                ", r2='" + r2 + '\'' +
                ", r3='" + r3 + '\'' +
                ", let=" + lat +
                ", long=" + lon +
                '}';
    }
}
