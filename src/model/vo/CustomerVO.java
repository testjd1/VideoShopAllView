package model.vo;

public class CustomerVO {
	private String pNo;				// 전화번호 -> 기본키
	private String name;			// 이름
	private String s_Pno;			// 보조전화
	private String addr;			// 주소
	private String email;			// 이메일
	
	
	public CustomerVO() {
		
	}
	
	public CustomerVO(String pNo, String name, String s_Pno, String addr, String email) {
		super();
		this.pNo = pNo;
		this.name = name;
		this.s_Pno = s_Pno;
		this.addr = addr;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "InfoVo [pNo=" + pNo + ", name=" + name + ", s_Pno=" + s_Pno + ", addr=" + addr + ", email=" + email
				+ "]\n";
	}

	public String getpNo() {
		return pNo;
	}
	public void setpNo(String pNo) {
		this.pNo = pNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getS_Pno() {
		return s_Pno;
	}
	public void setS_Pno(String s_Pno) {
		this.s_Pno = s_Pno;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	


}
