package xyz.sunnytoday.service.impl;



import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.util.Paging;
import xyz.sunnytoday.dao.face.AdminPostDao;
import xyz.sunnytoday.dao.face.BoardDao;
import xyz.sunnytoday.dao.impl.AdminPostDaoImpl;
import xyz.sunnytoday.dao.impl.BoardDaoImpl;
import xyz.sunnytoday.dto.Board;
import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.service.face.AdminPostService;

public class AdminPostServiceImpl implements AdminPostService {
	
	private AdminPostDao postDao = new AdminPostDaoImpl();
	
	
	@Override
	public List<Map<String, Object>> getList(HttpServletRequest req, Paging paging) {
		Connection conn = JDBCTemplate.getConnection();
	    List<Map<String, Object>> allList = postDao.selectAll(conn, paging);
		JDBCTemplate.close(conn);
		return allList; 
	}
	
	@Override
	public Paging getPaging(HttpServletRequest req) {

		Connection conn = JDBCTemplate.getConnection();

		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}
		
		//Post 테이블의 총 게시글 수를 조회한다
		int totalCount = postDao.selectCntAll(conn);
		
		//Paging객체 생성
		Paging paging = new Paging(totalCount, curPage);
		
		JDBCTemplate.close(conn);
		return paging;
	}

	@Override
	public Post getPostno(HttpServletRequest req) {
		
		//postno를 저장할 객체 생성
		Post postno = new Post();
		
		//postno 전달파라미터 검증 - null, ""
		String param = req.getParameter("post_no");
		if(param!=null && !"".equals(param)) {
			
			//postno 전달파라미터 추출
			postno.setPost_no( Integer.parseInt(param) );
		}
		
		//결과 객체 반환
		return postno;
	}

	@Override
	public Post view(Post postno) {

		Connection conn = JDBCTemplate.getConnection();

		//게시글 조회
		Post post = postDao.selectPostByPostno(conn, postno); 
		JDBCTemplate.close(conn);
		return post;
	}
	
	@Override
	public String getNick(Post viewPost) {
		Connection conn = JDBCTemplate.getConnection();
		
		String getNick = postDao.selectNickByid(conn, viewPost);
	
		JDBCTemplate.close(conn);
		return getNick;
	}	
	
	@Override
	public void deletePost(Post post) {
		Connection conn = JDBCTemplate.getConnection();
		
		if( postDao.delete(conn, post) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
	}
	
	@Override
	public void write(HttpServletRequest req) {
		// insert[dao]에서 받은데이터 writeCtr로 보내기 -첨부파일
	}


}
