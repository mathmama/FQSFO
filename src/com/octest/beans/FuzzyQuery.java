package com.octest.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FuzzyQuery {
	Connection con;

	public FuzzyQuery(Connection con) {
		super();
		this.con = con;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public JSONArray getReponce(String FQuery) throws SQLException {

		JSONArray FResponse = new JSONArray();
		PreparedStatement ps = con.prepareStatement(FQuery);
		// ps.setString(1, empID);
		ResultSet rs = ps.executeQuery();
		System.out.println("got response....");

		// JSONArray json = new JSONArray();
		ResultSetMetaData metadata = rs.getMetaData();
		int numColumns = metadata.getColumnCount();

		while (rs.next()) // iterate rows
		{
			JSONObject obj = new JSONObject(); // extends HashMap

			for (int i = 1; i <= numColumns; ++i) // iterate columns
			{
				String column_name = metadata.getColumnName(i);
				obj.put(column_name, rs.getObject(column_name));
				// obj.put("agedouble", rs.getObject("AGE"));
				System.out.println("rs.getObject('" + column_name + "')........." + rs.getObject(column_name));
			}
			FResponse.add(obj);
			System.out.println("Added JSON object to JSON Array..");

		}

		return FResponse;

	}

}
