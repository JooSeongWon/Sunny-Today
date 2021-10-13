package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.MessageEvent;
import xyz.sunnytoday.service.face.MessageEventService;
import xyz.sunnytoday.service.impl.MessageEventServiceImpl;
import xyz.sunnytoday.util.Paging;

@WebServlet("/admin/message/event")
public class MessageEventController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MessageEventService messageService = new MessageEventServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/message/event [GET]");
		
		//요청파라미터를 전달하여 Paging객체 생성하기
		Paging paging = messageService.getPaging(req);
		
		//메시지이벤트 조회
		List<MessageEvent> list = messageService.getlist(req, paging);
		
		
		req.setAttribute("list", list);
		req.setAttribute("paging", paging);
		
		req.getRequestDispatcher("/WEB-INF/views/admin/management/message/message_event.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/message/event [POST]");
		
	}
}
