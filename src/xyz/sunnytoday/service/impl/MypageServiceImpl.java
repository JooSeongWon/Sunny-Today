package xyz.sunnytoday.service.impl;

import java.sql.Connection;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.MypageDao;
import xyz.sunnytoday.dao.impl.MypageDaoImpl;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.service.face.MypageService;

public class MypageServiceImpl implements MypageService {
	
	private MypageDao mypageDao = new MypageDaoImpl();
	
	@Override
	public Member selectMember(String loginUserId) {
		Connection conn = JDBCTemplate.getConnection();
		
		Member member = mypageDao.selectMemberById(conn, loginUserId);
		
		JDBCTemplate.close(conn);
		
		return member;
	}
	
}
