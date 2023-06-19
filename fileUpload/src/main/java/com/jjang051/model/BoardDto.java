package com.jjang051.model;

public class BoardDto {
	private int id;
	private String userId;
	private String name;
	private String title;
	private String contents;
	private String regDate;
	private int hit;
	public BoardDto() {
		super();
	}
	public BoardDto(int id, String userId, String name, String title, String contents, String regDate, int hit) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.title = title;
		this.contents = contents;
		this.regDate = regDate;
		this.hit = hit;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	@Override
	public String toString() {
		return "BoardDto [id=" + id + ", userId=" + userId + ", name=" + name + ", title=" + title + ", contents="
				+ contents + ", regDate=" + regDate + ", hit=" + hit + "]";
	}
}
