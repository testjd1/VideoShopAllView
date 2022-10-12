package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import model.CustomerDao;
import model.vo.CustomerVO;

public class CustomerDaoImpl implements CustomerDao{
	final static String DRIVER 	= "oracle.jdbc.driver.OracleDriver";			// final static 붙인 경우 잘보이게 대문자로 변수명 선언
	final static String	URL 	= "jdbc:oracle:thin:@192.168.0.56:1521:xe";		// 오라클 링크 연결
	final static String USER 	= "scott";										// db 아이디
	final static String PASS 	= "tiger";										// 비밀번호



	public CustomerDaoImpl() throws Exception{
		// 1. 드라이버로딩
		Class.forName(DRIVER);
		System.out.println("드라이버 로딩 성공");

	}

	public void insertCustomer(CustomerVO vo) throws Exception{
		// 2. Connection 연결객체 얻어오기
		Connection con = null;								// 전역변수 선언
		PreparedStatement ps = null;						// 전역변수 선언

		try{
			con = DriverManager.getConnection(URL,USER,PASS);		// 드라이브 통해 연결

			// 3. sql 문장 만들기
			String sql = "INSERT INTO Customer(name,pNo,s_Pno,addr,email) VALUES(?,?,?,?,?)";
			// 4. sql 전송객체 (PreparedStatement)	
			ps = con.prepareStatement(sql);							// 값 입력
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getpNo());
			ps.setString(3, vo.getS_Pno());
			ps.setString(4, vo.getAddr());
			ps.setString(5, vo.getEmail());


			// 5. 전송
			ps.executeUpdate();
		} finally {
			// 6. 닫기 
			ps.close();
			con.close();
		}
	}// end of info
	
	/*
	 * 메소드명 : selectByTel
	 * 인자 : 검색할 전화번호
	 * 리턴 값 : 전화번호 검색에 따른 고객정보
	 * 역할 : 사용자가 입력한 전화번호를 받아서 해당하는 고객정보 리턴 
	 */
	

	public CustomerVO selectByTel(String tel) throws Exception{
		CustomerVO dao = new CustomerVO();
		// 2. 연결객체 얻어오기
		Connection con = null;								// 전역변수 선언
		PreparedStatement ps = null;						// 전역변수 선언
		try {
			con= DriverManager.getConnection(URL,USER,PASS);
			// 3. sql 문장 만들기
			String sql = "SELECT * FROM Customer where pNo = ?";
			// 4. 전송 객체
			ps = con.prepareStatement(sql);
			ps.setString(1, tel);  // ? 자리에 해당 값 지정
			// 5. 전송 - executeQuery()
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				dao.setName(rs.getString("NAME"));
				dao.setpNo(rs.getString("PNO"));
				dao.setS_Pno(rs.getString("S_PNO"));
				dao.setAddr(rs.getString("ADDR"));
				dao.setEmail(rs.getString("EMAIL"));
		
			}else {
				JOptionPane.showMessageDialog(null, "해당 정보가 없습니다.");
			}
			// 결과를 customerVo에 담기
		}finally {
			//6. 닫기
			ps.close();
			con.close();
		}
		
	

		return dao;

	} // end of selectBytel
	/*
	 * 메소드명 : updateCustomer
	 * 인자 : 수정할 값들
	 * 리턴 값 : 전화번호를 제외한 다른 위치에 있는 해당 값들 저장
	 * 역할 : 
	 */

	public int updateCustomer(CustomerVO vo) throws Exception{
		// 2. Connection 연결객체 얻어오기
				Connection con = null;								// 전역변수 선언
				PreparedStatement ps = null;						// 전역변수 선언
				int result = 0;
				try{
					con = DriverManager.getConnection(URL,USER,PASS);		// 드라이브 통해 연결

					// 3. sql 문장 만들기
					String sql = "Update Customer set name = ?, s_pno =?, "
							+ " addr = ?, email =  ?  "
							+ " where pno = ? ";
					// 4. sql 전송객체 (PreparedStatement)	
					ps = con.prepareStatement(sql);							// 값 입력
					ps.setString(1, vo.getName());
					ps.setString(2, vo.getS_Pno());
					ps.setString(3, vo.getAddr());
					ps.setString(4, vo.getEmail());
					ps.setString(5, vo.getpNo());


					// 5. 전송
					ps.executeUpdate();
				} finally {
					// 6. 닫기 
					ps.close();
					con.close();
				}		

		


		return result;
	} // end of Update
}
