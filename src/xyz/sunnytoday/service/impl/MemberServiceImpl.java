package xyz.sunnytoday.service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.MemberDao;
import xyz.sunnytoday.dao.impl.MemberDaoImpl;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.service.face.MemberService;

public class MemberServiceImpl implements MemberService {

	//MemberDao 객체
	private MemberDao memberDao = new MemberDaoImpl();
	
	@Override
	public Member getLoginMember(HttpServletRequest req) {
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		Member member = new Member();
		
		member.setUserid( req.getParameter("userid") );
		member.setUserpw( req.getParameter("userpw") );
		
		return member;
	}

	@Override
	public boolean login(Member member) {

		if( memberDao.selectCntMemberByUseridUserpw(JDBCTemplate.getConnection(), member) > 0 ) {
			return true;	//로그인 성공
		} else {
			return false;	//로그인 실패
		}
	}

	@Override
	public Member info(Member member) {
		return memberDao.selectMemberByUserid(JDBCTemplate.getConnection(), member);
	}

	@Override
	public Member getJoinMember(HttpServletRequest req) {

		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		Member member = new Member();
		
		member.setUserid(req.getParameter("userid"));
		member.setUserpw(req.getParameter("userpw"));
		member.setEmail(req.getParameter("email"));
		member.setNick(req.getParameter("nick"));
		
		return member;
	}

	@Override
	public void join(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		
		if( memberDao.insert(conn, member) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	
	

}
