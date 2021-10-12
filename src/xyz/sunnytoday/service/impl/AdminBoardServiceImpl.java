package xyz.sunnytoday.service.impl;

import java.sql.Connection;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.util.Paging;
import xyz.sunnytoday.dao.face.AdminBoardDao;
import xyz.sunnytoday.dao.impl.AdminBoardDaoImpl;
import xyz.sunnytoday.dto.AdminBoard;
import xyz.sunnytoday.service.face.AdminBoardService;

public class AdminBoardServiceImpl implements AdminBoardService {
	
	private AdminBoardDao boardDao = new AdminBoardDaoImpl();

	@Override
	public List<AdminBoard> getList() {
		Connection conn = JDBCTemplate.getConnection();
		
		
		//Board 테이블의 총 게시글 수를 조회한다
		return boardDao.selectAll(conn);
		
	}

	@Override
	public List<AdminBoard> getList(Paging paging) {
		Connection conn = JDBCTemplate.getConnection();

		//게시글 전체 조회 결과 처리 - 페이징 추가
		return boardDao.selectAll(conn, paging);
		
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
		
		//Board 테이블의 총 게시글 수를 조회한다
		int totalCount = boardDao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging객체 생성
		Paging paging = new Paging(totalCount, curPage);
		
		JDBCTemplate.close(conn);
		return paging;
	}
	
	@Override
	public int getCount(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();

		
		return boardDao.boardCntAll(conn);
	}

	@Override
	public int getTitleCount(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();

		return boardDao.titleCount(conn);

	}
	
	@Override
	public AdminBoard getBoardno(HttpServletRequest req) {
		//boardno를 저장할 객체 생성
		AdminBoard boardno = new AdminBoard();
		
		//boardno 전달파라미터 검증 - null, ""
		String param = req.getParameter("board_no");
		if(param!=null && !"".equals(param)) {
			
			//boardno 전달파라미터 추출
			boardno.setBoard_no( Integer.parseInt(param) );
		}
		
		//결과 객체 반환
		return boardno;
	}
	
	@Override
	public AdminBoard view(AdminBoard board_no) {
		
		Connection conn = JDBCTemplate.getConnection();

		AdminBoard board = boardDao.selectBoardByBoardno(conn, board_no); 
		
		JDBCTemplate.close(conn);
		return board;
		}
	
	@Override
	public void write(HttpServletRequest req) {

		AdminBoard bor = new AdminBoard();
			
//		bor.setBoard_no(Integer.parseInt(req.getParameter("board_no")) );
		bor.setComments_grant(req.getParameter("comments_grant"));
//		bor.setLike(req.getParameter("like"));
		bor.setIndex(0);
		bor.setLike("Y");
		bor.setList_grant(req.getParameter("list_grant"));
		bor.setRead_grant(req.getParameter("read_grant"));
//		bor.setShow(req.getParameter("show"));
		bor.setTitle( req.getParameter("title") );
//		bor.setTitle_length(Integer.parseInt(req.getParameter("title_length")));
		bor.setWrite_grant(req.getParameter("write_grant"));
		bor.setIndex(Integer.parseInt(req.getParameter("index")));
		
		System.out.println(bor);
		
		Connection conn = JDBCTemplate.getConnection();
		if( boardDao.insert(conn, bor) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}






	
}
