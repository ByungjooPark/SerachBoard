package kr.co.hk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	public static final int viewCnt = 5;
	
	public static Connection getConn() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "hr";
		String pw = "hkitedu";
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("연결 허용!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if(rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					System.out.println("rs Close err");
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
					System.out.println("ps Close err");
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					System.out.println("conn Close err");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn, PreparedStatement ps) {
		close(conn, ps, null);
	}
	
	//리스트 검색
	public static List<SBoardVO> getBoardList(String searchWord, String searchType, int page) {
		List<SBoardVO> list = new ArrayList<SBoardVO>();
		int ps_index = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int startIndex = ((page - 1) * viewCnt) + 1;
		int endIndex = page * viewCnt;
		searchWord = '%' + searchWord + '%';
		
		System.out.println("startIndex : " + startIndex);
		System.out.println("endIndex : " + endIndex);
		String sql = " select x.rum, x.board_no, x.board_title, x.regdate, x.cnt "
				+ " from( "
			    + " select rownum as rum, a.board_no, a.board_title, a.regdate, a.cnt "
			    + " from( "
		        + " select board_no, board_title, regdate, cnt "
		        + " from s_board ";
		
		        if(searchType.equals("0")) {
					sql += " where board_title like ? or board_content like ? ";
				} else if(searchType.equals("1")) {
					sql += " where board_title like ? ";
				} else if(searchType.equals("2")) {
					sql += " where board_content like ? ";
				}
		
        sql += " order by BOARD_NO " 
        		+ " ) a "
        		+ " where rownum <= ? "
        		+ " ) x "
        		+ " where x.rum >= ? ";

		
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(++ps_index, searchWord);
			if(searchType.equals("0")) {
				ps.setString(++ps_index, searchWord);
			}
			ps.setInt(++ps_index, endIndex);
			ps.setInt(++ps_index, startIndex);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				SBoardVO vo = new SBoardVO();
				vo.setBoard_no(rs.getInt("board_no"));
				vo.setBoard_title(rs.getString("board_title"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setCnt(rs.getInt("cnt"));
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return list;
	}
	
	
	// totalpagecnt 구하기
	public static int getTotalPageCnt(String searchWord, String searchType) {
		int totalPageCnt = 0;
		int ps_index = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		searchWord = '%' + searchWord + '%';
		String sql = " select count(board_no) as totalpagecnt "
		        + " from s_board ";
		
		        if(searchType.equals("0")) {
					sql += " where board_title like ? or board_content like ? ";
				} else if(searchType.equals("1")) {
					sql += " where board_title like ? ";
				} else if(searchType.equals("2")) {
					sql += " where board_content like ? ";
				}
		
        sql += " order by BOARD_NO ";
		
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(++ps_index, searchWord);
			if(searchType.equals("0")) {
				ps.setString(++ps_index, searchWord);
			}
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				totalPageCnt = rs.getInt("totalpagecnt");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		System.out.println("totalPageCnt : " + totalPageCnt);
		return totalPageCnt;
	}
}
