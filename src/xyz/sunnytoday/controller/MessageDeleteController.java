package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Message;
import xyz.sunnytoday.dto.Question;
import xyz.sunnytoday.service.face.MessageService;
import xyz.sunnytoday.service.impl.MessageServiceImpl;

@WebServlet("/message/delete")
public class MessageDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MessageService messageService = new MessageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Message message = messageService.getMessage_No(req);
	
		messageService.delete(message);
		
		resp.sendRedirect("/message");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("받은 데이터 " + req.getParameter("delno"));
		req.setCharacterEncoding("UTF-8");
		String chNum = null; // 받아올 파라미터의 이름 설정
		
		int cntRow = messageService.cntList(req);
		
		int count = 0;
		
		//선택한 항목의 갯 수 확인
		for(int i=0; i < cntRow; i++) {
			
			chNum = "delno" + i; // 파라미터의 뒷번호를 for문으로 자동 생성
			if(req.getParameter(chNum) != null && !"".equals(req.getParameter(chNum))) {
				  count++; //선택된 항목이 있다면 카운트 증가
				  System.out.println("req.getParameter(chNum) : " + req.getParameter(chNum));
			}
		}
		
		System.out.println("count : " + count);
		
		//항목의 갯수가 0이 아니면 실행
		if(count != 0) {
			//항목을 저장할 배열 생성
			int[] message_no = new int[count];//카운트 값을 넣어 에러가 나지 않도록 처리
			int cnt = 0;
			
			//요청 객체의 모든 값을 조회해야 선택된 값인지 판별이 가능합니다.
			for(int i=0; i < cntRow; i++) {
				chNum = "delno" + i;
				
				if(req.getParameter(chNum) != null && !"".equals(req.getParameter(chNum))) {
					message_no[cnt] = Integer.parseInt(req.getParameter(chNum));  
					cnt++;
				}
			}
			
			cnt = 0;
			Message message = new Message();
			
			for(int i=0; i < message_no.length; i++) {
				message.setMessage_no(message_no[i]);
				messageService.deleteMessage(message);
			}			
		}
		
		resp.sendRedirect("/message");	
	}
}
