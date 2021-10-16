package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.service.face.MessageService;
import xyz.sunnytoday.service.impl.MessageServiceImpl;

@WebServlet("/message/send")
public class MessageSendController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//MessageService 객체 생성
	private MessageService messageService = new MessageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/message/send [GET]");
		
		req.getRequestDispatcher("/WEB-INF/views/user/message/messageSend.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/message/write [POST]");
		
		messageService.postMessage(req);
		
		resp.sendRedirect("/message");
	}
}
