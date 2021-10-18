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
		param.setUserno(Integer.parseInt(req.getParameter("user_no")));
		if(req.getParameter("Ban_type") == "login") {
			ban.setBan_type("L");
		}else {
			ban.setBan_type("W");
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("curent : " + df.format(cal.getTime()));
		if(req.getParameter("Ban_date") == "1week") {
			cal.add(Calendar.DATE, 7);
		}else if(req.getParameter("Ban_date") == "1month") {
			cal.add(Calendar.MONTH, 1);
		}else if(req.getParameter("Ban_date") == "3month") {
			cal.add(Calendar.MONTH, 3);
		}else if(req.getParameter("Ban_date") == "1year") {
			cal.add(Calendar.YEAR, 1);
		}else{
			cal.add(Calendar.YEAR, 9999);
		}
		
		System.out.println("after : " + df.format(cal.getTime()));
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date to = fm.parse(df.format(cal.getTime()));
			ban.setExpiry_date(to);
		} catch (ParseException e) {
			e.printStackTrace();
		} 

		memberService.insertBan(param, ban);
		memberService.updateExecuteResult(req);
		resp.sendRedirect("/admin/member/report");
	}
}
