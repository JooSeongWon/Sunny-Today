package xyz.sunnytoday.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.AdminMessageDao;
import xyz.sunnytoday.dao.impl.AdminMessageDaoImpl;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.service.face.AdminMessageService;
import xyz.sunnytoday.util.Paging;

public class AdminMessageServiceImpl implements AdminMessageService {
	
	private AdminMessageDao messageDao = new AdminMessageDaoImpl();
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		String param = req.getParameter("curPage");
		String select = req.getParameter("select");
		String search =  req.getParameter("search");
		
		int curPage = 0;
		
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있음");
		}
		Connection conn = JDBCTemplate.getConnection();
		
		int totalCount = 0;
		
		if( select != null && select.equals("id") ) {
			if(search != null && !"".equals(search)) {
				totalCount = messageDao.selectCntById(conn,search); 
			} else {
				totalCount = messageDao.selectCntAll(conn); 
			}			
		} else if( select != null && select.equals("nick") ) {
			if(search != null && !"".equals(search)) {
				totalCount = messageDao.selectCntByNick(conn,search); 
			} else {
				totalCount = messageDao.selectCntAll(conn); 
			}			
		} else if( select != null && select.equals("email") ) {
			if(search != null && !"".equals(search)) {
				totalCount = messageDao.selectCntByEmail(conn,search); 
			} else {
				totalCount = messageDao.selectCntAll(conn); 
			}			
		} else {
			totalCount = messageDao.selectCntAll(conn); 
		}

		Paging paging = new Paging(totalCount, curPage);
		
		JDBCTemplate.close(conn);
		
		return paging;
	}
	
	@Override
	public List<Member> getlist(HttpServletRequest req, Paging paging) {
		Connection conn = JDBCTemplate.getConnection();
		List<Member> list = null;
		String select = req.getParameter("select");
		String search =  req.getParameter("search");
		
		if( select != null && select.equals("id") ) {
			if(search != null && !"".equals(search)) {
				list = messageDao.searchId(conn, search, paging); 
			} else {
				list = messageDao.searchAll(conn, paging); 
			}			
		} else if( select != null && select.equals("nick") ) {
			if(search != null && !"".equals(search)) {
				list = messageDao.searchNick(conn, search, paging); 
			} else {
				list = messageDao.searchAll(conn, paging); 
			}			
		} else if( select != null && select.equals("email") ) {
			if(search != null && !"".equals(search)) {
				list = messageDao.searchEmail(conn, search, paging); 
			} else {
				list = messageDao.searchAll(conn, paging); 
			}			
		} else {
			list = messageDao.searchAll(conn, paging); 
		}
		
		JDBCTemplate.close(conn);
		
		return list;
	}
}
