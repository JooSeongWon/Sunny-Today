package xyz.sunnytoday.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.util.Paging;
import xyz.sunnytoday.dto.Post;

public interface AdminPostService {

	public List<Post> getList(Paging paging);

	public Paging getPaging(HttpServletRequest req);

}