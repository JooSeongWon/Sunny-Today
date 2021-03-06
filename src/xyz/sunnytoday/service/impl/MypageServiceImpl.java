package xyz.sunnytoday.service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.util.CipherUtil;
import xyz.sunnytoday.common.util.ThumbnailMaker;
import xyz.sunnytoday.dao.face.MypageDao;
import xyz.sunnytoday.dao.impl.MypageDaoImpl;
import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.service.face.MypageService;

public class MypageServiceImpl implements MypageService {

    private MypageDao mypageDao = new MypageDaoImpl();
    
    @Override
    public Member selectMember(int userno) {
    	Connection conn = JDBCTemplate.getConnection();
    	    	
    	//userno로 맴버 추출
    	Member member = mypageDao.selectMemberByUserno(conn, userno);
    	
    	JDBCTemplate.close(conn);
    	
    	return member;
    }
    
    @Override//
    public int nickCheck(String nick) {
    	Connection conn = JDBCTemplate.getConnection();
    	
    	//닉네임 중복 확인
    	int result = mypageDao.nickCheck(conn, nick);
    	
    	JDBCTemplate.close(conn);
    	
    	return result;
    }
    
    
    @Override//
    public int phoneOpen(String phone, Member member) {
    	Connection conn = JDBCTemplate.getConnection();
    	//return받을 결과값
    	int phoneOpen = mypageDao.updatePhoneOpen(conn, phone, member); 
    	
    	//UPDATE 완료 시 1
    	if( phoneOpen > 0 ) {
    		JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
    	
    	JDBCTemplate.close(conn);
    	return phoneOpen;
    }
    
    @Override
    public int birthOpen(String birth, Member member) {
    	Connection conn = JDBCTemplate.getConnection();
    	//return받을 결과값
    	int birthOpen = mypageDao.updateBirthOpen(conn, birth, member); 
    	
    	//UPDATE 완료 시 1
    	if( birthOpen > 0 ) {
    		JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
    	
    	JDBCTemplate.close(conn);
    	return birthOpen;
    }
    
       
    
    @Override
    public void update(HttpServletRequest req) {
    	
    	//유저 정보 DTO 객체
    	Member member = null;
    	//사진파일 정보 DTO 객체
    	File file = null;
    	
		//DB연결 객체
		Connection conn = JDBCTemplate.getConnection();
    	
		//파일업로드 형태의 데이터가 맞는지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if( !isMultipart ) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
			return; //write() 메소드 중단
		}
		
		member = new Member();
		
		//디스크기반 아이템 팩토리
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//메모리 처리 사이즈 지정
		factory.setSizeThreshold(1 * 1024 * 1024); //1MB

		//임시 저장소 설정
		java.io.File repository = new java.io.File(req.getServletContext().getRealPath("tmp"));
		repository.mkdir(); //임시 저장소 폴더 생성
		factory.setRepository(repository); //임시 저장소 폴더 지정
		
		
		//파일업로드 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);
		//업로드 용량 제한
		upload.setFileSizeMax(10 * 1024 * 1024); //10MB
		
		//전달 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		//파싱된 전달파라미터를 처리할 반복자
		Iterator<FileItem> iter = items.iterator();

		while( iter.hasNext() ) { //모든 요청 정보 처리
			FileItem item = iter.next();
			
			//--- 1) 빈 파일에 대한 처리 ---
			if( item.getSize() <= 0 ) {
				continue; //빈 파일은 무시하고 다음 FileItem처리로 넘긴다
			}
			
			//--- 2) form-data에 대한 처리 ---
			if( item.isFormField() ) {
				//키 추출하기
				String key = item.getFieldName();
				//값 추출하기
				String value = null;
				try {
					value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				
				//키(name)에 따라서 value저장하기
				if( "nick".equals(key) ) {
					member.setNick(value);
					System.out.println( member.getNick() );
				} else if( "phone".equals(key) ) {
					member.setPhone( value );
				} else if( "birth".equals(key) ) {
					
					//DateFormatter 형식지정
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
					//String 타입 value LocalDate로 변환
					LocalDate date = LocalDate.parse(value, formatter);
					//LocalDate 타입 date Date로 변환
					Date date2 = java.sql.Date.valueOf(date);
					
					member.setBirth( date2 );
				}
				
			} //if( item.isFormField() ) end
		
		//--- 3) 파일에 대한 처리 ---
		if( !item.isFormField() ) {
			
			//UUID 생성
			UUID uuid = UUID.randomUUID(); //랜덤 UUID
			String uid = uuid.toString().split("-")[0]; //8자리 uuid
			
			//로컬 저장소의 업로드 폴더
			java.io.File upFolder = new java.io.File(req.getServletContext().getRealPath("upload"));
			upFolder.mkdir(); //폴더 생성
			
			//업로드 파일 객체
			String origin = item.getName(); //원본파일명
			int dotIndex = origin.lastIndexOf(".");
			String extention = origin.substring(dotIndex);
			String stored = origin.substring(0, dotIndex) + "_" + uid + extention; //원본파일명_uid
			java.io.File up = new java.io.File(upFolder, stored);
			
			String thumbnail = null;
			try {
				item.write(up); //실제 업로드(임시파일을 최종결과파일로 생성함)
				thumbnail = ThumbnailMaker.makeThumbnail(stored, req.getServletContext().getRealPath("upload"), 40, 40); 
				item.delete(); //임시파일을 삭제
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//업로드된 파일의 정보 저장
			file = new File();
			file.setOrigin_name(origin);
			file.setUrl(stored);
			file.setThumbnail_url(thumbnail);
			
		} //if( !item.isFormField() ) end
	} //while( iter.hasNext() ) end

		int file_no = mypageDao.selectNextFile_no(conn);
		
		//유저 정보가 있을 경우
		if(member != null) {
			
			//유저 정보 받기
			Object param = req.getSession().getAttribute("userno");
			int userno = (int) param;
			
			member.setUserno(userno);
			
			if( mypageDao.update(conn, member) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		//첨부파일 정보가 있을 경우
		if(file != null) {
			
			file.setUser_no(member.getUserno()); //유저 번호 입력 (FK)
			file.setFile_no(file_no);
			member.setPictureno(file_no);
			
			if( mypageDao.insertFile(conn, file) > 0 && mypageDao.insertPicture(conn, member) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		
		JDBCTemplate.close(conn);
    }
    
    @Override
    public File selectProfile(Member member) {
    	Connection conn = JDBCTemplate.getConnection();
    	
    	File profile = mypageDao.selectFile(conn, member);
    	
		JDBCTemplate.close(conn);
    	
    	return profile;
    }
    
    @Override
    public int checkPassword(HttpServletRequest req) {
    	Connection conn = JDBCTemplate.getConnection();
    	
    	int res = 0;
    	
    	String userId = req.getParameter("userid");
    	String userPw = req.getParameter("userpw");
    	
    	if(userPw != null && !"".equals(userPw)){
    		Member member = new Member();
    		member = mypageDao.getsalt(userId, conn);
    		if(!CipherUtil.encodeSha256(userPw, member.getSalt()).equals(member.getUserpw())) {
    			res = 1 ;
    		} else {
    			res = 3;
    		} 
    	}
    	JDBCTemplate.close(conn);
    	
    	return res;
    }
    
    @Override
    public int createpw(HttpServletRequest req, int userno) {
    	Connection conn = JDBCTemplate.getConnection();
    	String userPw = req.getParameter("newPassword");
    	String salt = CipherUtil.getSalt();
    	String newpw = CipherUtil.encodeSha256(userPw,salt);
    	
    	int res = mypageDao.insertNewPw(userno, conn , newpw, salt);
    	if( res > 0 ) {
    		JDBCTemplate.commit(conn);
    	} else {
			JDBCTemplate.rollback(conn);
		}
    	JDBCTemplate.close(conn);
    	return res;
    }
    
    
    @Override
    public int updatePw(HttpServletRequest req, int userno) {
    	Connection conn = JDBCTemplate.getConnection();
    	String userPw = req.getParameter("password");
    	Member member = new Member();
    	member = mypageDao.getsalt(userno, conn);
    	String newpw = CipherUtil.encodeSha256(userPw, member.getSalt());
    	
    	int res = mypageDao.insertPw(userno, conn , newpw);
    	
    	if( res > 0 ) {
    		JDBCTemplate.commit(conn);
    	} else {
			JDBCTemplate.rollback(conn);
		}
    	
    	JDBCTemplate.close(conn);
    	return res;
    }
    
    @Override
    public void delMember(int userno) {
    	Connection conn = JDBCTemplate.getConnection();
    	
    	if( mypageDao.deleteMember(userno,conn) > 0) {
    		JDBCTemplate.commit(conn);
    	} else {
    		JDBCTemplate.rollback(conn);
    	}
    	
    	JDBCTemplate.close(conn);
    }
 
}