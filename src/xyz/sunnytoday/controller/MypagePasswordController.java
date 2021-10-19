package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.service.face.MypageService;
import xyz.sunnytoday.service.impl.MypageServiceImpl;

@WebServlet("/mypage/password")
public class MypagePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MypageService mypageService = new MypageServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/mypage/password [GET]");
		// 로그인 유저 세션의 유저넘버 얻기
		Object param = req.getSession().getAttribute("userno");
		int userno = (int) param;
		
		//유저넘버로 유저정보 얻기 - member
		Member member = mypageService.selectMember(userno);
		
		//유저정보 전달
		req.setAttribute("member", member);
		
		req.getRequestDispatcher("/WEB-INF/views/user/mypage/change_Password.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/mypage/password [POST]");

		// 로그인 유저 세션의 유저넘버 얻기
		Object param = req.getSession().getAttribute("userno");
		int userno = (int) param;

		String pwRegex = "^(?=.*[a-zA-Z0-9$`~!@$!%*#^?&])(?!.*[^a-zA-Z0-9$`~!@$!%*#^?&]).{8,20}$";

		int res = 0;
		if (req.getParameter("passwordcheck") != null && Pattern.matches(pwRegex, req.getParameter("passwordcheck"))) {
			if (req.getParameter("newPassword") != null && !"".equals(req.getParameter("newPassword"))) {
				if (req.getParameter("newPassword").equals(req.getParameter("passwordcheck"))) {
					res = mypageService.createpw(req, userno);

					// json 형식으로 변환
					Gson gson = new Gson();
					String rs = gson.toJson(res);

					// 전송이 되는 부분
					resp.getWriter().write(rs);

				} else {
					res = 2;

					// json 형식으로 변환
					Gson gson = new Gson();
					String rs = gson.toJson(res);

					// 전송이 되는 부분
					resp.getWriter().write(rs);

				}
			}

			if (req.getParameter("password") != null && !"".contentEquals(req.getParameter("password"))) {
				if (req.getParameter("password").equals(req.getParameter("passwordcheck"))) {
					res = mypageService.updatePw(req, userno);

					// json 형식으로 변환
					Gson gson = new Gson();
					String rs = gson.toJson(res);

					// 전송이 되는 부분
					resp.getWriter().write(rs);

				} else {
					res = 2;

					// json 형식으로 변환
					Gson gson = new Gson();
					String rs = gson.toJson(res);

					// 전송이 되는 부분
					resp.getWriter().write(rs);

				}
			}

		}
		
		// json 형식으로 변환
		Gson gson = new Gson();
		String rs = gson.toJson(res);

		// 전송이 되는 부분
		resp.getWriter().write(rs);

	}
}
