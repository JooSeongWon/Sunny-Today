package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Message;
import xyz.sunnytoday.service.face.MemberService;
import xyz.sunnytoday.service.face.MessageService;
import xyz.sunnytoday.service.impl.MemberServiceImpl;
import xyz.sunnytoday.service.impl.MessageServiceImpl;

@WebServlet("/message")
public class MessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//MessageService 객체 생성
	private MessageService messageService = new MessageServiceImpl();
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/message [GET]");
		
		//요청파라미터를 전달하여 Paging객체 생성하기
		Paging paging = messageService.getPaging(req);
		System.out.println("MessageController [GET] - " + paging);
	
		//받은 쪽지 목록 조회
		List<Message> messageList = messageService.getMessageList(paging, (Integer) req.getSession().getAttribute("userno")); 
		
		//조회결과 MODEL값 전달
		req.setAttribute("messageList", messageList);
		
		//페이징 정보 MODEL값 전달
		req.setAttribute("paging", paging); 

    	req.getRequestDispatcher("/WEB-INF/views/user/message/message.jsp").forward(req, resp);				
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/message [POST]");
	
	}
}
