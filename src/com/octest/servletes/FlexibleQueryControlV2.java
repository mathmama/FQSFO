package com.octest.servletes;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;

import com.octest.beans.OracleConnection;

/**
 * Servlet implementation class FlexibleQueryControl
 */
@WebServlet("/FlexibleQueryControlV2")
public class FlexibleQueryControlV2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FlexibleQueryControlV2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONArray fuzzy_query_resp = new JSONArray();
		JSONArray semantic_query_resp = new JSONArray();
		JSONArray flexible_query_resp = new JSONArray();
		// TODO Auto-generated method stub
		String SQLfquery = request.getParameter("sqlfquery");
		SQLfquery = SQLfquery.replaceAll(";", "");

		// System.out.println("enterred servlet..." + SQLfquery);
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			HttpSession session = request.getSession();
			OracleConnection orc = (OracleConnection) session.getAttribute("oracleconnection");
			Connection con = orc.getCnn();
			// FuzzyQuery FQ = new FuzzyQuery(con);
			// fuzzy_query_resp = FQ.getReponce(SQLfquery);

			/*****/
			// flexible_query_resp = fuzzy_query_resp;

			/***/

			/* send the response to the output */
			response.setContentType("application/json;charset=UTF-8");
			String jsonString = flexible_query_resp.toJSONString();
			System.out.println("jsonString:" + jsonString);
			response.getWriter().write(jsonString);

			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("Status", "Success");
			jsonMap.put("Rows", 100);

			// con.close();
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("######## message error: " + e.toString() + "##########" +
			// e.getMessage());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
			// response.getWriter().write(e.toString());
			// e.getMessage());

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
