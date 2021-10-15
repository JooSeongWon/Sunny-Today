//package xyz.sunnytoday.controller;
//
//import java.io.IOException;
//
//
//
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import xyz.sunnytoday.dto.Board;
//import xyz.sunnytoday.service.face.AdminBoardService;
//import xyz.sunnytoday.service.impl.AdminBoardServiceImpl;
//
//@WebServlet("/admin/board/view")
//public class AdminBoardViewController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	private final AdminBoardService boardService = new AdminBoardServiceImpl();
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	
//		//전달파라미터 얻기 - board_no
//		Board board_no = boardService.getBoardno(req);
//
//		//상세보기 결과 조회
//		Board viewBoard = boardService.view(board_no);
//		
//		req.setAttribute("viewBoard", viewBoard);
//
//		req.getRequestDispatcher("/WEB-INF/views/admin/view.jsp").forward(req, resp);		
//	}
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
////		boardService.view(req);
//		
//		resp.sendRedirect("/SunnyToday/admin/board/list");
//	}
//
//}

