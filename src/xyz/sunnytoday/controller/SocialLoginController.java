package xyz.sunnytoday.controller;

import xyz.sunnytoday.dto.ResponseMessage;
import xyz.sunnytoday.service.face.MemberService;
import xyz.sunnytoday.service.impl.MemberServiceImpl;
import xyz.sunnytoday.service.impl.SocialLoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/login/naver", "/login/google"})
public class SocialLoginController extends HttpServlet {

    private final MemberService memberService = new MemberServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURL().toString();
        int lastSlashIndex = url.lastIndexOf('/');
        url = url.substring(lastSlashIndex + 1);


        //로그인 시도
        ResponseMessage responseMessage = memberService.loginSocial(req, url.equals("naver") ? SocialLoginServiceImpl.TYPE_NAVER : SocialLoginServiceImpl.TYPE_GOOGLE);

        if (!responseMessage.getResult()) {

            //미가입자
            if (responseMessage.getMsg().equals("미가입")) {
                req.getRequestDispatcher("/WEB-INF/views/user/member/join.jsp").forward(req, resp);
                return;
            }

            //소셜 인증실패 알럿
            resp.setContentType("text/html; charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<script>");
            writer.println("alert('" + responseMessage.getMsg() + "')");
            writer.print("location.href = \"");
            writer.println(req.getContextPath() + "/\";");
            writer.println("</script>");

        } else { //기존 소셜회원, 혹은 같은 이메일 계정 찾음 로그인 성공
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}
