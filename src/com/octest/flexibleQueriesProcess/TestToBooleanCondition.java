package com.octest.flexibleQueriesProcess;

import java.sql.SQLException;

import com.octest.beans.OracleConnection;

import slib.utils.ex.SLIB_Ex_Critic;

public class TestToBooleanCondition {

	public static void main(String[] args) throws SLIB_Ex_Critic, SQLException {
		// TODO Auto-generated method stub
		OracleConnection orc = new OracleConnection();
		orc.setHostname("localhost");
		orc.setUserName("user1");
		orc.setUserPassword("user1");
		orc.setPortNumber("1521");
		orc.setSid("orcl");
		orc.ConnectionCheckup();

		ToBooleanCondition toBoo = new ToBooleanCondition("(release_period='new')>=0.3", "movie", orc.getCnn());

		System.out.println(toBoo.getBooleanCondition());
	}

}
