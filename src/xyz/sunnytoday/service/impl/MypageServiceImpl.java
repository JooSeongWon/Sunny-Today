package xyz.sunnytoday.service.impl;

import java.io.File;
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

<<<<<<< HEAD
=======
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
>>>>>>> d567e8d14b7b5bda567b23c39c9eb843567d12a8

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.util.CipherUtil;
import xyz.sunnytoday.dao.face.MypageDao;
import xyz.sunnytoday.dao.impl.MypageDaoImpl;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Filee;
import xyz.sunnytoday.service.face.MypageService;

public class MypageServiceImpl implements MypageService {

    private MypageDao mypageDao = new MypageDaoImpl();
    
<<<<<<< HEAD
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
    
    
=======
    @Override
    public Member selectMember(int userno) {
    	Connection conn = JDBCTemplate.getConnection();
    	
    	//userno로 맴버 추출
    	Member member = mypageDao.selectMemberByUserno(conn, userno);
    	
    	JDBCTemplate.close(conn);
    	
    	return member;
    }
    
>>>>>>> d567e8d14b7b5bda567b23c39c9eb843567d12a8
    @Override//
    public int nickCheck(String nick) {
    	Connection conn = JDBCTemplate.getConnection();
    	
    	//닉네임 중복 확인
    	int result = mypageDao.nickCheck(conn, nick);
    	
    	JDBCTemplate.close(conn);
    	
    	return result;
    }
    
    
    @Override//
<<<<<<< HEAD
    public int phoneOpen(String phone, Member loginUser) {
=======
    public int phoneOpen(String phone, Member member) {
>>>>>>> d567e8d14b7b5bda567b23c39c9eb843567d12a8
    	Connection conn = JDBCTemplate.getConnection();
    	//return받을 결과값
    	int phoneOpen = mypageDao.updatePhoneOpen(conn, phone, member); 
    	
<<<<<<< HEAD
    	int phoneOpen = mypageDao.selectPhoneOpen(conn, phone, loginUser);
=======
    	//UPDATE 완료 시 1
    	if( phoneOpen > 0 ) {
    		JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
>>>>>>> d567e8d14b7b5bda567b23c39c9eb843567d12a8
    	
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
    	Filee file = null;
    	
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
		File repository = new File(req.getServletContext().getRealPath("tmp"));
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
			File upFolder = new File(req.getServletContext().getRealPath("upload"));
			upFolder.mkdir(); //폴더 생성
			
			//업로드 파일 객체
			String origin = item.getName(); //원본파일명
			String stored = origin + "_" + uid; //원본파일명_uid
			File up = new File(upFolder, stored);
			
			
			
			try {
				item.write(up); //실제 업로드(임시파일을 최종결과파일로 생성함)
				item.delete(); //임시파일을 삭제
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//업로드된 파일의 정보 저장
			file = new Filee();
			file.setOrigin_name(origin);
			file.setUrl(req.getServletContext().getRealPath("upload"));
			file.setThumbnail_url(req.getServletContext().getRealPath("upload")+stored);
			
		} //if( !item.isFormField() ) end
	} //while( iter.hasNext() ) end

		//DB연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
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
			
			if( mypageDao.insertFile(conn, file) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		
		JDBCTemplate.close(conn);
    }
    
<<<<<<< HEAD
    


=======
    @Override
    public boolean checkPassword(HttpServletRequest req) {
    	Connection conn = JDBCTemplate.getConnection();
    	
    	boolean user = false;
    	
    	String userId = req.getParameter("userId");
    	String userPw = req.getParameter("userPw");
    	
    	Member member = new Member();
    	
    	member = mypageDao.getsalt(userId, conn);
    	
    	if((!CipherUtil.encodeSha256(userPw, member.getSalt()).equals(member.getUserpw()))) {
    		user = false;
    	} else {
    		user = true;
    	}
    	
    	JDBCTemplate.close(conn);
    	
    	return user;
    }
    
 
>>>>>>> d567e8d14b7b5bda567b23c39c9eb843567d12a8
}