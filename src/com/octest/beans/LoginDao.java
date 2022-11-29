package com.octest.beans;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

public class LoginDao {
	private User user = new User();
	public Connection con;

	public void validate(HttpServletRequest request) {
		try {
			con = ConnectionProvider.getCon();
			con.setAutoCommit(false);
			String email = request.getParameter("email");
			String pwd = request.getParameter("pwd");
			// String cmd = String.format("SELECT * FROM USERS WHERE EMAIL= %s AND PASSWORD
			// = %s ;",email,pwd);
			String cmd = "SELECT  * FROM users WHERE email=\'" + email + "\'" + " AND password=\'" + pwd + "\'";
			/*
			 * PreparedStatement
			 * ps=con.prepareStatement("SELECT * FROM USERS WHERE EMAIL=? AND PASSWORD=?;");
			 * ps.setString(1,email); ps.setString(2, pwd);
			 */

			ResultSet rs = con.createStatement().executeQuery(cmd);
			while (rs.next()) {
				// creation d'un objet User :
				user.setEmail(rs.getString("email"));
				user.setFirst_Name(rs.getString("first_name"));
				user.setLast_Name(rs.getString("last_name"));
				user.setPassword(rs.getString("password"));
				// System.out.println("status"+status);
			}
			rs.close();
			// con.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
			// status=rs.next();
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
