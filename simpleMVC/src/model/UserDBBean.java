package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//2.DB접속해제 부분을 제외하고는 USER SEARCH로 분리
//3.INSERT 메소드를 추가
//-INDEX.JSP에서 받아와서 CONTROLLER가 가지고 있는 회원정보를 DB에 넘겨주는 부분으로 추가
//-DB에 넘겨주는 부분으로 추가 insert into member(?,?,?) 
//	-> user.getfirstname(), user.getlastname(), user.getemail()  	
//4.SEARCH한 결과를 RESULT.JSP에 출력


public class UserDBBean {
	static final String driver = "com.mysql.cj.jdbc.Driver"; //정적변수로 드라이버를 생성
	static final String url = "jdbc:mysql://localhost:3306/simplemvc?serverTimezone=UTC";
	static final String userid = "root";
	static final String userpw = "1234";
	
	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	
	public static void DBConnect() {

		//1.DB접속
		System.out.print("DB접속 : ");
		try {
			Class.forName(driver);//드라이버 연결
			conn = DriverManager.getConnection(url, userid, userpw);
			if(conn != null)
				System.out.println("성공");
			else
				System.out.println("실패");
			
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException : ");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQLException : ");
			e.printStackTrace();
		}
	}

	public static void DBClose() {
		try {
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void searchUser() {
		//2.PSTMT문 준비 -> 쿼리전송
		String sql = "select * from member";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("first name : " + rs.getString("firstname"));
				System.out.println("last name : " + rs.getString("lastname"));
				System.out.println("email : " + rs.getString("email"));
				//rs.getString("email")는 rs.getString(3)으로 쓸 수 있음. 3은 컬럼의 순서
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void insertUser(String ufirstname, String ulastname, String uemail) {
		//2.PSTMT문 준비 -> 쿼리전송
		String sql = "inset into member(?, ?, ?)";
		try {
			pstmt.setString(1, ufirstname);
			pstmt.setString(2, ulastname);
			pstmt.setString(3, uemail);
			pstmt = conn.prepareStatement(sql);
			int num = pstmt.executeUpdate();
			if(num == 0)
				System.out.println("데이터 추가 실패");
			else
				System.out.println("데이터 추가 성공");
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) throws SQLException{
	
		DBConnect();
		searchUser();
		DBClose();
		
	}

}


