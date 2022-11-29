package com.octest.servletes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.octest.beans.OracleConnection;

/**
 * Servlet implementation class GetTableColumns
 */
@WebServlet("/GetTableColumns")
public class GetTableColumns extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetTableColumns() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String selected_table = request.getParameter("selected_table");
		String data_type = request.getParameter("data_type");

		System.out.println("enterred servlet..." + selected_table);
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			HttpSession session = request.getSession();
			OracleConnection orc = (OracleConnection) session.getAttribute("oracleconnection");
			Connection con = orc.getCnn();
			PreparedStatement ps = con
					.prepareStatement("SELECT column_name FROM USER_TAB_COLUMNS WHERE table_name = ? AND DATA_TYPE=? ");
			ps.setString(1, selected_table);
			ps.setString(2, data_type);
			ResultSet rs = ps.executeQuery();
			System.out.println("got response....");

			JSONArray json = new JSONArray();
			ResultSetMetaData metadata = rs.getMetaData();
			int numColumns = metadata.getColumnCount();

			while (rs.next()) // iterate rows
			{
				JSONObject obj = new JSONObject(); // extends HashMap
				for (int i = 1; i <= numColumns; ++i) // iterate columns
				{
					String column_name = metadata.getColumnName(i);
					obj.put(column_name, rs.getObject(column_name));
					System.out.println("rs.getObject('" + column_name + "')........." + rs.getObject(column_name));
				}
				json.add(obj);
				System.out.println("Added JSON object to JSON Array..");

			}

			response.setContentType("application/json;charset=UTF-8");
			String jsonString = json.toJSONString();
			System.out.println("jsonString:" + jsonString);
			response.getWriter().write(jsonString);

			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("Status", "Success");
			jsonMap.put("Rows", 100);

			// con.close();
		} catch (Exception e) {
			e.printStackTrace();
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
