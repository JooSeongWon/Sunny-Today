package xyz.sunnytoday.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.MessageEventDao;
import xyz.sunnytoday.dao.impl.MessageEventDaoImpl;
import xyz.sunnytoday.dto.Event;
import xyz.sunnytoday.dto.MessageEvent;
import xyz.sunnytoday.service.face.MessageEventService;
import xyz.sunnytoday.util.Paging;

public class MessageEventServiceImpl implements MessageEventService {
	
	private MessageEventDao messageDao = new MessageEventDaoImpl();
	
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
		System.out.println(req.getParameter("event"));
		
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
}
