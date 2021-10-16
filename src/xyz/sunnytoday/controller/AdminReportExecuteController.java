package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.service.face.MemberMenageService;
import xyz.sunnytoday.service.impl.MemberMenageServiceImpl;

/**
 * Servlet implementation class AdminReportExecuteController
 */
@WebServlet("/admin/report/execute")
public class AdminReportExecuteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    MemberMenageService memberService = new MemberMenageServiceImpl();
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("/admin/report/execute [GET]");
    	List<Map<String, Object>> list = memberService.getReportDatil(req);
    	
    	req.setAttribute("list", list);
    	req.getRequestDispatcher("/WEB-INF/views/admin/member/report_execute.jsp").forward(req, resp);
    }
    
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/report/execute [POST]");
		req.setCharacterEncoding("UTF-8");

		memberService.insertBan(req);
		memberService.updateExecuteResult(req);
		resp.sendRedirect("/admin/member/report");
	}
}
