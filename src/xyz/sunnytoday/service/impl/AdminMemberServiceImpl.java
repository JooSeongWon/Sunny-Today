package xyz.sunnytoday.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.AdminMemberDao;
import xyz.sunnytoday.dao.impl.AdminMemberDaoImpl;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.service.face.AdminMemberService;
import xyz.sunnytoday.util.Paging;

public class AdminMemberServiceImpl implements AdminMemberService {
	
	private AdminMemberDao memberDao = new AdminMemberDaoImpl();
	
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
			totalCount = memberDao.selectCntId(conn,search); 
		} else {
			totalCount = memberDao.selectCntAll(conn); 
			System.out.println(totalCount);
		}
		
		Paging paging = new Paging(totalCount, curPage);
		
		JDBCTemplate.close(conn);
		
		return paging;
	}
	
	@Override
	public List<Member> getlist(HttpServletRequest req, Paging paging) {
		Connection conn = JDBCTemplate.getConnection();
		List<Member> list = null;
		String param = req.getParameter("search");
		
		if(param != null && !"".equals(param)) {
			list = memberDao.searchId(param, paging, conn);
		} else {
			list = memberDao.All(conn, paging);
		}
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	@Override
	public void getuserno(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();
		
		int member = 0;
		String userno = req.getParameter("userno");
		String state = req.getParameter("val");
		
		
		
		if(userno != null && !"".equals(userno)) {
			if(state == "set") {
				member = memberDao.setAdmin(Integer.parseInt(userno), conn);
			} else if(state == "del") {
				member = memberDao.setAdmin(Integer.parseInt(userno), conn);
			}
		}
		
		if( member > 0) {
			JDBCTemplate.commit(conn); //커밋
		} else {
			JDBCTemplate.rollback(conn); //롤백
		}

		JDBCTemplate.close(conn);
		
	}
	
}
