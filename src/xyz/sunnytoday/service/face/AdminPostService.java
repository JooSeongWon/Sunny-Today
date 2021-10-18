package xyz.sunnytoday.service.face;



import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.util.Paging;
import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.dto.Post;

public interface AdminPostService {

	public List<Map<String, Object>> getList(HttpServletRequest req, Paging paging);
	
	public Paging getPaging(HttpServletRequest req);

	public Post getPostno(HttpServletRequest req);
	
	public Post view(Post post_no);
//	public List<Map<String, Object>> view(Post post_no);

	public String getNick(Post viewPost);
	
	public void write(HttpServletRequest req);
	
	public void deletePost(Post post);

	public List<Map<String, Object>> searchPost(HttpServletRequest req, Paging paging);

	public File viewFile(Post viewPost);
}