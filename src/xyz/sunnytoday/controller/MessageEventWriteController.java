package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Event;
import xyz.sunnytoday.service.face.MessageEventService;
import xyz.sunnytoday.service.impl.MessageEventServiceImpl;


@WebServlet("/admin/message/write")
public class MessageEventWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MessageEventService messageService = new MessageEventServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/message/write [GET]");
		
		List<Event> event = messageService.getEvent();
		
		req.setAttribute("event", event);
		
		req.getRequestDispatcher("/WEB-INF/views/admin/management/message/message_eventWrite.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		messageService.write(req);
		
		resp.sendRedirect("/admin/message/event");
	}
	
}
