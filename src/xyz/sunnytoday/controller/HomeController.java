package xyz.sunnytoday.controller;

import xyz.sunnytoday.common.config.AppConfig;
import xyz.sunnytoday.common.repository.Forecast;
import xyz.sunnytoday.service.face.ForecastService;
import xyz.sunnytoday.service.face.GeoLocationService;
import xyz.sunnytoday.service.impl.ForecastServiceImpl;
import xyz.sunnytoday.service.impl.GeoLocationServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(
        urlPatterns = "/main",
        loadOnStartup = 1
)
public class HomeController extends HttpServlet {

    private GeoLocationService geoLocationService;
    private ForecastService forecastService;

    /*
     * AppConfig class의 lifecycle을 Servlet lifecycle과 맞춘다.
     * */

    @Override
    public void init(ServletConfig config) {
        AppConfig.Init(config.getServletContext().getRealPath("/WEB-INF/"));
        geoLocationService = new GeoLocationServiceImpl();
        forecastService = new ForecastServiceImpl();
    }

    @Override
    public void destroy() {
        AppConfig.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //도시 쿠키 존재여부 확인
        String cityR1 = "";
        String cityR2 = "";

        final Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cityR1")) {
                    cityR1 = cookie.getValue();
                } else if (cookie.getName().equals("cityR2")) {
                    cityR2 = cookie.getValue();
                }
            }
        }

        //쿠키가 없음 도시정보 조회해서 넣기.
        //도시정보 조회가 불가능한 ip라면 서울로 세팅한다.
        if (cityR1.equals("")) {
            String ipAddress = geoLocationService.getIpAddress(req);
            if (ipAddress.equals("127.0.0.1")) {
                cityR1 = "서울특별시";
            } else {
                try {
                    String[] regionName = geoLocationService.requestGeoLocationData(ipAddress);
                    if (AppConfig.getForecastRepository().isContainsCity(regionName[0] + regionName[1])) {
                        cityR1 = regionName[0];
                        cityR2 = regionName[1];
                    } else {
                        cityR1 = "서울특별시";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    cityR1 = "서울특별시";
                }
            }

            Cookie r1 = new Cookie("cityR1", cityR1);
            Cookie r2 = new Cookie("cityR2", cityR2);
            resp.addCookie(r1);
            resp.addCookie(r2);
        }

        req.setAttribute("r1", cityR1);
        req.setAttribute("r2", cityR2);
        req.setAttribute("sForecast", forecastService.getShortTermForecast(cityR1 + cityR2));

        final List<Forecast> mediumTermForecast = forecastService.getMediumTermForecast(cityR1 + cityR2);
        if (mediumTermForecast == null) {
            super.doGet(req, resp);
            return;
        }

        req.setAttribute("mForecast", mediumTermForecast);
        req.getRequestDispatcher("/WEB-INF/views/user/home/home.jsp").forward(req, resp);
    }
}
