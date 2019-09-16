package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.UserDataBean;

//2.DB접속해제 부분을 제외하고는 USER SEARCH로 분리
//3.INSERT 메소드를 추가
//-INDEX.JSP에서 받아와서 CONTROLLER가 가지고 있는 회원정보를 DB에 넘겨주는 부분으로 추가
//-DB에 넘겨주는 부분으로 추가 insert into members(?,?,?) 
//	-> user.getfirstname(), user.getlastname(), user.getemail()  	
//4.SEARCH한 결과를 RESULT.JSP에 출력


public class UserDBBean {
	static final String driver = "com.mysql.jdbc.Driver"; //정적변수로 드라이버를 생성
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
	
	public static List<UserDataBean> searchUser() {
		//2.PSTMT문 준비 -> 쿼리전송
		DBConnect();
		String sql = "select * from member";
		List<UserDataBean> userList = new ArrayList<UserDataBean>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserDataBean user = new UserDataBean(null, null, null);
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	public void insertUser(String firstname, String lastname, String email) {
		//2.PSTMT문 준비 -> 쿼리전송
		DBConnect();
		String sql = "insert into member(firstname, lastname, email) values(?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, firstname);;
			pstmt.setString(2, lastname);
			pstmt.setString(3, email);
			
			int num = pstmt.executeUpdate();
			
			if(num == 0)
				System.out.println("데이터 추가 실패");
			else
				System.out.println("데이터 추가 성공");
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
	}

}


