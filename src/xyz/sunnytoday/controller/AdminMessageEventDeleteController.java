package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.MessageEvent;
import xyz.sunnytoday.service.face.AdminMessageEventService;
import xyz.sunnytoday.service.impl.AdminMessageEventServiceImpl;


@WebServlet("/admin/message/delete")
public class AdminMessageEventDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminMessageEventService messageService = new AdminMessageEventServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//전달파라미터
		MessageEvent message = messageService.getEno(req);
		
		messageService.delete(message);
		
		resp.sendRedirect("/admin/message/event");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//전달파라미터 message_e_no
		String arr[] = req.getParameterValues("delno[]");
		if(arr != null) {
		int cnt = arr.length;
		int[] arr2 = new int[cnt];
		
			for (int i = 0; i < arr.length; i++) {
				arr2[i] = Integer.parseInt(arr[i]);
				System.out.println(arr2[i]);
            	messageService.deleteAll(arr2[i]);
        	}
        }
		
		resp.sendRedirect("/admin/message/event");
	}
	
}
