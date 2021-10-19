package xyz.sunnytoday.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(urlPatterns = {"/message/*", "/logout", "/messagesend", "/mypage/*" , "/schedule/*", "/board/detail/*", "/board/write", "/board/list/mine"})
public class NoLoginFilter implements Filter {

	    @Override
	    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	        if (isLoggedIn((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse)) return;
	        filterChain.doFilter(servletRequest, servletResponse);
	    }

	    //로그인 안된 상태 체크
	    public boolean isLoggedIn(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	        if (req.getSession().getAttribute("userno") == null) {
	            resp.setContentType("text/html; charset=utf-8");

	            PrintWriter writer = resp.getWriter();
	            writer.println("<script>");
	            writer.println("alert('로그인이 필요합니다.')");
	            writer.print("location.href =\"");
	            writer.println(req.getContextPath() + "/login\";");
	            writer.println("</script>");
	            return true;
	        }
	        return false;
	    }

}
