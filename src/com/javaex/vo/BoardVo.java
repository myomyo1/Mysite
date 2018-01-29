package com.javaex.vo;

public class BoardVo {
	
	private int no;
	private int hit;
	private int userNo;
	private String title;
	private String content;
	private String regDate;
	private String name;

	public BoardVo() {
		
	}
	
	public BoardVo(String title, String content) {
		this.title=title;
		this.content=content;		
	}
	
	public BoardVo(int no, String title, String name,  int hit, String regDate) {
		this.no = no;
		this.title = title;
		this.name = name;
		this.hit = hit;
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
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

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", hit=" + hit + ", userNo=" + userNo + ", title=" + title + ", content=" + content
				+ ", regDate=" + regDate + ", name=" + name + "]";
	}

	}
	
	

