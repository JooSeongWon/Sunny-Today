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
import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.dto.PostFile;
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
	public List<Map<String, Object>> getList(Paging paging) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		
		List<Map<String, Object>> result = questionDao.selectListAll(conn, paging);
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
	
	@Override
	public Question getUserno(HttpServletRequest req) {
		Question userNo = new Question();
		
		String param = req.getParameter("user_no");
		if(param!=null && !"".equals(param)) {
			userNo.setUser_no( Integer.parseInt(param) );
		}
		
		return userNo;
	}
	
	@Override
	public String getNick(Question userno) {
		String nick = questionDao.selectNickByUserno(JDBCTemplate.getConnection(), userno);
		JDBCTemplate.close(JDBCTemplate.getConnection());
		return nick;
	}
	
	@Override
	public Member loginMember(HttpServletRequest req) {
		
		Connection conn = JDBCTemplate.getConnection();
		Member member = new Member();
		
		member.setUserno( (int)req.getSession().getAttribute("userno") );
		member.setNick( (String)req.getSession().getAttribute("nick") );
		JDBCTemplate.close(conn);
		return member;
	}
	
	@Override
	public Question getQuestionno(HttpServletRequest req) {
		Question questionNo = new Question();
		
		String param = req.getParameter("questionno");
		if(param!=null && !"".equals(param)) {
			questionNo.setQuestion_no( Integer.parseInt(param) );
		}
		
		return questionNo;
	}
	
	@Override
	public Question detail(Question questionNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 조회
		Question questionDetail = questionDao.selectQuestionByquestionno(conn, questionNo); 
		
		JDBCTemplate.close(conn);
		return questionDetail;
	}

	@Override
	public void update(HttpServletRequest req) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Question question = new Question();

		int userno = (int)req.getSession().getAttribute("userno");
		
		String userid = questionDao.selectIdByUserno(conn, userno);
		
		question.setQuestion_no( Integer.parseInt( req.getParameter("questionno")));
		question.setTitle( req.getParameter("title") );
		question.setContent( req.getParameter("updateContent") );
		question.setUser_no(userno);
		question.setId(userid);

		if(question.getTitle()==null || "".equals(question.getTitle())) {
			question.setTitle("(제목없음)");
		}

		if( questionDao.update(conn, question) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	
	@Override
	public void delete(Question questionNo) {

		Connection conn = JDBCTemplate.getConnection();
		
		int res = questionDao.deleteQuestionByQuestionno(conn, questionNo);
				
		
		if( res > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
	}
}
