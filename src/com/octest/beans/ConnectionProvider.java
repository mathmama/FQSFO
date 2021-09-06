package com.octest.beans;
import java.sql.*;
public class ConnectionProvider {

	private static Connection conn=null;
	 
	static {
		try {
			Class.forName(Provider.DRIVER);
			conn=DriverManager.getConnection(Provider.connection_url);
		} catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	}
	
	public static Connection getCon() {
		return conn;
	}
}
