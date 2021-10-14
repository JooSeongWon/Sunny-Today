package xyz.sunnytoday.dto;

import java.util.Date;

public class Message {
	private int MESSAGE_NO;	
	private int TOO;
	private int FROMM;
	private Date POST_DATE;
	private String READ;
	private String TITLE;
	private String CONTENT;
	
	@Override
	public String toString() {
		return "Message [MESSAGE_NO=" + MESSAGE_NO + ", TOO=" + TOO + ", FROMM=" + FROMM + ", POST_DATE=" + POST_DATE
				+ ", READ=" + READ + ", TITLE=" + TITLE + ", CONTENT=" + CONTENT + "]";
	}
	public int getMESSAGE_NO() {
		return MESSAGE_NO;
	}
	public void setMESSAGE_NO(int mESSAGE_NO) {
		MESSAGE_NO = mESSAGE_NO;
	}
	public int getTOO() {
		return TOO;
	}
	public void setTOO(int tOO) {
		TOO = tOO;
	}
	public int getFROMM() {
		return FROMM;
	}
	public void setFROMM(int fROMM) {
		FROMM = fROMM;
	}
	public Date getPOST_DATE() {
		return POST_DATE;
	}
	public void setPOST_DATE(Date pOST_DATE) {
		POST_DATE = pOST_DATE;
	}
	public String getREAD() {
		return READ;
	}
	public void setREAD(String rEAD) {
		READ = rEAD;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	
}
