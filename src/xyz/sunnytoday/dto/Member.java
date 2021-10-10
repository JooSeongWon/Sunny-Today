package xyz.sunnytoday.dto;

import java.util.Date;

public class Member {
<<<<<<< HEAD
<<<<<<< HEAD
	private int userno;
	private String userid;
	private String userpw;
	private String salt;
	private String idType;
=======
=======
>>>>>>> mypageprofile
	
	private int user_no;
	private String id;
	private String password;
	private String salt;
<<<<<<< HEAD
>>>>>>> mypageprofile
=======
>>>>>>> mypageprofile
	private Date create_date;
	private String email;
	private String nick;
	private Date birth;
	private String gender;
	private String phone;
	private String admin;
<<<<<<< HEAD
<<<<<<< HEAD
	private int pictureno;
	
	
	@Override
	public String toString() {
		return "Member [userno=" + userno + ", userid=" + userid + ", userpw=" + userpw + ", salt=" + salt + ", idType="
				+ idType + ", CreateDate=" + create_date + ", email=" + email + ", nick=" + nick + ", birth=" + birth
				+ ", gender=" + gender + ", phone=" + phone + ", admin=" + admin + ", pictureno=" + pictureno + "]";
	}
	
	public int getUserno() {
		return userno;
	}
	
	public void setUserno(int userno) {
		this.userno = userno;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getUserpw() {
		return userpw;
	}
	
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getIdType() {
		return idType;
	}
	
	public void setIdType(String idType) {
		this.idType = idType;
	}
	
	public Date getCreate_date() {
		return create_date;
	}
	
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNick() {
		return nick;
	}
	
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public Date getBirth() {
		return birth;
	}
	
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getAdmin() {
		return admin;
	}
	
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
	public int getPictureno() {
		return pictureno;
	}
	
	public void setPictureno(int pictureno) {
		this.pictureno = pictureno;
	}
=======
=======
>>>>>>> mypageprofile
	private int picture_no;
	private String birth_open;
	private String phone_open;
	
	@Override
	public String toString() {
		return "Member [user_no=" + user_no + ", id=" + id + ", password=" + password + ", salt=" + salt
				+ ", create_date=" + create_date + ", email=" + email + ", nick=" + nick + ", birth=" + birth
				+ ", gender=" + gender + ", phone=" + phone + ", admin=" + admin + ", picture_no=" + picture_no
				+ ", birth_open=" + birth_open + ", phone_open=" + phone_open + "]";
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public int getPicture_no() {
		return picture_no;
	}

	public void setPicture_no(int picture_no) {
		this.picture_no = picture_no;
	}

	public String getBirth_open() {
		return birth_open;
	}

	public void setBirth_open(String birth_open) {
		this.birth_open = birth_open;
	}

	public String getPhone_open() {
		return phone_open;
	}

	public void setPhone_open(String phone_open) {
		this.phone_open = phone_open;
	}
	
	
<<<<<<< HEAD
>>>>>>> mypageprofile
=======
>>>>>>> mypageprofile
	
}
