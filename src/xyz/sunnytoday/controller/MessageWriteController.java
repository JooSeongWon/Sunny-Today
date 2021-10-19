package xyz.sunnytoday.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import xyz.sunnytoday.dto.ResponseMessage;
import xyz.sunnytoday.service.face.MessageService;
import xyz.sunnytoday.service.impl.MessageServiceImpl;

@WebServlet("/message/write")
public class MessageWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//MessageService 객체 생성
	private MessageService messageService = new MessageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/message/send [GET]");
		
		req.getRequestDispatcher("/WEB-INF/views/user/message/messageWrite.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/message/send [POST]");
		
		
		resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
		final PrintWriter writer = resp.getWriter();
		
		
		try { //jsp 열어주세요
			messageService.postMessage(req);
		} catch(NullPointerException e) {
			writer.write(new Gson().toJson(new ResponseMessage(false, "없는 회원입니다.")));
			return;
		}
        
        writer.write(new Gson().toJson(new ResponseMessage(true, "쪽지를 발송했습니다.")));
	}
}
