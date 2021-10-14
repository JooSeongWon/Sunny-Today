package xyz.sunnytoday.service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.common.util.ThumbnailMaker;
import xyz.sunnytoday.dao.face.BoardDao;
import xyz.sunnytoday.dao.impl.BoardDaoImpl;
import xyz.sunnytoday.dto.Board;
import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.dto.PostFile;
import xyz.sunnytoday.service.face.BoardService;

public class BoardServiceImpl implements BoardService {
	
	BoardDao boardDao = new BoardDaoImpl();
	
	@Override
	public List<Map<String, Object>> getList(Paging paging) {
		
		Connection conn = JDBCTemplate.getConnection();
		
//		JDBCTemplate.close(conn);
		return boardDao.selectMainListAll(conn, paging);
		
	}
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[ERROR] curPage값이 null이거나 비어있습니다");
		}
		
		int totalCount = boardDao.selectCntAll(conn);
		
		Paging paging = new Paging(totalCount, curPage);
		
//		JDBCTemplate.close(conn);
		return paging;
	}

	@Override
	public List<Map<String, Object>> getAskingList(HttpServletRequest req, Paging paging) {
		Connection conn = JDBCTemplate.getConnection();
		
		Board board = new Board();
		
		String param = req.getParameter("title");
		if(param != null && !"".equals(param)) {
			board.setTitle(param);
		}
		
//		JDBCTemplate.close(conn);
		return boardDao.selectAskingListAll(board, conn, paging);
	}

	@Override
	public List<Map<String, Object>> getBuyList(HttpServletRequest req, Paging paging) {
		Connection conn = JDBCTemplate.getConnection();
		
		Board board = new Board();
		
		String param = req.getParameter("title");
		if(param != null && !"".equals(param)) {
			board.setTitle(param);
		}
		
//		JDBCTemplate.close(conn);
		return boardDao.selectBuyListAll(board, conn, paging);
	}

	@Override
	public List<Map<String, Object>> getMineList(HttpServletRequest req, Paging paging) {
		Connection conn = JDBCTemplate.getConnection();
		
		Board board = new Board();
		
		String param = req.getParameter("title");
		if(param != null && !"".equals(param)) {
			board.setTitle(param);
		}
		
//		JDBCTemplate.close(conn);
		return boardDao.selectMineListAll(board, conn, paging);
	}

	@Override
	public List<Map<String, Object>> getShareList(HttpServletRequest req, Paging paging) {
		Connection conn = JDBCTemplate.getConnection();
		
		Board board = new Board();
		
		String param = req.getParameter("title");
		if(param != null && !"".equals(param)) {
			board.setTitle(param);
		}
		
//		JDBCTemplate.close(conn);
		return boardDao.selectShareListAll(board, conn, paging);
	}

	@Override
	public List<Map<String, Object>> getDailyList(HttpServletRequest req, Paging paging) {
		Connection conn = JDBCTemplate.getConnection();
		
		Board board = new Board();
		
		String param = req.getParameter("title");
		if(param != null && !"".equals(param)) {
			board.setTitle(param);
		}
		
//		JDBCTemplate.close(conn);
		return boardDao.selectDailyListAll(board, conn, paging);
	}
	
	@Override
	public void write(HttpServletRequest req) {

		//게시글 정보 DTO 객체
		Post post = null;	
		//첨부파일 정보 DTO 객체
		File file = null;
		//게시글, 첨부파일 연결 객체
		PostFile postFile = null;
		
		//DB연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		
		//파일업로드 형태의 데이터가 맞는지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if( !isMultipart ) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
			return; //write() 메소드 중단
		}	
		
		
		//게시글 정보를 저장할 DTO객체 생성
		post = new Post();
		postFile = new PostFile();
		
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
				if( "title".equals(key) ) {
					post.setTitle( value );
				} else if( "content".equals(key) ) {
					post.setContent( value );
				} else if( "select".equals(key) ) {
					//select로 넘어온 title(value)을 boardno으로 바꿔야함
					int boardno = boardDao.changeBoardno( conn, value );
					post.setBoard_no( boardno );
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
		
		
		
		
		
		int post_no = boardDao.selectNextPost_no(conn);
		int file_no = boardDao.selectNextFile_no(conn);
		
		//게시글 정보가 있을 경우
		if(post != null) {

			post.setPost_no(post_no);
			postFile.setPost_no( post_no );
			post.setUser_no( (int)req.getSession().getAttribute("userno") );
			
			if(post.getTitle()==null || "".equals(post.getTitle())) {
				post.setTitle("(제목없음)");
			}
			
			if( boardDao.insert(conn, post) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		//첨부파일 정보가 있을 경우
		if(file != null) {
			
			file.setFile_no(file_no);
			postFile.setFile_no(file_no);
			file.setUser_no( (int)req.getSession().getAttribute("userno") );

			if( boardDao.insertFile(conn, file) > 0 && boardDao.insertFileInfo(conn, postFile) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
				
		
		
	}

	@Override
	public Post getPostno(HttpServletRequest req) {
		
		Post post_no = new Post();
		
		String param = req.getParameter("postno");
		if(param!=null && !"".equals(param)) {
			post_no.setPost_no( Integer.parseInt(param) );
		}
		return post_no;
		
	}
	
	@Override
	public String SearchNick(Post post_no) {
		return boardDao.selectNickByUserno(JDBCTemplate.getConnection(), post_no); 
	}

	@Override
	public Post detail(Post post_no) {
		
		Connection conn = JDBCTemplate.getConnection();

		if( boardDao.updateHit(conn, post_no) == 1 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//게시글 조회
		Post post = boardDao.selectPostByPostno(conn, post_no); 
		
		return post;
		
	}

	@Override
	public String getNick(Post detailBoard) {
		return boardDao.selectNickByUserno(JDBCTemplate.getConnection(), detailBoard);
	}

	@Override
	public File detailFile(Post post_no) {
		
		int fileno = boardDao.changeFileno( JDBCTemplate.getConnection() , post_no );
		
		return boardDao.selectFile(JDBCTemplate.getConnection(), fileno);
	}
	
	@Override
	public void update(HttpServletRequest req) {

		//게시글 정보 DTO 객체
		Post post = null;	
		//첨부파일 정보 DTO 객체
		File file = null;
		//게시글, 첨부파일 연결 객체
		PostFile postFile = null;
		
		
		//DB연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		
		//파일업로드 형태의 데이터가 맞는지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if( !isMultipart ) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
			return; //write() 메소드 중단
		}	
		
		
		//게시글 정보를 저장할 DTO객체 생성
		post = new Post();		
		postFile = new PostFile();
		
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
				if( "title".equals(key) ) {
					post.setTitle( value );
				} else if( "content".equals(key) ) {
					post.setContent( value );
				} else if( "select".equals(key) ) {
					//select로 넘어온 title(value)을 boardno으로 바꿔야함
					int boardno = boardDao.changeBoardno( conn, value );
					post.setBoard_no( boardno );
				} else if( "postno".equals(key)) {
					//왜 전달이 안되는지 모르겠음
					post.setPost_no( Integer.parseInt(value) );
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
		
		int file_no = boardDao.selectNextFile_no(conn);
		
		//게시글 정보가 있을 경우
		if(post != null) {
			
			post.setUser_no( (int)req.getSession().getAttribute("userno") );
			System.out.println(post);
			
			if( boardDao.update(conn, post) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		//첨부파일 정보가 있을 경우
		if(file != null) {
			
			postFile.setFile_no(file_no);
			post.setUser_no( (int)req.getSession().getAttribute("userno") );

			if( boardDao.insertFile(conn, file) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
				
		
		
		
	}
	
	@Override
	public void delete(Post post_no) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int postno = post_no.getPost_no();
		System.out.println( postno );
		
		PostFile postFile = new PostFile();
		postFile = boardDao.selectFileByPostno(conn, post_no);
		
		System.out.println(postFile);
		
		if( postFile != null ) {
			
			int file_no = postFile.getFile_no();
			
			if( boardDao.deleteFile(conn, file_no) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		if( boardDao.delete(conn, post_no) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	
	@Override
	public File thumFileShow(List<Map<String, Object>> list) {

		Connection conn = JDBCTemplate.getConnection();
		
		File file = new File();
		Post post_no = new Post();
		PostFile file_no = new PostFile();

		for ( int i=0; i < list.size(); i++ ){
			post_no = (Post) list.get(i).get("post");
		}
		
		System.out.println("post_no : " + post_no.getPost_no());
		
		file_no = boardDao.selectFileByPostno(conn, post_no);
		
		System.out.println("file_no : " + file_no);
		
//		file = boardDao.selectThum(conn, file_no);
		
		return file;
		
	}
		

}
