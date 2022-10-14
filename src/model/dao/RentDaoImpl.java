package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.RentDao;
import model.vo.RentVo;


public class RentDaoImpl implements RentDao {
	final static String DRIVER 	= "oracle.jdbc.driver.OracleDriver";			// final static 붙인 경우 잘보이게 대문자로 변수명 선언
	final static String	URL 	= "jdbc:oracle:thin:@192.168.0.56:1521:xe";		// 오라클 링크 연결
	final static String USER 	= "scott";										// db 아이디
	final static String PASS 	= "tiger";										// 비밀번호

	Connection con;

	public RentDaoImpl() throws Exception{
		// 1. 드라이버로딩
		Class.forName(DRIVER);
		System.out.println("드라이버 로딩 성공");			
	}
	/*
	 * 메소드명 : rentVideo
	 * 인자 : 검색할 전화번호, 비디오 번호
	 * 리턴 값 : 대여 여부 확인
	 * 역할 : 아직 반납 안했으면 대여 못하고 , 반납했으면 대여 가능 
	 */

	public void rentVideo(String tel, int vnum) throws Exception{

		// 2. Connection 연결객체 얻어오기
		Connection con = null;								// 전역변수 선언
		PreparedStatement ps = null;						// 전역변수 선언
		RentVo ro = new RentVo();
		try {
			con = DriverManager.getConnection(URL,USER,PASS);		// 드라이브 통해 연결
			String sql = "Select ReturnYn from rent where videono = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, vnum);// 값 입력
			ResultSet rs = ps.executeQuery();
			rs.next();
		
			String yn = rs.getString("ReturnYn");
			System.out.println(yn);
			if(yn.equals("Y")) {
				sql = "Insert into Rent (rentno,pno,videono,rentdate,returnYn)"
						+ " Values(seq_rent1.nextval,?,?,sysdate,'N')";
				// 4. sql 전송객체 (PreparedStatement)	
				ps = con.prepareStatement(sql);
				ps.setString(1, tel);
				ps.setInt(2, vnum);// 값 입력
				// 5. 전송
				ps.executeUpdate();

			}else {
				JOptionPane.showMessageDialog(null, "아직 반납하지 않았습니다.");
			}


			// 3. sql 문장 만들기



		}finally {
			// 6. 닫기 
			ps.close();
			con.close();
		}
	}	// end of rentVideo
	
	
	/*
	 * 메소드명 : returnVideo
	 * 인자 : 비디오 번호
	 * 리턴 값 : 반납여부
	 * 역할 : 반납하면 반납여부칸 N -> Y로 변경 
	 */

	public void returnVideo(int vnum) throws Exception{
		// 2. Connection 연결객체 얻어오기
		Connection con = null;								// 전역변수 선언
		PreparedStatement ps = null;						// 전역변수 선언
		try {
			con = DriverManager.getConnection(URL,USER,PASS);		// 드라이브 통해 연결
			String sql = "UPDATE rent set returnYn='Y'"
					+ " where videono = ? and returnYn = 'N' ";

			// 4. sql 전송객체 (PreparedStatement)	
			ps = con.prepareStatement(sql);
			ps.setInt(1, vnum);// 값 입력



			// 5. 전송
			ps.executeUpdate();

		}finally {
			// 6. 닫기 
			ps.close();
			con.close();
		}
	}	// end of returnVideo
	
	/*
	 * 메소드명 : searchName
	 * 인자 : 검색할 전화번호
	 * 리턴 값 : 전화번호 검색에 따른 고객 이름 출력
	 * 역할 : 사용자가 입력한 전화번호를 받아서 해당하는 고객 이름 리턴 
	 */
	
	public String searchName(String tel) throws Exception{
		// 2. Connection 연결객체 얻어오기
		Connection con = null;								// 전역변수 선언
		PreparedStatement ps = null;						// 전역변수 선언
		String name = null;
		ResultSet rs=  null;
		try {
			con = DriverManager.getConnection(URL,USER,PASS);		// 드라이브 통해 연결
			String sql = "Select name from customer where  pno= ?";
			// 4. sql 전송객체 (PreparedStatement)	
			ps = con.prepareStatement(sql);
			ps.setString(1, tel);// 값 입력
		
			rs = ps.executeQuery();
			
			if(rs.next()) {
				 name=rs.getString("name");
			}
			



		} finally {
			// 6. 닫기 
			ps.close();
			con.close();

		}
		return name;
	}	//end of searchName
	

	/*
	 * 메소드명 : overdue
	 * 인자 : 미납여부 확인
	 * 리턴 값 : 반납 안한 정보들 
	 * 역할 : 아직 반납 안한 정보들 출력 
	 */
	@Override
	public ArrayList overdue() throws Exception {
		ArrayList data=new ArrayList();

		//2. 연결객체 
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DriverManager.getConnection(URL,USER,PASS);

			//3. sql
			String sql="select    v.videono videono, v.title title, c.name name, "
					+ "   c.pno tel, r.rentdate+7 returndate, r.returnyn return "
					+ " from  customer c inner join rent r on c.pno=r.pno "
					+ " inner join video v on v.videono=r.videono "
					+ " where r.returnyn = 'N' ";



			//4. 전송객체
			ps=con.prepareStatement(sql);

			//5. 전송
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				ArrayList temp = new ArrayList<>();
				temp.add(rs.getString("videono"));
				temp.add(rs.getString("title"));
				temp.add(rs.getString("name"));
				temp.add(rs.getString("tel"));
				temp.add(rs.getString("returndate"));
				temp.add(rs.getString("return"));
				data.add(temp);
			}
		}finally{
			//6.닫기
			con.close();
			ps.close();
		}   
		return data;
	}	// end of ovderdue


}
