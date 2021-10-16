package xyz.sunnytoday.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.AdminMessageEventDao;
import xyz.sunnytoday.dao.impl.AdminMessageEventDaoImpl;
import xyz.sunnytoday.dto.Event;
import xyz.sunnytoday.dto.MessageEvent;
import xyz.sunnytoday.service.face.AdminMessageEventService;
import xyz.sunnytoday.util.Paging;

public class AdminMessageEventServiceImpl implements AdminMessageEventService {
	
	private AdminMessageEventDao messageDao = new AdminMessageEventDaoImpl();
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		String param = req.getParameter("curPage");
		String search =  req.getParameter("search");
		
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있음");
		}
		Connection conn = JDBCTemplate.getConnection();
		
		int totalCount = 0;
		
		if(search != null && !"".equals(search)) {
			totalCount = messageDao.selectCntEvent(conn,search); 
		} else {
			totalCount = messageDao.selectCntAll(conn); 
		}
		
		Paging paging = new Paging(totalCount, curPage);
		
		JDBCTemplate.close(conn);
		
		return paging;
	}
	
	@Override
	public List<MessageEvent> getlist(HttpServletRequest req, Paging paging) {
		Connection conn = JDBCTemplate.getConnection();
		List<MessageEvent> list = null;
		String param = req.getParameter("search");
		
		if(param != null && !"".equals(param)) {
			list = messageDao.search(param, paging, conn);
		} else {
			list = messageDao.All(conn, paging);
		}
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	@Override
	public List<Event> getEvent() {
		Connection conn = JDBCTemplate.getConnection();
		List<Event> event = null;
		
		event = messageDao.selectEvent(conn);
		
		JDBCTemplate.close(conn);
		
		return event;
	}
	
	@Override
	public void write(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();
		
		MessageEvent messageEvent = new MessageEvent();
		
		messageEvent.setEvent_no(Integer.parseInt(req.getParameter("event")));
		messageEvent.setTitle( req.getParameter("title") );
		messageEvent.setContent(req.getParameter("content"));
		if(messageEvent != null ) {
			if( messageEvent.getTitle() == null || "".equals(messageEvent.getTitle())) {
				messageEvent.setTitle("(제목없음 삭제요망)");
			}
			
			if( messageDao.insertMessage(conn, messageEvent) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		JDBCTemplate.close(conn);
		
	}
	
	@Override
	public MessageEvent getEno(HttpServletRequest req) {
		
		MessageEvent messageno = new MessageEvent();
		
		String param = req.getParameter("no");
		
		if(param!=null && !"".equals(param)) {
			messageno.setMessage_e_no( Integer.parseInt(param) );
		}
		
		return messageno;
	}
	
	@Override
	public MessageEvent view(MessageEvent messageno) {
		Connection conn = JDBCTemplate.getConnection();
		
		MessageEvent message = messageDao.selectByMessageno(conn, messageno);
		
		JDBCTemplate.close(conn);
		
		return message;
	}
	
	@Override
	public void update(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();
		
		MessageEvent message = null;
		
		message = new MessageEvent();
		
		message.setEvent_no(Integer.parseInt(req.getParameter("event")));
		message.setTitle( req.getParameter("title") );
		message.setContent(req.getParameter("content"));
		message.setMessage_e_no( Integer.parseInt(req.getParameter("message_e_no")));
		
		if(message != null) {
			if( message.getTitle() == null || "".equals(message.getTitle())) {
				message.setTitle("(제목없음 삭제요망)");
			}
			if( messageDao.update(conn, message) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		JDBCTemplate.close(conn);
		
		}
	
	@Override
	public void delete(MessageEvent message) {
		Connection conn = JDBCTemplate.getConnection();
		
		if(messageDao.delete(conn, message) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
	}
	
	
	@Override
	public void deleteAll(int arr2) {
		Connection conn = JDBCTemplate.getConnection();
		
		if(messageDao.deleteAll(conn, arr2) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
	}

	@Override
	public List<MessageEvent> getEventList(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();
		List<MessageEvent> elist = null;
		String param = req.getParameter("no");
		
		if(param != null && !"".equals(param)) {
			elist = messageDao.selectByEventNo(Integer.parseInt(param),conn);
		}
		
		JDBCTemplate.close(conn);
		return elist;
	}
	
	@Override
	public void titleWrite(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();
		
		String event = req.getParameter("event");
		
		if( messageDao.titleWrite(event, conn) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
	}
	
	@Override
	public List<MessageEvent> getMassageList(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();
		List<MessageEvent> elist = null;
		String param = req.getParameter("no");
		
		if(param != null && !"".equals(param)) {
			elist = messageDao.selectByMessageNo(Integer.parseInt(param),conn);
		}
		
		JDBCTemplate.close(conn);
		return elist;
	}
}

		
	

