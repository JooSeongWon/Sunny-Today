package xyz.sunnytoday.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        //RSA 키페어 복사
        final Object keyPair = session.getAttribute("rsaKeyPair");
        final Object publicKey = session.getAttribute("publicKey");

        //세션초기화
        session.invalidate();

        //RSA 키페어 입력
        session = req.getSession();
        session.setAttribute("rsaKeyPair", keyPair);
        session.setAttribute("publicKey", publicKey);

        //리다이렉트
        resp.sendRedirect(req.getContextPath() + "/");
    }
}