package kr.co.hk;

public class SBoardVO {
	private int board_no, cnt, total_pagecnt;
	private String board_title, board_content, regdate;
	
	
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getTotal_pagecnt() {
		return total_pagecnt;
	}
	public void setTotal_pagecnt(int total_pagecnt) {
		this.total_pagecnt = total_pagecnt;
	}
	
}
