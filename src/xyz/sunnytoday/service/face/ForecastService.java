package xyz.sunnytoday.service.face;

import xyz.sunnytoday.common.repository.Region;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;

public interface ForecastService {

    void requestGeoLocationData(Region region) throws IOException, HTTPException;

}
