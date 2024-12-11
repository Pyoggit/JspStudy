package kr.co.dev.memberone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.dev.common.ConnectionPool;

public class StudentDAO {
	private final String SELECT_SQL = "SELECT * FROM STUDENT";
	private final String SELECT_BY_ID_SQL = "SELECT count(*) as count FROM STUDENT WHERE ID = ?";
	private final String SELECT_LOGIN_CHECK_SQL = "SELECT * FROM STUDENT WHERE ID = ? AND PWD = ?";
	private final String INSERT_SQL = "INSERT INTO STUDENT VALUES(?,?,?,?,?,?,?,?,?,?)";
	private final String DELETE_SQL = "DELETE FROM STUDENT WHERE ID = ?";
	private final String UPDATE_SQL = "UPDATE STUDENT SET PWD = ?, EMAIL = ?, NAME = ?, BIRTH = ? WHERE ID = ?";

	// 전체를 DB에서 출력
	public boolean selectIdCheck(StudentVO svo) {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection con = cp.dbCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(SELECT_BY_ID_SQL);
			pstmt.setString(1, svo.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt("COUNT");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cp.dbClose(con, pstmt, rs);
		}
		return (count != 0) ? (true) : (false);
	}

	public Boolean insertDB(StudentVO svo) {

		ConnectionPool cp = ConnectionPool.getInstance();
		Connection con = cp.dbCon();
		PreparedStatement pstmt = null;
		int count = 0;
		int rs = 0;
		try {
			pstmt = con.prepareStatement(INSERT_SQL);
			pstmt.setString(1, svo.getId());
			pstmt.setString(2, svo.getPass());
			pstmt.setString(3, svo.getName());
			pstmt.setString(4, svo.getPhone1());
			pstmt.setString(5, svo.getPhone2());
			pstmt.setString(6, svo.getPhone3());
			pstmt.setString(7, svo.getEmail());
			pstmt.setString(8, svo.getZipcode());
			pstmt.setString(9, svo.getAddress1());
			pstmt.setString(10, svo.getAddress2());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cp.dbClose(con, pstmt);
		}
		return (count > 0) ? false : true;
	}

	/*
	 * // 아이디를 받아서 아이디에 맞는 레코드 출력 public StudentVO selectByIdDB(StudentVO svo) {
	 * Connection con = DBUtility.dbCon(); PreparedStatement pstmt = null; ResultSet
	 * rs = null; try { pstmt = con.prepareStatement(SELECT_BY_ID_SQL);
	 * pstmt.setString(1, svo.getId()); rs = pstmt.executeQuery(); if (rs.next()) {
	 * String id = rs.getString("ID"); String pwd = rs.getString("PWD"); String
	 * email = rs.getString("EMAIL"); String name = rs.getString("NAME"); int birth
	 * = rs.getInt("BIRTH"); svo = new StudentVO(id, pwd, email, name, birth); }
	 * else { svo = null; } } catch (SQLException e) { e.printStackTrace(); } return
	 * svo; }
	 * 
	 * // id, pwd를받아서 맞는 레코드를 출력 public StudentVO selectLoginCheckDB(StudentVO svo)
	 * { Connection con = DBUtility.dbCon(); PreparedStatement pstmt = null;
	 * ResultSet rs = null; try { pstmt =
	 * con.prepareStatement(SELECT_LOGIN_CHECK_SQL); pstmt.setString(1,
	 * svo.getId()); pstmt.setString(2, svo.getPwd()); rs = pstmt.executeQuery(); if
	 * (rs.next()) { String id = rs.getString("ID"); String pwd =
	 * rs.getString("PWD"); String email = rs.getString("EMAIL"); String name =
	 * rs.getString("NAME"); int birth = rs.getInt("BIRTH"); svo = new StudentVO(id,
	 * pwd, email, name, birth); } else { svo = null; } } catch (SQLException e) {
	 * e.printStackTrace(); } return svo; }
	 * 
	 * public Boolean insertDB(StudentVO svo) { Connection con = DBUtility.dbCon();
	 * PreparedStatement pstmt = null; int rs = 0; try { pstmt =
	 * con.prepareStatement(INSERT_SQL); pstmt.setString(1, svo.getId());
	 * pstmt.setString(2, svo.getPwd()); pstmt.setString(3, svo.getEmail());
	 * pstmt.setString(4, svo.getName()); pstmt.setInt(5, svo.getBirth()); rs =
	 * pstmt.executeUpdate(); } catch (SQLException e) { e.printStackTrace(); }
	 * return (rs == 0) ? false : true; }
	 * 
	 * public Boolean deleteDB(StudentVO svo) { Connection con = DBUtility.dbCon();
	 * PreparedStatement pstmt = null; int rs = 0; try { pstmt =
	 * con.prepareStatement(DELETE_SQL); pstmt.setString(1, svo.getId()); rs =
	 * pstmt.executeUpdate(); } catch (SQLException e) { e.printStackTrace(); }
	 * return (rs == 0) ? false : true; }
	 * 
	 * public Boolean updateDB(StudentVO svo) { Connection con = DBUtility.dbCon();
	 * PreparedStatement pstmt = null; int rs = 0; try { pstmt =
	 * con.prepareStatement(UPDATE_SQL); pstmt.setString(1, svo.getPwd());
	 * pstmt.setString(2, svo.getEmail()); pstmt.setString(3, svo.getName());
	 * pstmt.setInt(4, svo.getBirth()); rs = pstmt.executeUpdate(); } catch
	 * (SQLException e) { e.printStackTrace(); } return (rs == 0) ? false : true; }
	 */
}
