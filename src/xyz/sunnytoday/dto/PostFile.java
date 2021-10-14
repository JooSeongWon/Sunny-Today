package xyz.sunnytoday.dto;

public class PostFile {

	private int post_no;
	private int file_no;
	@Override
	public String toString() {
		return "Post_File [post_no=" + post_no + ", file_no=" + file_no + "]";
	}
	public int getPost_no() {
		return post_no;
	}
	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}
	public int getFile_no() {
		return file_no;
	}
	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}

	
	
	
	
}
