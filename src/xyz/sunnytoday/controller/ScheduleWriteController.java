package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.service.face.ScheduleService;
import xyz.sunnytoday.service.impl.ScheduleServiceImpl;

@WebServlet("/schedule/write")
public class ScheduleWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//서비스 객체 생성
	private ScheduleService scheduleService = new ScheduleServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//로그인 확인
		if(req.getSession().getAttribute("member") == null) {
			req.getRequestDispatcher("/WEB-INF/views/user/schedule/schedule_write.jsp").forward(req, resp);
        } else {
        	req.getRequestDispatcher("/WEB-INF/views/user/schedule/schedule_write.jsp").forward(req, resp);
        }
		
	}

}
