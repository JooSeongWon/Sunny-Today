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
		
		//받은 쪽지 목록 전체 조회 결과 처리
		Connection connection = JDBCTemplate.getConnection();
		List<Message> list =  messageDao.selectAllToMe(connection, paging, userNo);
		JDBCTemplate.close(connection);
		
		list.forEach(message -> {
			message.setFromNick(memberService.getMemberByUserNoOrNull(message.getFromm()).getNick());
		});
		
		return list;		
	}
	
	@Override
	public List<Message> getSendMessageList(Paging paging, int userNo) {

		//보낸 쪽지 목록 전체 조회 결과 처리
		Connection connection = JDBCTemplate.getConnection();
		List<Message> slist =  messageDao.selectAllToOther(connection, paging, userNo);
		JDBCTemplate.close(connection);
		
		slist.forEach(message -> {
			message.setTooNick(memberService.getMemberByUserNoOrNull(message.getToo()).getNick());
		});
		
		return slist;		
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
	public Paging getSendPaging(HttpServletRequest req) {
		
		//전달파라미터 curPage 파싱
		String sparam = req.getParameter("curPage");
		int scurPage = 1;
		if(sparam != null && !"".equals(sparam)) {
			scurPage = Integer.parseInt(sparam);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다. 1페이지 출력");
		}
		
		//Message 테이블의 쪽지 개수를 조회
		Connection connection = JDBCTemplate.getConnection();
		int stotalCount = messageDao.selectCntAllToOther(connection, (Integer) req.getSession().getAttribute("userno"));
		JDBCTemplate.close(connection);
		
		//Paging 객체 생성
		Paging sendPaging = new Paging(stotalCount, scurPage);
		
		return sendPaging;
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
	public void postMessage(HttpServletRequest req) {
		
		Message message = new Message();
		System.out.println("too : " + req.getParameter("too")); //넵
		Member too = memberService.getMemberByUserIdOrNull(req.getParameter("too")); //큰일이네 닉으로 찾는게 없네요 하 흠 음 후우 그래서 소셜회원...왕따시키면 안 되겟죠...?아이디로 찾기 만들어주셨던 거 같아요 ㅠㅠ
		if(too == null) {
			System.out.println("[ERROR] 없는 회원입니다.");
			
			return;
		}

		message.setTitle( req.getParameter("msgTitle") );
		message.setContent( req.getParameter("content") );
		message.setToo(too.getUserno());
		message.setFromm((Integer)req.getSession().getAttribute("userno"));
		
		Connection connection = JDBCTemplate.getConnection();
		System.out.println(message);
		messageDao.insert(connection, message);
		//받은편지함 컨트롤러 열어주세요
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
	public Object getFromNick(Message viewMessage) {
		
		return messageDao.selectNickByUserno(JDBCTemplate.getConnection(), viewMessage);
	}
	
	@Override
	public void delete(Message message) {
		Connection conn = JDBCTemplate.getConnection();
		
		if( messageDao.delete(conn, message) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}      
		
		JDBCTemplate.close(conn);
	}

	@Override
	public int cntList(HttpServletRequest req) {
		int totalCount = 0;
		Connection conn = JDBCTemplate.getConnection();
		totalCount = messageDao.searchCnt(conn);
		JDBCTemplate.close(conn);
		
		return totalCount;
	}

	@Override
	public void deleteMessage(Message message) {
		System.out.println("deleteMessageService called");
		Connection conn = JDBCTemplate.getConnection();
		int res = 0;
		res = messageDao.deleteMessage(conn, message);
		
		if(res != 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);		
	}




	

}
