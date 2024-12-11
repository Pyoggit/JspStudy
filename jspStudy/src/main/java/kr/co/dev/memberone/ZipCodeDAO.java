package kr.co.dev.memberone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.dev.common.ConnectionPool;

public class ZipCodeDAO {

	private final String SELECT_BY_ID_SQL = "SELECT count(*) as count FROM STUDENT WHERE ID = ?";
	private final String SELECT_SQL = "select * from zipcode where dong like ?";


	// 전체를 DB에서 출력
	public ArrayList<ZipCodeVO> selectZipCode(ZipCodeVO zvo) {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection con = cp.dbCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ZipCodeVO> zipList = new ArrayList<ZipCodeVO>();

		try {
			pstmt = con.prepareStatement(SELECT_SQL);
			String dongValue = zvo.getDong()+ "%";
			pstmt.setString(1, dongValue);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String zipcode = rs.getString("ZIPCODE");
				String sido = rs.getString("SIDO");
				String gugun = rs.getString("GUGUN");
				String dong = rs.getString("DONG");
				String bunji = rs.getString("BUNJI");

				ZipCodeVO obj = new ZipCodeVO(zipcode, sido, gugun, dong, bunji);
				zipList.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cp.dbClose(con, pstmt, rs);
		}
		return zipList;
	}
}
