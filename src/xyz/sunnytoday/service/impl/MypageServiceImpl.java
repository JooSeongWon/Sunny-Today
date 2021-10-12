package xyz.sunnytoday.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.MypageDao;
import xyz.sunnytoday.dao.impl.MypageDaoImpl;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.service.face.MypageService;

public class MypageServiceImpl implements MypageService {

    private MypageDao mypageDao = new MypageDaoImpl();
    
    @Override//
    public Member getUser(HttpServletRequest req) {
    	//userno를 저장할 객체 생성
    	Member userno = new Member();
    	
    	//userno 전달파라미터 검증 - null, ""
    	String param = (String) req.getSession().getAttribute("userno");
    	
    	if(param!= null && !"".equals(param)) {
    		
    		//userno 추출
    		userno.setUserno(Integer.parseInt(param));
    	}
    	
    	return userno;
    }
    
    @Override//
    public Member selectMember(Member loginUser) {
   	   	
    	Connection conn = JDBCTemplate.getConnection();
        
   	   	//userno로 맴버 추출
   	   	Member member = mypageDao.selectMemberByUserno(conn, loginUser);
   		
       	JDBCTemplate.close(conn);

		return member;
    }
    
    @Override
    public void update(HttpServletRequest req) {
    	
    	Member member = null;
    	
    	File file = null;
    	
    	//파일업로드 형태의 데이터가 맞는지 검사
    	boolean isMultipart = false;
//    	isMultipart = ServletFileUpload.isMultipartContent(req);
    	
    	if( !isMultipart ) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
			
			return; //write() 메소드 중단
		}
    	
    	member = new Member();
    	
    	
    }
    
    
    @Override//
    public int nickCheck(String nick) {
    	Connection conn = JDBCTemplate.getConnection();
    	
    	int result = mypageDao.nickCheck(conn, nick);
    	
    	JDBCTemplate.close(conn);
    	
    	return result;
    }
    
    
    @Override//
    public int phoneOpen(String phone, Member loginUser) {
    	Connection conn = JDBCTemplate.getConnection();
    	
    	int phoneOpen = mypageDao.selectPhoneOpen(conn, phone, loginUser);
    	
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
    
    


}