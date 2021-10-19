package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Event;
import xyz.sunnytoday.service.face.AdminMessageEventService;
import xyz.sunnytoday.service.impl.AdminMessageEventServiceImpl;

/**
 * Servlet implementation class AdminMessageEventTitleController
 */
@WebServlet("/admin/message/event/title")
public class AdminMessageEventTitleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminMessageEventService messageService = new AdminMessageEventServiceImpl();
	   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	      System.out.println("/admin/title [GET]");
	      
			List<Event> event = messageService.getEvent();
			
			req.setAttribute("event", event);
	      
	      req.getRequestDispatcher("/WEB-INF/views/admin/management/message/message_eventTitle.jsp").forward(req, resp);
    }
	   
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	      System.out.println("/admin/title [post]");
	      
	      messageService.titleWrite(req);
      
	      resp.sendRedirect("/admin/message/event");
   }

}
