package com.octest.beans;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

public class SignupDao {
	private int status;
	
	public void createUser(HttpServletRequest request){
		try{
			Connection con = ConnectionProvider.getCon();
			con.setAutoCommit(false);
			String first_name=request.getParameter("first_name");
			String last_name=request.getParameter("last_name");
			String email = request.getParameter("email");
			String pwd = request.getParameter("password");
			
			//String cmd = String.format("SELECT * FROM USERS WHERE EMAIL= %s AND PASSWORD = %s ;",email,pwd);
			String cmd = "INSERT INTO USERS (EMAIL,PASSWORD,LAST_NAME,FIRST_NAME) VALUES(\'"+ email+"\',\'"+pwd+"\',\'"+last_name+"\',\'"+first_name+"\');";
			status=con.createStatement().executeUpdate(cmd);

		}catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	       //  System.exit(0);
	       //  status=rs.next();
	      }
	}
	public int isStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
