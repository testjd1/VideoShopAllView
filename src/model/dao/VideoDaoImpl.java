package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import model.VideoDao;
import model.vo.VideoVO;

public class VideoDaoImpl implements VideoDao{
	final static String DRIVER 	= "oracle.jdbc.driver.OracleDriver";			// final static 붙인 경우 잘보이게 대문자로 변수명 선언
	final static String	URL 	= "jdbc:oracle:thin:@192.168.0.56:1521:xe";		// 오라클 링크 연결
	final static String USER 	= "scott";										// db 아이디
	final static String PASS 	= "tiger";										// 비밀번호
	
	public VideoDaoImpl() throws Exception{
		// 1. 드라이버로딩
		Class.forName(DRIVER);
		System.out.println("드라이버 로딩 성공");	
	}


	public void insertVideo(VideoVO vo, int count) throws Exception{
		// 2. Connection 연결객체 얻어오기
		Connection con = null;								// 전역변수 선언
		PreparedStatement ps = null;						// 전역변수 선언

		try{
			con = DriverManager.getConnection(URL,USER,PASS);		// 드라이브 통해 연결

			// 3. sql 문장 만들기
			String sql = "INSERT INTO Video(Videono,genere,title,director,actor,expl) "
					+ " VALUES(SEQ_Video.NEXTVAL,?,?,?,?,?)";
			// 4. sql 전송객체 (PreparedStatement)	
			ps = con.prepareStatement(sql);							// 값 입력
			ps.setString(1, vo.getGenre());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getDirector());
			ps.setString(4, vo.getActor());
			ps.setString(5, vo.getExpl());


			// 5. 전송

			for(int i =0; i<count; i++) {
				ps.executeUpdate();
			}
		} finally {
			// 6. 닫기 
			ps.close();
			con.close();
		}
	}// end of info

	public ArrayList selectVideo(String text,String jemok) throws Exception{
		ArrayList data = new ArrayList();
		String  []a = new String[text.length()];

		// 2. Connection 연결객체 얻어오기
		Connection con = null;								// 전역변수 선언
		PreparedStatement ps = null;						// 전역변수 선언
		try {
			con = DriverManager.getConnection(URL,USER,PASS);		// 드라이브 통해 연결
	
			if(jemok.equals("제목")) {
				String sql = "SELECT VIDEONO, TITLE, DIRECTOR, ACTOR from VIDEO where title like '%'|| ? ||'%' ";
				// 4. sql 전송객체 (PreparedStatement)	
				ps = con.prepareStatement(sql);							// 값 입력
				// 5. 전송
				ps.setString(1,text);  // ? 자리에 해당 값 지정
			}else if(jemok.equals("감독")) {
				String sql = "SELECT VIDEONO, TITLE, DIRECTOR, ACTOR from VIDEO where director like '%'|| ? ||'%' ";
				// 4. sql 전송객체 (PreparedStatement)	
				ps = con.prepareStatement(sql);							// 값 입력
				// 5. 전송
				ps.setString(1,text);  // ? 자리에 해당 값 지정
			}else if (jemok.equals("")) {
				String sql = "SELECT VIDEONO, TITLE, DIRECTOR, ACTOR from VIDEO ";
				// 4. sql 전송객체 (PreparedStatement)	
				ps = con.prepareStatement(sql);							// 값 입력
			}
			

//			int cnt =0;
//			for (int i = 0; i<text.length(); i++) {
//				a[cnt] = Arrays.toString(text.split("",-1));
//				ps.setString(i,a[i] );
//			}
			ResultSet rs = ps.executeQuery();
			
				while(rs.next()) {
					ArrayList temp = new ArrayList();
					temp.add(rs.getInt("videono"));
					temp.add(rs.getString("title"));
					temp.add(rs.getString("director"));
					temp.add(rs.getString("actor"));
					data.add(temp);
	
			}
		}finally {
			con.close();
			ps.close();
		}
		// 6. 닫기 
		return data;

	}// selectvideo 종료
	/*
	public void delete(VideoVO vo) throws Exception{
		// 2. Connection 연결객체 얻어오기
		Connection con = null;								// 전역변수 선언
		PreparedStatement ps = null;						// 전역변수 선언

		try{
			con = DriverManager.getConnection(URL,USER,PASS);		// 드라이브 통해 연결

			// 3. sql 문장 만들기
			String sql = "DELETE FROM VIDEO where = ?";
			// 4. sql 전송객체 (PreparedStatement)	
			ps = con.prepareStatement(sql);							// 값 입력
			ps.setString(1, vo.getVideoNo());


			// 5. 전송
			ps.executeUpdate();

		} finally {
			// 6. 닫기 
			ps.close();
			con.close();
		}

	}// end of info
	 */




}
