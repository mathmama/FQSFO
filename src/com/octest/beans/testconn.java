package com.octest.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class testconn {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Statement stmt = null;
		try {
			Connection con = ConnectionProvider.getCon();
			con.setAutoCommit(false);
			System.out.println("Opened database successfully");
			/*
			 * stmt = con.createStatement(); String sql = "CREATE TABLE CONNECTIONS " +
			 * "(ID  INTEGER PRIMARY KEY AUTOINCREMENT," +
			 * " CONNECTIONNAME           TEXT    NOT NULL, " +
			 * " EMAIL           TEXT    NOT NULL, " +
			 * " CONNECTIONSTRING            TEXT     NOT NULL, " +
			 * " FOREIGN KEY(EMAIL) REFERENCES USERS(EMAIL))"; stmt.executeUpdate(sql);
			 */

			PreparedStatement ps = con
					.prepareStatement("INSERT INTO CONNECTIONS(CONNECTIONNAME,EMAIL ,CONNECTIONSTRING ) VALUES(?,?,?)");
			ps.setString(1, "connection2");
			ps.setString(2, "mathmama16@gmail.com");
			ps.setString(3, "localhost:user1:user1:1521:orcl");

			ps.executeUpdate();
			/*
			 * stmt = con.createStatement(); // stmt.executeUpdate("update connections set
			 * EMAIL='mathmama16@gmail.com' where // 1=1;"); ResultSet rs =
			 * stmt.executeQuery("select * from CONNECTIONS ;");
			 * 
			 * while (rs.next()) {
			 * 
			 * System.out.println("ID = " + rs.getString("email")); }
			 */
			con.commit();
			con.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Opened database successfully");
		System.out.println("Records created successfully");
	}

}
