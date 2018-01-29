package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;
import com.javaex.vo.GuestbookVo;
import com.javaex.vo.UserBoardVo;
import com.javaex.vo.UserVo;

public class BoardDao {

	public void write(BoardVo boardVo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into board values(seq_board_no.nextval, ?, ?, 0, sysdate, ?)";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setInt(3, boardVo.getUser_no());

			pstmt.executeUpdate();

			// 4.결과처리

		} catch (ClassNotFoundException e) {

			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {

			System.out.println("error:" + e);

		} finally {

			// 5. 자원정리
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

	public List<UserBoardVo> getList() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<UserBoardVo> userboard = new ArrayList<UserBoardVo>();

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = " select b.no boardno, title, content, hit, to_char(b.reg_date, 'YY-MM-DD HH:MI:SS') reg_date, user_no, name "
					+ " from board b, users u "
					+ " where b.user_no = u.no "
					+ " order by b.no desc ";
			 

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {

				UserBoardVo boardVo = new UserBoardVo();

				int boardno = rs.getInt("boardno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				String regdate = rs.getString("reg_date");
				int userno = rs.getInt("user_no");
				String name = rs.getString("name");

				boardVo.setBoardno(boardno);
				boardVo.setTitle(title);
				boardVo.setContent(content);
				boardVo.setHit(hit);
				boardVo.setDate(regdate);
				boardVo.setUser_no(userno);
				boardVo.setName(name);

				userboard.add(boardVo);

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
		return userboard;
	}

	public BoardVo getList(int boardno) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BoardVo boardVo = null;
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = " select no, title, content, user_no, hit from board where no = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, boardno);

			rs = pstmt.executeQuery();

			// 4.결과처리
			if (rs.next()) { // 한줄만받아올거니깐

				boardVo = new BoardVo();

				int no = rs.getInt("no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int user_no = rs.getInt("user_no");
				int hit = rs.getInt("hit");

				boardVo.setNo(no);
				boardVo.setTitle(title);
				boardVo.setContent(content);
				boardVo.setUser_no(user_no);
				boardVo.setHit(hit);

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
		return boardVo;
	}
	
	public void delete(int deleteno) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = " delete from board where no = ? ";
		    pstmt = conn.prepareStatement(query);
		    pstmt.setInt(1, deleteno);

		    pstmt.executeUpdate();			
		    
		    // 4.결과처리

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
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

	public void modify(BoardVo boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = " update board " + 
						   " set content = ?, " +
						   " 	 title = ? " + 
						   " where no = ? ";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, boardVo.getContent()); 
			pstmt.setString(2, boardVo.getTitle());
			pstmt.setInt(3, boardVo.getNo());
			
			pstmt.executeUpdate();			
			
			// 4.결과처리
		} catch (ClassNotFoundException e) {
			
			System.out.println("error: 드라이버 로딩 실패 - " + e);
	
		} catch (SQLException e) {
		
			System.out.println("error:" + e);

		} finally {

			// 5. 자원정리
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
	
	public void count(int boardno, int hit) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = " update board " + 
						   " set hit = ? " + 
						   " where no = ? ";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1,	hit);
			pstmt.setInt(2, boardno);

			pstmt.executeUpdate();			
			
			// 4.결과처리
		} catch (ClassNotFoundException e) {
			
			System.out.println("error: 드라이버 로딩 실패 - " + e);
	
		} catch (SQLException e) {
		
			System.out.println("error:" + e);

		} finally {

			// 5. 자원정리
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
	
	public List<UserBoardVo> search(String find) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<UserBoardVo> userboardvo = new ArrayList<UserBoardVo>();
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = " select b.no boardno, title, name, hit, to_char(b.reg_date, 'YY-MM-DD HH:MI:SS') reg_date " + 
						   " from board b, users u " + 
						   " where b.user_no = u.no " + 
						   " and title like ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, "%"+find+"%");

			rs = pstmt.executeQuery();

			// 4.결과처리
			if (rs.next()) { // 한줄만받아올거니깐

				UserBoardVo boardVo = new UserBoardVo();

				int boardno = rs.getInt("boardno");
				String title = rs.getString("title");
				String name = rs.getString("name");
				int hit = rs.getInt("hit");
				String regdate = rs.getString("reg_date");

				boardVo.setBoardno(boardno);
				boardVo.setTitle(title);
				boardVo.setName(name);
				boardVo.setHit(hit);
				boardVo.setDate(regdate);
				
				userboardvo.add(boardVo);

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
		return userboardvo;
	}
	
	
	
}
