package xyz.sunnytoday.dao.face;




import java.sql.Connection;
import java.util.List;
import java.util.Map;

import xyz.sunnytoday.common.util.Paging;
import xyz.sunnytoday.dto.Board;
import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.dto.PostFile;

public interface AdminPostDao {
	
	public List<Map<String, Object>> selectAll(Connection conn, Paging paging);
	
	public int selectCntAll(Connection conn);

	public Post selectPostByPostno(Connection conn, Post post_no);
//	public List<Map<String, Object>> selectPostByPostno(Connection conn, Post post_no);

	public int insert(Connection conn, Post post);

	public int delete(Connection conn, Post post);

	int selectNextPost_no(Connection conn);

	int selectNextFile_no(Connection conn);

	int changeBoardno(Connection conn, String value);

	int insertFile(Connection conn, File file);

	int insertFileInfo(Connection conn, PostFile postFile);

	public String selectNickByUserno(Connection conn, Post viewPost);

	File selectFile(Connection conn, int fileno);

	int changeFileno(Connection conn, Post post_no);

	public List<Map<String, Object>> searchTitle(Connection conn, String search, Paging paging);

	public List<Map<String, Object>> searchNick(Connection conn, String search, Paging paging);
	

}
