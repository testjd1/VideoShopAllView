package model;

import java.util.ArrayList;

import model.vo.RentVo;

public interface RentDao {
	
	// 대여
	public void rentVideo(String tel, int vnum) throws Exception;
	
	public String searchName(String tel) throws Exception;

	// 반납
	public void returnVideo(int vnum) throws Exception;
	
	//미납목록 검색
	public ArrayList overdue() throws Exception;
}
