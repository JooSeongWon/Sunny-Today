package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.service.face.AdminMessageService;
import xyz.sunnytoday.service.impl.AdminMessageServiceImpl;


@WebServlet("/admin/message/write")
public class AdminMessageWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminMessageService messageService = new AdminMessageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/message/write [GET]");
		
		//전달파라미터 얻기
		int[] userno = messageService.getParam(req);
		
		List<Member> list = messageService.getlist(userno);
		
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/views/admin/management/message/message_sendWrite.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/message/write [POST]");
		
	}
}
