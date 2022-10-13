package model.vo;

public class RentVo {
	private String rentNo;
	private String pNo;
	private String videoNo;
	private String returnYN;
	private String returnDate;
	
	
	public RentVo(){}
	
	public RentVo(String rentNo, String pNo, String videoNo, String returnYN, String returnDate) {
		super();
		this.rentNo = rentNo;
		this.pNo = pNo;
		this.videoNo = videoNo;
		this.returnYN = returnYN;
		this.returnDate = returnDate;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Rent_infoVo [rentNo=" + rentNo + ", pNo=" + pNo + ", videoNo=" + videoNo + ", returnYN=" + returnYN
				+ ", returnDate=" + returnDate + "]\n";
	}

	
	
	public String getRentNo() {
		return rentNo;
	}


	public void setRentNo(String rentNo) {
		this.rentNo = rentNo;
	}
	public String getpNo() {
		return pNo;
	}
	public void setpNo(String pNo) {
		this.pNo = pNo;
	}
	public String getVideoNo() {
		return videoNo;
	}
	public void setVideoNo(String videoNo) {
		this.videoNo = videoNo;
	}
	public String getReturnYN() {
		return returnYN;
	}
	public void setReturnYN(String returnYN) {
		this.returnYN = returnYN;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	
}
