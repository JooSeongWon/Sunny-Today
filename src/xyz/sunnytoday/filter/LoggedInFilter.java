package xyz.sunnytoday.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = {"/login/*", "/join", "/find"})
public class LoggedInFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (isLoggedIn((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse)) return;
        filterChain.doFilter(servletRequest, servletResponse);
    }

    //로그인된 상태 체크
    public boolean isLoggedIn(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession().getAttribute("userno") != null) { //이미 로그인 상태
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
