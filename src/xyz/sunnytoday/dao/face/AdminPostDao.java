package xyz.sunnytoday.dao.face;

import java.io.File;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import xyz.sunnytoday.common.util.Paging;
import xyz.sunnytoday.dto.Post;

public interface AdminPostDao {
	
//	public List<Post> selectAll(Connection conn, Paging paging);
//	public List<Map<String, Object>> selectAll(Connection conn, Board board, Paging paging); 
	
	public int selectCntAll(Connection conn);

	public Post selectPostByPostno(Connection conn, Post postno);

	public String selectNickByid(Connection conn, Post viewPost);
	
	public int insert(Connection conn, Post post);

	public int selectNextBoardno(Connection conn);

	public int insertFile(Connection conn, File postFile);

	public File selectFile(Connection conn, File viewBoard);

	public int update(Connection conn, Post post);
	
	public int delete(Connection conn, Post post);
	
	public int deleteFile(Connection conn, Post post);

	
	public List<Map<String, Object>> selectAll(Connection conn, Paging paging);

}
