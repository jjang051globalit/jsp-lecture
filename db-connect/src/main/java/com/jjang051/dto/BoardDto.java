package com.jjang051.dto;

public class BoardDto {
	private int id;
	private String name;
	private String title;
	private String contents;
	private String date;
	private int hit;
	
	public BoardDto() {}

	public BoardDto(int id, String name, String title, String contents, String date, int hit) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.contents = contents;
		this.date = date;
		this.hit = hit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	@Override
	public String toString() {
		return "BoardDto [id=" + id + ", name=" + name + ", title=" + title + ", contents=" + contents + ", date="
				+ date + ", hit=" + hit + "]";
	}

}
