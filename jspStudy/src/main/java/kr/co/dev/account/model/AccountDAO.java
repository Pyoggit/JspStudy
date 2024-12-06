package kr.co.dev.account.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.dev.common.DBUtility;

public class AccountDAO {
    private final String SELECT_SQL = "SELECT * FROM ACCOUNT ORDER BY NO";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM ACCOUNT WHERE ID = ?";
    private final String SELECT_LOGIN_CHECK_SQL = "SELECT * FROM ACCOUNT WHERE ID = ? AND PWD = ?";
    private final String INSERT_SQL = "INSERT INTO ACCOUNT VALUES((SELECT NVL(MAX(NO),0)+1 FROM ACCOUNT),?,?,?,SYSDATE)";
    private final String DELETE_SQL = "DELETE FROM ACCOUNT WHERE NO = ?";
    private final String UPDATE_SQL = "UPDATE ACCOUNT SET NAME= ? , PWD = ? WHERE ID = ?";

    //전체를 DB에서 출력
    public ArrayList<AccountVO> selectDB() {
        Connection con = DBUtility.dbCon();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<AccountVO> aList = new ArrayList<AccountVO>();
        try {
            pstmt = con.prepareStatement(SELECT_SQL);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int no = rs.getInt("NO");
                String name = rs.getString("NAME");
                String id = rs.getString("ID");
                String pwd = rs.getString("PWD");
                Date regdate = rs.getDate("REGDATE");
                AccountVO avo = new AccountVO(no, name, id, pwd, regdate);
                aList.add(avo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aList;
    }

    //아이디를 받아서 아이디에 맞는 레코드 출력
    public AccountVO selectByIdDB(AccountVO avo) {
        Connection con = DBUtility.dbCon();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(SELECT_BY_ID_SQL);
            pstmt.setString(1, avo.getId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int no = rs.getInt("NO");
                String name = rs.getString("NAME");
                String id = rs.getString("ID");
                String pwd = rs.getString("PWD");
                Date regdate = rs.getDate("REGDATE");
                avo = new AccountVO(no, name, id, pwd, regdate);
            } else {
                avo = new AccountVO();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avo;
    }

    //id, pwd를받아서 맞는 레코드를 출력
    public AccountVO selectLoginCheckDB(AccountVO avo) {
        Connection con = DBUtility.dbCon();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(SELECT_LOGIN_CHECK_SQL);
            pstmt.setString(1, avo.getId());
            pstmt.setString(2, avo.getPwd());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int no = rs.getInt("NO");
                String name = rs.getString("NAME");
                String id = rs.getString("ID");
                String pwd = rs.getString("PWD");
                Date regdate = rs.getDate("REGDATE");
                avo = new AccountVO(no, name, id, pwd, regdate);
            } else {
                avo = new AccountVO();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avo;
    }

    public Boolean insertDB(AccountVO avo) {
        Connection con = DBUtility.dbCon();
        PreparedStatement pstmt = null;
        int rs = 0;
        try {
            pstmt = con.prepareStatement(INSERT_SQL);
            pstmt.setString(1, avo.getName());
            pstmt.setString(2, avo.getId());
            pstmt.setString(3, avo.getPwd());
            rs = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (rs == 0) ? false : true;
    }

    public Boolean deleteDB(AccountVO avo) {
        Connection con = DBUtility.dbCon();
        PreparedStatement pstmt = null;
        int rs = 0;
        try {
            pstmt = con.prepareStatement(DELETE_SQL);
            pstmt.setInt(1, avo.getNo());
            rs = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (rs == 0) ? false : true;
    }

    public Boolean updateDB(AccountVO avo) {
        Connection con = DBUtility.dbCon();
        PreparedStatement pstmt = null;
        int rs = 0;
        try {
            pstmt = con.prepareStatement(UPDATE_SQL);
            pstmt.setString(1, avo.getName());
            pstmt.setString(2, avo.getPwd());
            pstmt.setString(3, avo.getId());
            rs = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (rs == 0) ? false : true;
    }
}
