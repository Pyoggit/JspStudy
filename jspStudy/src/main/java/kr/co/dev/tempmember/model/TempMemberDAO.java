package kr.co.dev.tempmember.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.dev.common.ConnectionPool;
import kr.co.dev.common.DBUtility;

public class TempMemberDAO {
	private final String SELECT_SQL = "SELECT * FROM TEMPMEMBER";
	private final String SELECT_BY_ID_SQL = "SELECT * FROM TEMPMEMBER WHERE ID = ?";
	private final String SELECT_LOGIN_CHECK_SQL = "SELECT * FROM TEMPMEMBER WHERE ID = ? AND PWD = ?";
	private final String INSERT_SQL = "INSERT INTO TEMPMEMBER VALUES(?,?,?,?,?)";
	private final String DELETE_SQL = "DELETE FROM TEMPMEMBER WHERE ID = ?";
	private final String UPDATE_SQL = "UPDATE TEMPMEMBER SET PWD = ?, EMAIL = ?, NAME = ?, BIRTH = ? WHERE ID = ?";

	// 전체를 DB에서 출력
	public ArrayList<TempMemberVO> selectDB() {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection con = cp.dbCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<TempMemberVO> tmList = new ArrayList<TempMemberVO>();
		try {
			pstmt = con.prepareStatement(SELECT_SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("ID");
				String passwd = rs.getString("PASSWD");
				String name = rs.getString("NAME");
				String memNum1 = rs.getString("MEM_NUM1");
				String memNum2 = rs.getString("MEM_NUM2");
				String email = rs.getString("E_MAIL");
				String phone = rs.getString("PHONE");
				String zipcode = rs.getString("ZIPCODE");
				String address = rs.getString("ADDRESS");
				String job = rs.getString("JOB");
				
				TempMemberVO tmvo = new TempMemberVO(id, passwd, name, memNum1, memNum2, email, phone, zipcode, address, job);
				tmList.add(tmvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cp.dbClose(con, pstmt, rs);
		}
		return tmList;
	}

	/*
	 * // 아이디를 받아서 아이디에 맞는 레코드 출력 public TempMemberVO selectByIdDB(TempMemberVO svo)
	 * { Connection con = DBUtility.dbCon(); PreparedStatement pstmt = null;
	 * ResultSet rs = null; try { pstmt = con.prepareStatement(SELECT_BY_ID_SQL);
	 * pstmt.setString(1, svo.getId()); rs = pstmt.executeQuery(); if (rs.next()) {
	 * String id = rs.getString("ID"); String pwd = rs.getString("PWD"); String
	 * email = rs.getString("EMAIL"); String name = rs.getString("NAME"); int birth
	 * = rs.getInt("BIRTH"); svo = new TempMemberVO(id, pwd, email, name, birth); }
	 * else { svo = null; } } catch (SQLException e) { e.printStackTrace(); } return
	 * svo; }
	 * 
	 * // id, pwd를받아서 맞는 레코드를 출력 public TempMemberVO selectLoginCheckDB(TempMemberVO
	 * svo) { Connection con = DBUtility.dbCon(); PreparedStatement pstmt = null;
	 * ResultSet rs = null; try { pstmt =
	 * con.prepareStatement(SELECT_LOGIN_CHECK_SQL); pstmt.setString(1,
	 * svo.getId()); pstmt.setString(2, svo.getPwd()); rs = pstmt.executeQuery(); if
	 * (rs.next()) { String id = rs.getString("ID"); String pwd =
	 * rs.getString("PWD"); String email = rs.getString("EMAIL"); String name =
	 * rs.getString("NAME"); int birth = rs.getInt("BIRTH"); svo = new
	 * TempMemberVO(id, pwd, email, name, birth); } else { svo = null; } } catch
	 * (SQLException e) { e.printStackTrace(); } return svo; }
	 * 
	 * public Boolean insertDB(TempMemberVO svo) { Connection con =
	 * DBUtility.dbCon(); PreparedStatement pstmt = null; int rs = 0; try { pstmt =
	 * con.prepareStatement(INSERT_SQL); pstmt.setString(1, svo.getId());
	 * pstmt.setString(2, svo.getPwd()); pstmt.setString(3, svo.getEmail());
	 * pstmt.setString(4, svo.getName()); pstmt.setInt(5, svo.getBirth()); rs =
	 * pstmt.executeUpdate(); } catch (SQLException e) { e.printStackTrace(); }
	 * return (rs == 0) ? false : true; }
	 * 
	 * public Boolean deleteDB(TempMemberVO svo) { Connection con =
	 * DBUtility.dbCon(); PreparedStatement pstmt = null; int rs = 0; try { pstmt =
	 * con.prepareStatement(DELETE_SQL); pstmt.setString(1, svo.getId()); rs =
	 * pstmt.executeUpdate(); } catch (SQLException e) { e.printStackTrace(); }
	 * return (rs == 0) ? false : true; }
	 * 
	 * public Boolean updateDB(TempMemberVO svo) { Connection con =
	 * DBUtility.dbCon(); PreparedStatement pstmt = null; int rs = 0; try { pstmt =
	 * con.prepareStatement(UPDATE_SQL); pstmt.setString(1, svo.getPwd());
	 * pstmt.setString(2, svo.getEmail()); pstmt.setString(3, svo.getName());
	 * pstmt.setInt(4, svo.getBirth()); rs = pstmt.executeUpdate(); } catch
	 * (SQLException e) { e.printStackTrace(); } return (rs == 0) ? false : true; }
	 */
}
