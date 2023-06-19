package com.jjang051.model;

public class PageDto {
	private int pageTotal;
	private double total;
	private int pageBlock;
	private int pageStart;
	private int pageEnd;
	private double pagePerList;
	
	public PageDto() {
		super();
	}
	public PageDto(int pageTotal, double total, int pageBlock, int pageStart, int pageEnd, double pagePerList) {
		super();
		this.pageTotal = pageTotal;
		this.total = total;
		this.pageBlock = pageBlock;
		this.pageStart = pageStart;
		this.pageEnd = pageEnd;
		this.pagePerList = pagePerList;
	}
	public int getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}
	public int getTotal() {
		return (int)total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getPageBlock() {
		return pageBlock;
	}
	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}
	public int getPageStart() {
		return pageStart;
	}
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}
	public int getPageEnd() {
		return pageEnd;
	}
	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}
	public int getPagePerList() {
		return (int)pagePerList;
	}
	public void setPagePerList(double pagePerList) {
		this.pagePerList = pagePerList;
	}
	
	
	
}
