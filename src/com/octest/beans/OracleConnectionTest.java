package com.octest.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleConnectionTest {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		OracleConnection orc = new OracleConnection();
		orc.setHostname("localhost");
		orc.setUserName("user1");
		orc.setUserPassword("user1");
		orc.setPortNumber("1521");
		orc.setSid("orcl");
		orc.ConnectionCheckup();
		System.out.println("yes" + orc.getCnn().getClientInfo());
		Connection con = orc.getCnn();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select \"semantic_measure\" from semantic_predicate");
		while (rs.next())
			System.out.println(rs.getString(1));

	}

}
