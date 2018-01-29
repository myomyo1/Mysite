package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao {
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void insert(UserVo vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";

			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String query = "insert into users values (seq_users_no.nextval, ? , ? , ? ,? )";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());

			int result = pstmt.executeUpdate();
			System.out.println("처리결과 : " + result);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public UserVo getUser(String email, String password) {// select
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo uservo = new UserVo();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String query = "select no, name" + " from  users" + " where email = ?" + " and password = ?";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				uservo.setNo(no);
				uservo.setName(name);
				System.out.println(no + ", \t" + name);
				
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return uservo;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public UserVo getUser(int no) { // 이름같은 함수, 매게변수가 다른 것 : 오버로딩
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo uservo = new UserVo();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String query = "select no, name , email, password, gender" + " from  users" + " where no = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				no = rs.getInt("no");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String gender = rs.getString("gender");

				uservo = new UserVo();
				uservo.setNo(no);
				uservo.setName(name);
				uservo.setEmail(email);
				uservo.setPassword(password);
				uservo.setGender(gender);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return uservo;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public int modify(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String query = "update users set name = ?, password = ? , gender =? where no=? "; // ★콤마자리확인

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getGender());
			pstmt.setInt(4, vo.getNo());

			result = pstmt.executeUpdate();

			System.out.println("처리결과 " + result);
			

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}
		return result;
	}

}
