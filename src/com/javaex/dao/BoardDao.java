package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.javaex.vo.BoardVo;
import com.javaex.vo.GuestbookVo;
import com.javaex.vo.UserVo;

public class BoardDao {
	public List<BoardVo> showListAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<BoardVo> bList = new ArrayList<>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String query = "select b.no, b.title, u.name, b.hit, to_char(b.reg_date, 'YY-MM-DD hh:mi') reg_date from board b, users u where b.user_no=u.no order by reg_date desc, b.no desc";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String name = rs.getString("name");
				int hit = rs.getInt("hit");
				String regDate = rs.getString("reg_date");
				BoardVo vo = new BoardVo(no, title, name, hit, regDate);
				bList.add(vo);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

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
		return bList;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public BoardVo viewBoard(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo= new BoardVo();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String query = "select title, content, user_no from board where no =?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				int userNo =rs.getInt("user_no");
				vo.setNo(no);
				vo.setUserNo(userNo);
				vo.setTitle(title);
				vo.setContent(content);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

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
		return vo;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void write (BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";

			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String query = "insert into board values (seq_b_no.nextval, 0, ?, ? ,?,sysdate)";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, vo.getUserNo());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();
			
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
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void delete(int no) {
		Connection conn = null; 
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			String query = "delete from board where no = ?"; 
			pstmt = conn.prepareStatement(query);
		
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate(); 
			
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
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	public int modify(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String query = "update board set title=?, content=? where no=? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getUserNo());

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
	
/////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void countHit(int no) {
		Connection conn = null; 
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			String query = "update board set hit = hit+1 where no = ? ";  //★콤마자리확인
			pstmt = conn.prepareStatement(query);
		
			pstmt.setInt(1, no);
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
	
}
