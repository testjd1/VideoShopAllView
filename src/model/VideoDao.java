package model;


import java.util.ArrayList;

import model.vo.VideoVO;

public interface VideoDao {
	public void insertVideo(VideoVO vo, int count) throws Exception;	// 입력
	public ArrayList selectVideo(String text,  String jemok) throws Exception;			//  검색
//	public int updateVideo(VideoVO vo) throws Exception;				// 고객정보 수정
//	public void delete(VideoVO vo) throws Exception;
}
