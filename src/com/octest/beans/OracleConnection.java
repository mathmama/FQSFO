package com.octest.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnection {
	String UserName;
	String UserPassword;
	String Hostname;
	String PortNumber;
	String Sid;
	Connection cnn;
	String error;
	String ConnectionUrl;

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserPassword() {
		return UserPassword;
	}

	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}

	public String getHostname() {
		return Hostname;
	}

	public void setHostname(String hostname) {
		Hostname = hostname;
	}

	public String getPortNumber() {
		return PortNumber;
	}

	public void setPortNumber(String portNumber) {
		PortNumber = portNumber;
	}

	public String getSid() {
		return Sid;
	}

	public void setSid(String sid) {
		Sid = sid;
	}

	public void ConnectionCheckup() {
		try {
			// step1 load the driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			ConnectionUrl = "jdbc:oracle:thin:@" + Hostname + ":" + PortNumber + ":" + Sid;
			// System.out.println(ConnectionUrl);

			// step2 create the connection object
			Connection conn = DriverManager.getConnection(ConnectionUrl, UserName, UserPassword);

			if (conn != null)
				cnn = conn;
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			error = e.toString();
			// error = e.toString();
		} catch (Exception e) {
			// e.printStackTrace();
			error = e.toString();
		}

	}

	public void ConnectionWithUrl() {
		try {
			// step1 load the driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// System.out.println(ConnectionUrl);
			// step2 create the connection object
			Connection conn = DriverManager.getConnection(ConnectionUrl, UserName, UserPassword);

			if (conn != null)
				cnn = conn;
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			error = e.toString();
			// error = e.toString();
		} catch (Exception e) {
			// e.printStackTrace();
			error = e.toString();
		}

	}

	public String getConnectionUrl() {
		return ConnectionUrl;
	}

	public void setConnectionUrl(String connectionUrl) {
		ConnectionUrl = connectionUrl;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "OracleConnection [UserName=" + UserName + ", Hostname=" + Hostname + ", PortNumber=" + PortNumber
				+ ", Sid=" + Sid + "]";
	}

	public Connection getCnn() {
		return cnn;
	}

	public void setCnn(Connection cnn) {
		this.cnn = cnn;
	}

	public String getUrl() {
		return "jdbc:oracle:thin:@" + Hostname + ":" + PortNumber + ":" + Sid;
	}
}
