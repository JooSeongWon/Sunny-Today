package xyz.sunnytoday.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.MemberMenageDao;
import xyz.sunnytoday.dao.impl.MemberMenageDaoImpl;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.service.face.MemberMenageService;
import xyz.sunnytoday.util.Paging;

public class MemberMenageServiceImpl implements MemberMenageService {
	MemberMenageDao memberDao = new MemberMenageDaoImpl();
	@Override
	public List<Member> getMemberList(Paging paging) {
		Connection conn = JDBCTemplate.getConnection();
		List<Member> list = memberDao.getMemberList(conn, paging);
		JDBCTemplate.close(conn);
		return list;
	}
	@Override
	public Paging getPaging(HttpServletRequest req) {
		System.out.println("getPaging called");
		String param = req.getParameter("curPage");
		System.out.println("curPage : " + param );
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있음");
		}
		Connection conn = JDBCTemplate.getConnection();
		
		//member테이블의 총 게시글 수를 조회
		int totalCount = memberDao.selectCntAll(conn);
		
		//Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage);
		JDBCTemplate.close(conn);
		return paging;
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
	public List<Member> getSearchUserList(Member param, Paging paging) {
		System.out.println("getSearchUserList called");
		Connection conn = JDBCTemplate.getConnection();
		List<Member> list = memberDao.searchUser(param, paging);
		return null;
	}

}
