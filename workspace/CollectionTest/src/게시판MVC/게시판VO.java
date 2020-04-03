package 게시판MVC;

import java.util.Date;

/**
 * DB테이블에 있는 컬럼을 기준으로 데이터를 객체화한 클래스
 * @author PC-16
 *<p>
 *	DB테이블의 '컬럼'이 이 클래스의 '멤버변수'가 된다.<br>
 *	DB테이블의 컬럼과 클래스의 멤버변수를 매핑하는 역할을 수행한다.<br>
 *</p>
 */
public class 게시판VO {
	private int board_no;
	private String board_title;
	private String board_writer;
	private Date board_date;
	private String board_content;
	
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int i) {
		this.board_no = i;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public Date getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	
/*	
 * ibatis 적용하며 삭제
 * @Override
	public String toString() {
		return "게시판VO [board_no=" + board_no + ", board_title=" + board_title + ", board_writer=" + board_writer
				+ ", board_date=" + board_date + ", board_content=" + board_content + "]";
	}*/
	
	
	
}
