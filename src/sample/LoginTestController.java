package sample;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/test/login")
public class LoginTestController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/test/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //테스트용이라 대충 했어요
        HttpSession session = req.getSession();
        if (session.getAttribute("userno") != null) { //새로고침, 로그아웃 판별

            if (req.getParameter("logout") != null) {//로그아웃
                session.invalidate();
            }

            doGet(req, resp);
            return;
        }

        session.setAttribute("userno",Integer.parseInt(req.getParameter("userno")));

        doGet(req, resp);
    }
}
