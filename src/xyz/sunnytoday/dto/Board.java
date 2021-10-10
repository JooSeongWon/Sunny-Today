package xyz.sunnytoday.dto;

public class Board {

	private int board_no;
	private String comments_grant;
	private int index;
	private String like;
	private String list_grant;
	private String read_grant;
	private String show;
	private String title;
	private int title_length;
	private String write_grant;
	@Override
	public String toString() {
		return "Board [board_no=" + board_no + ", comments_grant=" + comments_grant + ", index=" + index + ", like="
				+ like + ", list_grant=" + list_grant + ", read_grant=" + read_grant + ", show=" + show + ", title="
				+ title + ", title_length=" + title_length + ", write_grant=" + write_grant + "]";
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getComments_grant() {
		return comments_grant;
	}
	public void setComments_grant(String comments_grant) {
		this.comments_grant = comments_grant;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public String getList_grant() {
		return list_grant;
	}
	public void setList_grant(String list_grant) {
		this.list_grant = list_grant;
	}
	public String getRead_grant() {
		return read_grant;
	}
	public void setRead_grant(String read_grant) {
		this.read_grant = read_grant;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getTitle_length() {
		return title_length;
	}
	public void setTitle_length(int title_length) {
		this.title_length = title_length;
	}
	public String getWrite_grant() {
		return write_grant;
	}
	public void setWrite_grant(String write_grant) {
		this.write_grant = write_grant;
	}
	


}
