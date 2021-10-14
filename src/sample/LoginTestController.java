package sample;

import xyz.sunnytoday.dto.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test/login")
public class LoginTestController extends HttpServlet {

    private final MemberServiceSample memberServiceSample = new MemberServiceSampleImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (checkLogin(req, resp)) return;

        req.getRequestDispatcher("/WEB-INF/views/test/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //테스트용이라 대충 했어요
        HttpSession session = req.getSession();
        if (checkLogin(req, resp)) return;

        String userId = req.getParameter("userId");
        String userPw = req.getParameter("userPw");

        if (!memberServiceSample.login(userId, userPw)) { //로그인 실패
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("text/html; charset=utf-8");

            PrintWriter writer = resp.getWriter();
            writer.println("<script>");
            writer.println("alert('아이디 혹은 패스워드가 정확하지 않습니다.')");
            writer.print("location.href = \"");
            writer.println(req.getContextPath() + "/test/login\";");
            writer.println("</script>");

            return;
        }

        Member member = memberServiceSample.getMemberByUserIdOrNull(userId);
        session.setAttribute("userno", member.getUserno());
        session.setAttribute("nick", member.getNick());

        resp.sendRedirect(req.getContextPath()+"/");
    }

    public static boolean checkLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("userno") != null) { //이미 로그인 상태
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("text/html; charset=utf-8");

            PrintWriter writer = resp.getWriter();
            writer.println("<script>");
            writer.println("alert('잘못된 접근입니다.')");
            writer.print("location.href = \"");
            writer.println(req.getContextPath() + "/\";");
            writer.println("</script>");
            return true;
        }
        return false;
    }
}
