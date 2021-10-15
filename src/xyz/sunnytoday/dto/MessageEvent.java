package xyz.sunnytoday.dto;

public class MessageEvent {
	
	private int message_e_no;	
	private int event_no;
	private String title;
	private String content;
	private String name;
	
	@Override
	public String toString() {
		return "MessageEvent [message_e_no=" + message_e_no + ", event_no=" + event_no + ", title=" + title
				+ ", content=" + content + ", name=" + name + "]";
	}
	
	public int getMessage_e_no() {
		return message_e_no;
	}
	public void setMessage_e_no(int message_e_no) {
		this.message_e_no = message_e_no;
	}
	public int getEvent_no() {
		return event_no;
	}
	public void setEvent_no(int event_no) {
		this.event_no = event_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
