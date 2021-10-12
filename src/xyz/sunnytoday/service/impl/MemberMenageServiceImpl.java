package xyz.sunnytoday.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.MemberMenageDao;
import xyz.sunnytoday.dao.face.QuestionMenageDao;
import xyz.sunnytoday.dao.impl.MemberMenageDaoImpl;
import xyz.sunnytoday.dao.impl.QuestionMenageDaoImpl;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Question;
import xyz.sunnytoday.service.face.MemberMenageService;
import xyz.sunnytoday.util.Paging;

public class MemberMenageServiceImpl implements MemberMenageService {
	MemberMenageDao memberDao = new MemberMenageDaoImpl();
	QuestionMenageDao questionDao = new QuestionMenageDaoImpl();
	@Override
	public Paging getPaging(HttpServletRequest req, Member param, String location) {
		System.out.println("getMemberPaging called");
		String page = req.getParameter("curPage");
		System.out.println("curPage : " + page );
		System.out.println("location : " + location);
		int curPage = 0;
		if(page != null && !"".equals(page)) {
			curPage = Integer.parseInt(page);
		}else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있음");
		}
		
		Connection conn = JDBCTemplate.getConnection();
		
		int totalCount = 0; 
		if(location == "member") {
			System.out.println("paging member");
			if(param.getUserid() != null && !"".equals(param.getUserid())) {
				//id로 조회된 총 회원 수 조회
				totalCount = memberDao.selectIdCntAll(conn, param);
			}else if(param.getNick() != null && !"".equals(param.getNick())) {
				//nick으로 검색된 총 회원 수 조회
				totalCount = memberDao.selectNickCntAll(conn, param);
			}else {
				//member테이블의 총 회원수 조회
				totalCount = memberDao.selectCntAll(conn);		
			}
		}else if(location == "question") {
			System.out.println("paging question");
			if(param.getUserid() != null && !"".equals(param.getUserid())) {
				//id로 조회된 총 회원 수 조회
				totalCount = questionDao.selectIdCntAll(conn, param);
			}else if(param.getNick() != null && !"".equals(param.getNick())) {
				//nick으로 검색된 총 회원 수 조회
				totalCount = questionDao.selectNickCntAll(conn, param);
			}else {
				//member테이블의 총 회원수 조회
				totalCount = questionDao.selectCntAll(conn);		
			}
		}else {
			System.out.println("location [Error]");
		}
		
		//Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage);
		
		JDBCTemplate.close(conn);
		
		return paging;
	}
	
	@Override
	public List<Member> getUserList(Member param, Paging paging) {
		System.out.println("getSearchUserList called");
		Connection conn = JDBCTemplate.getConnection();
		System.out.println("param : " + param);
		List<Member> list = null;
		if(param.getUserid() != null && !"".equals(param.getUserid())) {
			list = memberDao.searchUserId(param, paging, conn);
		}else if(param.getNick() != null && !"".equals(param.getNick())) {
			list = memberDao.searchUserNick(param, paging, conn);
		}else {
			list = memberDao.getMemberList(conn, paging);
		}
		
		JDBCTemplate.close(conn);
		return list;
	}
	
	@Override
	public Member getMemberDetailList(HttpServletRequest req) {
		System.out.println("getMemberDetailList called");
		Connection conn = JDBCTemplate.getConnection();
		Member param = new Member();
		param.setUserno(Integer.parseInt(req.getParameter("userno")));
		param = memberDao.selectUserDatail(param, conn);
		
		JDBCTemplate.close(conn);
		return param;
	}

	@Override
	public List<Question> getQuestionList(Member param, Paging paging) {
		System.out.println("getQuestionList");
		Connection conn = JDBCTemplate.getConnection();
		List<Question> list = null;
		if(param.getUserid() != null && !"".equals(param.getUserid())) {
			list = questionDao.searchUserId(param, paging, conn);
		}else if(param.getNick() != null && !"".equals(param.getNick())) {
			list = questionDao.searchUserNick(param, paging, conn);
		}else {
			list = questionDao.getQuestionList(conn, paging);
		}
		
		JDBCTemplate.close(conn);
		return list;
	}

	@Override
	public Question getQuestionDetail(HttpServletRequest req, Question param) {
		System.out.println("getQuestionDetail called");
	
		if(req.getParameter("question_no") != null && !"".equals(req.getParameter("question_no"))) {
			param.setQuestion_no(Integer.parseInt(req.getParameter("question_no")));
			Connection conn = JDBCTemplate.getConnection();
			param = questionDao.getQuestionDatil(conn, param);
			
			JDBCTemplate.close(conn);
			return param;
		}else {
			System.out.println("question_no가 null이거나 값이 존재 하지 않습니다.");
			return null;
		}

	}

	@Override
	public void updateAnswer(Question param) {
		System.out.println("updateAnswer called");
		Connection conn = JDBCTemplate.getConnection();
		int res = 0;
		res = questionDao.setUpdateAnswer(conn, param);
		if(res != 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
	}

}
