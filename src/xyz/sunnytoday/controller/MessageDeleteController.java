package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Message;
import xyz.sunnytoday.service.face.MessageService;
import xyz.sunnytoday.service.impl.MessageServiceImpl;

@WebServlet("/message/delete")
public class MessageDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MessageService messageService = new MessageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Message message = messageService.getMessage_No(req);
	
		messageService.deleteMessage(message);
		
		resp.sendRedirect("/message");
	}
}
