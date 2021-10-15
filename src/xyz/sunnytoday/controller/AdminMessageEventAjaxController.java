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

@WebServlet("/admin/message/event/ajax")
public class AdminMessageEventAjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminMessageEventService messageService = new AdminMessageEventServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<MessageEvent> elist = messageService.getEventList(req) ;
		
		System.out.println(elist);
		
		req.setAttribute("elist", elist);	
		
		req.getRequestDispatcher("/WEB-INF/views/admin/management/message/ajax/selectBox.jsp").forward(req, resp);
	}
}
