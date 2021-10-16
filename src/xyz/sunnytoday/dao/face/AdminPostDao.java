package xyz.sunnytoday.dao.face;

import java.io.File;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import xyz.sunnytoday.common.util.Paging;
import xyz.sunnytoday.dto.Post;

public interface AdminPostDao {
	
	public List<Map<String, Object>> selectAll(Connection conn, Paging paging);
	
	public int selectCntAll(Connection conn);

	public Post selectPostByPostno(Connection conn, Post post_no);

	public int insert(Connection conn, Post post);

	public int delete(Connection conn, Post post);
	

}
