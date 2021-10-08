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
	public List<Member> getMemberList() {
		Connection conn = JDBCTemplate.getConnection();
		
		return memberDao.getMemberList(conn);
	}
	@Override
	public Paging getPaging(HttpServletRequest req) {
		System.out.println("getPaging called");
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있음");
		}
		Connection conn = JDBCTemplate.getConnection();
		
		//Board테이블의 총 게시글 수를 조회
		int totalCount = memberDao.selectCntAll(conn);
		
		//Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}

}
