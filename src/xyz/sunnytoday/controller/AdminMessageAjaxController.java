package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.MessageEvent;
import xyz.sunnytoday.service.face.AdminMessageEventService;
import xyz.sunnytoday.service.impl.AdminMessageEventServiceImpl;


@WebServlet("/admin/message/ajax")
public class AdminMessageAjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminMessageEventService messageService = new AdminMessageEventServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<MessageEvent> elist = messageService.getMassageList(req);
		
		req.setAttribute("elist", elist);	
		
		req.getRequestDispatcher("/WEB-INF/views/admin/management/message/ajax/tablecontent.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<MessageEvent> elist = messageService.getMassageList(req);
		
		req.setAttribute("elist", elist);	
		
		req.getRequestDispatcher("/WEB-INF/views/admin/management/message/ajax/table.jsp").forward(req, resp);
				
	}
	
}
