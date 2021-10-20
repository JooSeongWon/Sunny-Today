package xyz.sunnytoday.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Ban;
import xyz.sunnytoday.dto.Member;
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
		Member param = new Member();
		Ban ban = new Ban();
		System.out.println("target : " +req.getParameter("target_no"));
		
		int user_no = Integer.parseInt(req.getParameter("target_no"));
		param.setUserno(user_no);
		
		if(req.getParameter("Ban_type") == "login") {
			ban.setBan_type("L");
		}else {
			ban.setBan_type("W");
		}
		
		int date = 0;
		if("1week".equals(req.getParameter("Ban_date"))) {
			date = 7;
		}else if("1month".equals(req.getParameter("Ban_date"))) {
			date = 30;
		}else if("3month".equals(req.getParameter("Ban_date"))) {
			date = 60;
		}else if("1year".equals(req.getParameter("Ban_date"))) {
			date = 365;
		}else{
			date = 9999;
		}

		memberService.insertBan(param, ban, date);
		memberService.updateExecuteResult(req);
		resp.sendRedirect("/admin/member/report");
	}
}
