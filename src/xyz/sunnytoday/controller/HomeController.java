package xyz.sunnytoday.controller;

import xyz.sunnytoday.common.config.AppConfig;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = "/main",
        loadOnStartup = 1
)
public class HomeController extends HttpServlet {

    /*
    * AppConfig class의 lifecycle을 Servlet lifecycle과 맞춘다.
    * */

    @Override
    public void init(ServletConfig config) {
        AppConfig.Init(config.getServletContext().getRealPath("/WEB-INF/app-config.xml"));
    }

    @Override
    public void destroy() {
        AppConfig.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/user/home/home.jsp").forward(req, resp);
    }
}
