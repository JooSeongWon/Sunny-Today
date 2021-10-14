package xyz.sunnytoday.dto;

public class Report_Category {
	private int report_c_no;
	private String title;
	@Override
	public String toString() {
		return "Report_Category [report_c_no=" + report_c_no + ", title=" + title + "]";
	}
	public int getReport_c_no() {
		return report_c_no;
	}
	public void setReport_c_no(int report_c_no) {
		this.report_c_no = report_c_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
