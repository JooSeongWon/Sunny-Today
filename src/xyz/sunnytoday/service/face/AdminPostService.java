package xyz.sunnytoday.service.face;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.util.Paging;
import xyz.sunnytoday.dto.Post;

public interface AdminPostService {

	public List<Map<String, Object>> getList(HttpServletRequest req, Paging paging);
	
	public Paging getPaging(HttpServletRequest req);

	public Post getPostno(HttpServletRequest req);
	
	public Post view(Post post_no);
	
	public void write(HttpServletRequest req);
	
	public void deletePost(Post post);

}