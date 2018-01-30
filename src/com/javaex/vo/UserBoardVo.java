package com.javaex.vo;

public class UserBoardVo {
	private int userno;
	private String name;
	private String email;
	private String password;
	private String gender;
	private int boardno;
	private String title;
	private String content;
	private int hit;
	private String date;
	private int user_no;
	
	public UserBoardVo() {
		super();
	}
	
	public UserBoardVo(int userno, String name, String email, String password, String gender, int boardno, String title,
			String content, int hit, String date, int user_no) {
		super();
		this.userno = userno;
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.boardno = boardno;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.date = date;
		this.user_no = user_no;
	}
	
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
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
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	@Override
	public String toString() {
		return "UserBoardVo [userno=" + userno + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", gender=" + gender + ", boardno=" + boardno + ", title=" + title + ", content=" + content + ", hit="
				+ hit + ", date=" + date + ", user_no=" + user_no + "]";
	}
	
	
	
}
