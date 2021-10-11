package xyz.sunnytoday.service.impl;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import util.Paging;
import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.BoardDao;
import xyz.sunnytoday.dao.impl.BoardDaoImpl;
import xyz.sunnytoday.dto.Board;
import xyz.sunnytoday.service.face.BoardService;

public class BoardServiceImpl implements BoardService {
	
	private BoardDao boardDao = new BoardDaoImpl();

	@Override
	public List<Board> getList() {
				
		//Board 테이블의 총 게시글 수를 조회한다
		return boardDao.selectAll(JDBCTemplate.getConnection());
	}

	@Override
	public List<Board> getList(Paging paging) {
		
		//게시글 전체 조회 결과 처리 - 페이징 추가
		return boardDao.selectAll(JDBCTemplate.getConnection(), paging);
		
	}
	
	

	@Override
	public Paging getPaging(HttpServletRequest req) {
		//전달파라미터 curPage 파싱
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
		
		return paging;
	}

	@Override
	public Board getBoardno(HttpServletRequest req) {
		
		//boardno를 저장할 객체 생성
		Board boardno = new Board();
		
		
		
		return null;
	}

}
