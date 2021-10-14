package xyz.sunnytoday.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.util.Paging;
import xyz.sunnytoday.dao.face.AdminBoardDao;
import xyz.sunnytoday.dao.face.AdminPostDao;
import xyz.sunnytoday.dao.impl.AdminBoardDaoImpl;
import xyz.sunnytoday.dao.impl.AdminPostDaoImpl;
import xyz.sunnytoday.dto.AdminBoard;
import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.service.face.AdminPostService;

public class AdminPostServiceImpl implements AdminPostService {
	
	private AdminPostDao postDao = new AdminPostDaoImpl();

	@Override
	public List<Post> getList(Paging paging) {
		Connection conn = JDBCTemplate.getConnection();

		//게시물 전체 조회 결과 처리 - 페이징 추가
		List<Post> getList = postDao.selectAll(conn, paging);
		
		JDBCTemplate.close(conn);
		return getList;
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

}
