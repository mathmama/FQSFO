package com.octest.labo;

import java.sql.SQLException;

import com.octest.beans.OracleConnection;
import com.octest.beans.SMComputation;
import com.octest.flexibleQueriesProcess.IsFuzzyCondition;
import com.octest.flexibleQueriesProcess.IsSemanticCondition;

import slib.utils.ex.SLIB_Ex_Critic;

public class TestSMComputation {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		OracleConnection orc = new OracleConnection();
		orc.setHostname("localhost");
		orc.setUserName("user1");
		orc.setUserPassword("user1");
		orc.setPortNumber("1521");
		orc.setSid("orcl");
		orc.ConnectionCheckup();

		long start = System.currentTimeMillis();
		IsSemanticCondition sematt = new IsSemanticCondition("movie", "genre", orc.getCnn());

		System.out.println(sematt.IsSemanticCondition());

		SMComputation SMM = new SMComputation("movie", "genre", "Brute_Action", 0.8, ">", sematt.getOntologyType(),
				sematt.getOntologyRef(), sematt.getSemanticMeasure(), orc.getCnn());
		try {
			System.out.println(SMM.ReturnSemanticValues());
			long end = System.currentTimeMillis();
			System.out.println("Time " + (end - start) + " MS");

			IsFuzzyCondition fuzzC = new IsFuzzyCondition("movie", "release_period", orc.getCnn());
			System.out.println(fuzzC.IsFuzzyCondition());
		} catch (SLIB_Ex_Critic e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
