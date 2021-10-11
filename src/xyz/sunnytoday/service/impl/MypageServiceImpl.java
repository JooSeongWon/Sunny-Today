package xyz.sunnytoday.service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

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
    
    @Override
    public int nickCheck(String nick) {
    	Connection conn = JDBCTemplate.getConnection();
    	
    	int result = mypageDao.nickCheck(conn, nick);
    	
    	JDBCTemplate.close(conn);
    	
    	return result;
    }
    
    
    @Override
    public int phoneOpen(String phone, String loginUserId) {
    	Connection conn = JDBCTemplate.getConnection();
    	
    	int phoneOpen = mypageDao.selectPhoneOpen(conn, phone, loginUserId);
    	
    	JDBCTemplate.close(conn);
    	
    	return phoneOpen;
    }
    
    @Override
    public Member getchangeMember(HttpServletRequest req) {
		
    	try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
    	Member member = new Member();
    	
    	member.setNick(req.getParameter("nick"));
    	member.setPhone(req.getParameter("phone"));
//    	member.setBirth(req.getParameter("birth"));
    	
    	return member;
    }
    
    
    @Override
    public void change(Member param) {
    	
    	
    }

}