package xyz.sunnytoday.dto;

public class Filee {

	private int file_no;
	private String url;
	private String thumbnail_url;
	private String origin_name;
	private int user_no;
	@Override
	public String toString() {
		return "File [file_no=" + file_no + ", url=" + url + ", thumbnail_url=" + thumbnail_url + ", origin_name="
				+ origin_name + ", user_no=" + user_no + "]";
	}
	public int getFile_no() {
		return file_no;
	}
	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getThumbnail_url() {
		return thumbnail_url;
	}
	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}
	public String getOrigin_name() {
		return origin_name;
	}
	public void setOrigin_name(String origin_name) {
		this.origin_name = origin_name;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	
	
}
