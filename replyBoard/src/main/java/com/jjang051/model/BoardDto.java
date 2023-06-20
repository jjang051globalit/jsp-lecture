package com.jjang051.model;

public class BoardDto {
	private int id;
	private String userId;
	private String name;
	private String title;
	private String contents;
	private String regDate;
	private int hit;
	private int regroup;
	private int relevel;
	private int restep;

	public BoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardDto(int id, String userId, String name, String title, String contents, String regDate, int hit,
			int regroup, int relevel, int restep) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.title = title;
		this.contents = contents;
		this.regDate = regDate;
		this.hit = hit;
		this.regroup = regroup;
		this.relevel = relevel;
		this.restep = restep;
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

	public int getRegroup() {
		return regroup;
	}

	public void setRegroup(int regroup) {
		this.regroup = regroup;
	}

	public int getRelevel() {
		return relevel;
	}

	public void setRelevel(int relevel) {
		this.relevel = relevel;
	}

	public int getRestep() {
		return restep;
	}

	public void setRestep(int restep) {
		this.restep = restep;
	}

	@Override
	public String toString() {
		return "BoardDto [id=" + id + ", userId=" + userId + ", name=" + name + ", title=" + title + ", contents="
				+ contents + ", regDate=" + regDate + ", hit=" + hit + ", regroup=" + regroup + ", relevel=" + relevel
				+ ", restep=" + restep + "]";
	}

}
