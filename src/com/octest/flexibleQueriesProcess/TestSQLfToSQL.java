package com.octest.flexibleQueriesProcess;

import java.sql.SQLException;

import com.octest.beans.OracleConnection;

import net.sf.jsqlparser.JSQLParserException;
import slib.utils.ex.SLIB_Ex_Critic;

public class TestSQLfToSQL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		OracleConnection orc = new OracleConnection();
		orc.setHostname("localhost");
		orc.setUserName("user1");
		orc.setUserPassword("user1");
		orc.setPortNumber("1521");
		orc.setSid("orcl");
		orc.ConnectionCheckup();
		String SQLfquery = "SELECT * FROM movie ";
		SQLfToSQL Fquery = new SQLfToSQL(SQLfquery, orc.getCnn());
		try {
			System.out.println(Fquery.SQLftoSQL());
			long end = System.currentTimeMillis();
			System.out.println("Time " + (end - start) + " MS");
		} catch (SLIB_Ex_Critic e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSQLParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
