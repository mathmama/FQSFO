package com.octest.servletes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;

import com.octest.beans.OracleConnection;
import com.octest.flexibleQueriesProcess.SQLfToSQL;

/**
 * Servlet implementation class FlexibleQueryControl
 */
@WebServlet("/FlexibleQueryControl")
public class FlexibleQueryControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FlexibleQueryControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// start
		String SQLquery = "";
		String SQLfquery = request.getParameter("sqlfquery");
		SQLfquery = SQLfquery.replaceAll(";", "");

		System.out.println("SQLf Query: " + SQLfquery);
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			HttpSession session = request.getSession();
			OracleConnection orc = (OracleConnection) session.getAttribute("oracleconnection");
			Connection con = orc.getCnn();

			/*** transalte SQLf Query to SQL ********/
			// SQLfquery = "SELECT * FROM movie WHERE (release_period='new')>=0.2 AND
			// (genre='Brute_Action')>=0.2";

			SQLfToSQL Fquery = new SQLfToSQL(SQLfquery, orc.getCnn());
			System.out.println("ouiii");
			SQLquery = Fquery.SQLftoSQL();
			System.out.println("SQL Query: " + SQLquery);
			session.setAttribute("sqlquery", SQLquery);
			// out.print(SQLquery);

			PreparedStatement ps = con.prepareStatement(SQLquery);
			// ps.setString(1, empID);
			ResultSet rs = ps.executeQuery();
			System.out.println("got response....");

			JSONArray json = new JSONArray();
			ResultSetMetaData metadata = rs.getMetaData();
			int numColumns = metadata.getColumnCount();
			// System.out.println(">>>>>" + metadata.getColumnName(1));

			while (rs.next()) // iterate rows
			{
				// System.out.println(rs.getString(1));
				// JSONObject obj = new JSONObject(); // extends HashMap
				Map obj = new LinkedHashMap();

				for (int i = 1; i <= numColumns; ++i) // iterate columns
				{
					String column_name = metadata.getColumnName(i);
					// System.out.println(">>>>" + column_name);
					obj.put(column_name, rs.getObject(column_name));
					// obj.put("agedouble", rs.getObject("AGE"));
					// System.out.println("rs.getObject('" + column_name + "')........." +
					// rs.getObject(column_name));
				}
				// Added JSON object to JSON Array..
				// System.out.println("####" + obj.toString());
				json.add(obj);
				// System.out.println("Added JSON object to JSON Array..");

			}

			response.setContentType("application/json;charset=UTF-8");
			// System.out.println(">>>>" + json.get(0));
			String jsonString = json.toJSONString();
			// System.out.println("jsonString:" + jsonString);
			response.getWriter().write(jsonString);

			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("Status", "Success");
			jsonMap.put("Rows", 100);

			// con.close();

		} catch (Exception e) {
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			response.setStatus(400);
			out.print(e.getMessage());
			out.flush();
			// response.setContentType("text/plain");
			// response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
			// response.getOutputStream().write(e.getMessage().getBytes());
			System.out.println("########### " + e.getMessage() + " ##############");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
