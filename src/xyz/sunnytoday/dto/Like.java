package xyz.sunnytoday.dto;

public class Like {
	
	private int post_no;
	private int user_no;
	private int score;
	@Override
	public String toString() {
		return "Like [post_no=" + post_no + ", user_no=" + user_no + ", score=" + score + "]";
	}
	public int getPost_no() {
		return post_no;
	}
	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	

}
