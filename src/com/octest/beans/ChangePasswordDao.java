package com.octest.beans;

import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;

public class ChangePasswordDao {
private int status ;

public void validate(HttpServletRequest request){
	try{
		Connection con = ConnectionProvider.getCon();
		con.setAutoCommit(false);
		String email = request.getParameter("email");
		String pwd = request.getParameter("newpassword");
		//String cmd = String.format("SELECT * FROM USERS WHERE EMAIL= %s AND PASSWORD = %s ;",email,pwd);
		String cmd = "UPDATE users set  password=\'"+pwd+"\' WHERE email=\'"+ email+"\'";
		/*
		PreparedStatement ps=con.prepareStatement("SELECT * FROM USERS WHERE EMAIL=? AND PASSWORD=?;");
		ps.setString(1,email);
		ps.setString(2, pwd);
		*/
		
		 setStatus(con.createStatement().executeUpdate(cmd));
	     con.commit();
		}catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
       //  System.exit(0);
       //  status=rs.next();
      }
}

public int getStatus() {
	return status;
}

public void setStatus(int status) {
	this.status = status;
}
}
