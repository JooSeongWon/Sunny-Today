package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD:src/xyz/sunnytoday/controller/MessageController.java
@WebServlet("/message")
public class MessageController extends HttpServlet {
=======
/**
 * Servlet implementation class AdminConnAnalysisController
 */
@WebServlet("/admin/conn/analysis")
public class AdminConnAnalysisController extends HttpServlet {
>>>>>>> d567e8d14b7b5bda567b23c39c9eb843567d12a8:src/xyz/sunnytoday/controller/AdminConnAnalysisController.java
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
<<<<<<< HEAD:src/xyz/sunnytoday/controller/MessageController.java
		System.out.println("/message [GET]");
		
		req.getRequestDispatcher("/WEB-INF/views/user/message/message.jsp").forward(req, resp);		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
=======
		System.out.println(req.getHeader("referer"));
		req.getRequestDispatcher("/WEB-INF/views/admin/accessor/conn_graph.jsp").forward(req, resp);
>>>>>>> d567e8d14b7b5bda567b23c39c9eb843567d12a8:src/xyz/sunnytoday/controller/AdminConnAnalysisController.java
	}
}