package xyz.sunnytoday.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dao.face.MessageDao;
import xyz.sunnytoday.dao.impl.MessageDaoImpl;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Message;
import xyz.sunnytoday.service.face.MemberService;
import xyz.sunnytoday.service.face.MessageService;

public class MessageServiceImpl implements MessageService {

	//MessageService 객체 생성
	private MessageDao messageDao = new MessageDaoImpl();
	private MemberService memberService = new MemberServiceImpl();

	@Override
	public List<Message> getMessageList(Paging paging, int userNo) {
		
		//쪽지 목록 전체 조회 결과 처리
		Connection connection = JDBCTemplate.getConnection();
		List<Message> list =  messageDao.selectAllToMe(connection, paging, userNo);
		JDBCTemplate.close(connection);
		return list;
		
	}
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		//전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		int curPage = 1;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다. 1페이지 출력");
		}
		
		//Message 테이블의 쪽지 개수를 조회
		Connection connection = JDBCTemplate.getConnection();
		int totalCount = messageDao.selectCntAllToMe(connection, (Integer) req.getSession().getAttribute("userno"));
		JDBCTemplate.close(connection);
		
		//Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}

	@Override
	public Message getMessage_No(HttpServletRequest req) {
		
		//message_no를 저장할 객체 생성
		Message message_no = new Message();
		
		//message_no 전달파라미터 검증 - null, ""
		String param = req.getParameter("message_no");
		if(param!=null && !"".equals(param)) {
			
			//message_no 전달파라미터 추출
			message_no.setMessage_no( Integer.parseInt(param));
		}
		
		return message_no;
	}

	@Override
	public void deleteMessage(Message message) {
		Connection conn = JDBCTemplate.getConnection();
		
		if( messageDao.delete(conn, message) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
	}

	@Override
	public void postMessage(HttpServletRequest req) {
		
		Message message = new Message();
		System.out.println("too : " + req.getParameter("too"));
		Member too = memberService.getMemberByUserIdOrNull(req.getParameter("too"));
		if(too == null) {
			//없는 회원 처리 할 내용 여기에 넣으세ㅐ요 생각해보니까 from은 보낸사람 이잖아요 저거 jsp에서 이름 바꾸세요ㅕ
			
			return;
		}

		message.setTitle( req.getParameter("msgTitle") );
		message.setContent( req.getParameter("content") );
		message.setToo(too.getUserno());
		message.setFromm((Integer)req.getSession().getAttribute("userno"));
		
		Connection connection = JDBCTemplate.getConnection();
		System.out.println(message);
		messageDao.insert(connection, message);
		
		JDBCTemplate.close(connection);
	}

	@Override
	public Message view(Message message_no) {

		Connection conn = JDBCTemplate.getConnection();
		
		//쪽지 조회
		Message message = messageDao.selectMessageByMessageno(conn, message_no);
		
		return message;
	}

	@Override
	public Object getFromm(Message viewMessage) {
		// TODO Auto-generated method stub
		return null;
	}





	

}
