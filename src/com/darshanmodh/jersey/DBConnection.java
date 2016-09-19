package com.darshanmodh.jersey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	@SuppressWarnings("finally")
	public static Connection createConnection() throws Exception {
		Connection con = null;
		try {
			Class.forName(Constants.dbClass);
			con = DriverManager.getConnection(Constants.dbUrl, Constants.dbUser, Constants.dbPwd);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return con;
		}
	}
	
	public static boolean checkLogin(String uname, String pwd) throws SQLException {
		boolean isUserAvailable = false;
		Connection dbCon = null;
		try {
			try {
				dbCon = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Statement stmt = dbCon.createStatement();
			String query = "SELECT * FROM user WHERE username = '" + uname + "' AND password='" + pwd + "'";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				isUserAvailable = true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch(Exception e) {
			if(dbCon != null)
				dbCon.close();
		} finally {
			if(dbCon != null)
				dbCon.close();
		}
		return isUserAvailable;
	}

	public static boolean insertUser(String uname, String name, String pwd) throws SQLException {
		boolean insertStatus = false;
		Connection dbCon = null;
		try {
			try {
				dbCon = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Statement stmt = dbCon.createStatement();
			String sql = "INSERT INTO user(name, username, password) VALUES ('" + name + "', '" + uname + "', '" + pwd + "')";
			int records = stmt.executeUpdate(sql);
			if(records > 0)
				insertStatus = true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			if(dbCon != null)
				dbCon.close();
		} finally {
			if(dbCon != null)
				dbCon.close();
		}
		return insertStatus;
	}
}
