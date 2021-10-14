package sample;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/test/join")
public class JoinTestController extends HttpServlet {

    private final MemberServiceSample memberServiceSample = new MemberServiceSampleImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(LoginTestController.checkLogin(req,resp)) return;
        req.getRequestDispatcher("/WEB-INF/views/test/join.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(LoginTestController.checkLogin(req,resp)) return;

        try {
            memberServiceSample.join(req);
        }catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("text/html; charset=utf-8");

            PrintWriter writer = resp.getWriter();
            writer.println("<script>");
            writer.println("alert('잘못 입력한 데이터가 있습니다.')");
            writer.print("location.href = \"");
            writer.println(req.getContextPath() + "/test/join\";");
            writer.println("</script>");

            e.printStackTrace();
            return;
        }

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html; charset=utf-8");

        PrintWriter writer = resp.getWriter();
        writer.println("<script>");
        writer.println("alert('회원가입 성공')");
        writer.print("location.href = \"");
        writer.println(req.getContextPath() + "/test/login\";");
        writer.println("</script>");
    }
}
