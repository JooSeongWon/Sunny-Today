package xyz.sunnytoday.controller;

import xyz.sunnytoday.service.face.VerificationService;
import xyz.sunnytoday.service.impl.VerificationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/verification")
public class VerificationController extends HttpServlet {

    VerificationService verificationService = new VerificationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //회원가입 인증
        if (req.getParameter("join") != null) {
            if (verificationService.verifyEmailForMemberJoin(req)) {//가입성공
                req.getRequestDispatcher("/WEB-INF/views/user/member/join-complete.jsp").forward(req, resp);
                return;
            }
        }
        
        //인증실패
        req.getRequestDispatcher("/WEB-INF/views/user/verification/fail.jsp").forward(req, resp);
    }
}
