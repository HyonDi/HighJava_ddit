package kr.or.ddit.basic;

public class member_pr_VO {
	
	private String id;
	private String name;
	private String tel;
	private String addr;
	
	
	public member_pr_VO(String id, String name, String tel, String addr) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}
	
	public member_pr_VO() {
		
	}
	


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
	
	
	}
