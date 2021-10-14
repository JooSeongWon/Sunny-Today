package xyz.sunnytoday.dao.face;

import java.sql.Connection;
import java.util.List;

import xyz.sunnytoday.common.util.Paging;
import xyz.sunnytoday.dto.Post;

public interface AdminPostDao {
	
	public List<Post> selectAll(Connection conn, Paging paging);
	
	public int selectCntAll(Connection conn);

}
