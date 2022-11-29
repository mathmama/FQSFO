package com.octest.labo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.octest.beans.OracleConnection;

public class SwitchColumnType {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		OracleConnection orc = new OracleConnection();
		orc.setHostname("localhost");
		orc.setUserName("user1");
		orc.setUserPassword("user1");
		orc.setPortNumber("1521");
		orc.setSid("orcl");
		orc.ConnectionCheckup();
		ResultSet rs = null;
		Statement stmt = null;
		long start = System.currentTimeMillis();

		try {

			Connection con = orc.getCnn();

			// step3 create the statement object
			stmt = con.createStatement();

			// step4 execute query
			rs = stmt.executeQuery("select * from emp where age in ('young','old')");
			System.out.println("is a ordinary query");
			while (rs.next())
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));

			// step5 close the connection object
			con.close();
		} catch (SQLException e) {
			System.out.println(e.hashCode());
			if (e.getErrorCode() == 1722) {
				System.out.println("is a fuzzy query");
				try {
					rs = stmt.executeQuery("select * from femp where \"age.young\">0");
					// rs = stmt.executeQuery("select * from ffemp where age between 16 and 40");
					while (rs.next())
						System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		long end = System.currentTimeMillis();
		System.out.println("Time : " + (end - start) + " MS");

	}

}
