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

@WebServlet("/message/view")
public class MessageViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//MessageService 객체 생성
	private MessageService messageService = new MessageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/message/view [GET]");
		
		//전달파라미터 얻기 - message_no		
		Message message_no = messageService.getMessage_No(req);
		
		//상세보기 결과 조회
		Message viewMessage = messageService.view(message_no);
		
		//조회 결과 Model값 전달
		req.setAttribute("viewMessage", viewMessage);
		
		//보낸 사람 전달
		req.setAttribute("fromNick", messageService.getFromNick(viewMessage));
				
		req.getRequestDispatcher("/WEB-INF/views/user/message/messageDetail.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/message/view [POST]");
	
	}
}
