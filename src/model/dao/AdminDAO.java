package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.DB;
import model.dto.AdminDTO;

public class AdminDAO {
	//--- 공통부분 시작 ---------------------------------------------------------------------------
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//메서드
	public void getConn() {
		try {
			conn = DB.dbConn();
			System.out.println("-- 오라클 접속 성공 --");
		} catch(Exception e) {
			System.out.println("-- 오라클 접속 실패 --");
		}
	}
	public void getConnClose() {
		DB.dbConnClose(rs, pstmt, conn);
	}	
	//--- 공통부분 종료 ---------------------------------------------------------------------------
	
	//로그인
	public AdminDTO getLogin(AdminDTO dto) {
		AdminDTO dtoLogin = new AdminDTO();
		getConn();
		try {
			String sql = "";
			//sql += "select admin.*, trunc(sysdate - upd_date) passChg_period from admin";
			sql += "select admin.*, (curdate() - upd_date) passChg_period from admin";
			sql += " where id=? and passwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPasswd());			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dtoLogin.setNo(rs.getInt("no"));
				dtoLogin.setId(rs.getString("id"));
				dtoLogin.setName(rs.getString("name"));
				dtoLogin.setDep_name(rs.getString("dep_name"));
				dtoLogin.setLevel_staff(rs.getString("level_staff"));
				dtoLogin.setAuthority(rs.getString("authority"));
				dtoLogin.setPassChg_period(rs.getInt("passChg_period"));				
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return dtoLogin;
	}
	
	
}
