package com.octest.labo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.octest.beans.OracleConnection;
import com.slib.sml.SMComputationOWL;

import slib.utils.ex.SLIB_Exception;

public class SemanticQueryExemple {

	public static void main(String[] args) {

		// semantic preparation :
		String _ontoFile = "C:\\Users\\MATHMAMA\\Downloads\\DatasetOntology\\movieontology.owl";
		SMComputationOWL sm = new SMComputationOWL(_ontoFile, "SIM_PAIRWISE");

		// System.out.println(sm.getsimilarity_degree("Reboot_filmsý",
		// "Indian_documentary_films"));

		// TODO Auto-generated method stub
		int threshold = (int) 0.5;
		String SemanticAttribut = "genre";
		String moviegenre = "Info-TV";
		String InList = "";

		try {
			try {
				sm.initialise();
			} catch (SLIB_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			OracleConnection orc = new OracleConnection();
			orc.setHostname("localhost");
			orc.setUserName("user1");
			orc.setUserPassword("user1");
			orc.setPortNumber("1521");
			orc.setSid("orcl");
			orc.ConnectionCheckup();

			// System.out.println("yes" + orc.getCnn().getClientInfo());
			Connection con = orc.getCnn();
			Statement stmt;
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select distinct " + SemanticAttribut + " from movie");

			while (rs.next()) {
				// String inst=rs.getString(1);
				/*
				 * System.out.println("Sim : " + moviegenre + "/" + rs.getNString(1) + " :" +
				 * sm.getsimilarity_degree(moviegenre, rs.getNString(1)));
				 */
				if (sm.getsimilarity_degree(moviegenre, rs.getNString(1)) > threshold) {
					System.out.println("Sim : " + moviegenre + "/" + rs.getNString(1) + " :"
							+ sm.getsimilarity_degree(moviegenre, rs.getNString(1)));

					InList = "'" + rs.getString(1) + "'," + InList;

				}

			}
			InList = InList.substring(0, InList.length() - 1);
			System.out.println(InList);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("got response....");

	}

}
