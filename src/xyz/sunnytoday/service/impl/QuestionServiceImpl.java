package xyz.sunnytoday.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dao.face.QuestionDao;
import xyz.sunnytoday.dao.impl.QuestionDaoImpl;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Question;
import xyz.sunnytoday.service.face.QuestionService;

public class QuestionServiceImpl implements QuestionService {
	
	QuestionDao questionDao = new QuestionDaoImpl();

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

		int totalCount = questionDao.selectCntAll(conn);

		
		Paging paging = new Paging(totalCount, curPage);
		
		JDBCTemplate.close(conn);
		return paging;
	}

	@Override
	public List<Question> getList(Paging paging) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		
		List<Question> result = questionDao.selectListAll(conn, paging);
		JDBCTemplate.close(conn);
		
		return result;
		
	}
	
	@Override
	public void insert(HttpServletRequest req) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Question question = new Question();

		int userno = (int)req.getSession().getAttribute("userno");
		
		String userid = questionDao.selectIdByUserno(conn, userno);

		question.setTitle( req.getParameter("title") );
		question.setContent( req.getParameter("content") );
		question.setUser_no(userno);
		question.setId(userid);

		

		if(question.getTitle()==null || "".equals(question.getTitle())) {
			question.setTitle("(제목없음)");
		}

		if( questionDao.insert(conn, question) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}

}
