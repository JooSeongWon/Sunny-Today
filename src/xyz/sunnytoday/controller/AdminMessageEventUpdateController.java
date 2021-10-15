package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Event;
import xyz.sunnytoday.dto.MessageEvent;
import xyz.sunnytoday.service.face.AdminMessageEventService;
import xyz.sunnytoday.service.impl.AdminMessageEventServiceImpl;


@WebServlet("/admin/message/update")
public class AdminMessageEventUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminMessageEventService messageService = new AdminMessageEventServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/message/update [GET]");
		
		//전달파라미터
		MessageEvent messageno = messageService.getEno(req);
		
		//전달파라미터로 얻은 메세지 정보
		MessageEvent message = messageService.view(messageno);
		
		//이벤트 리스트
		List<Event> event = messageService.getEvent();
		
		req.setAttribute("event", event);
		
		req.setAttribute("message", message);
		
		req.getRequestDispatcher("/WEB-INF/views/admin/management/message/message_eventUpdate.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/message/update [Post]");
		
		messageService.update(req);
		
		resp.sendRedirect("/admin/message/event");
	}
}
