package xyz.sunnytoday.service.face;



import java.util.List;


import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.util.Paging;
import xyz.sunnytoday.dto.Board;

public interface AdminBoardService {

	public List<Board> getList();

	public List<Board> getList(Paging paging);
	
	public Paging getPaging(HttpServletRequest req);
	
	public int getCount(HttpServletRequest req);
	
	public int getTitleCount(HttpServletRequest req);
	
	public int getCntTitle(HttpServletRequest req);
	
	public Board getBoardno(HttpServletRequest req);
	
	public Board view(Board board_no);
	
	public void write(HttpServletRequest req);
	
	public void deleteByAdBoard(Board board);
	
	public void updateByAdBoard(HttpServletRequest req);


}
