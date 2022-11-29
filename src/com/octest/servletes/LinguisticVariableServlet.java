package com.octest.servletes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.octest.beans.OracleConnection;

/**
 * Servlet implementation class DatabaseTransaction
 */
@WebServlet("/LinguisticVariableServlet")
public class LinguisticVariableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LinguisticVariableServlet() {
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
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String output_message = null;

		OracleConnection orc = (OracleConnection) session.getAttribute("oracleconnection");
		switch (action) {
		case "add":
			String lvname = request.getParameter("lvname");
			int lvmaxvalue = Integer.parseInt(request.getParameter("lvmaxvalue"));
			try {
				Connection cn = orc.getCnn();
				PreparedStatement ps = cn.prepareStatement(
						"INSERT INTO linguistic_variable_table(linguistic_variable_name,username,MAXIMUM_VALUE) VALUES(?,?,?)");
				ps.setString(1, lvname);
				ps.setString(2, orc.getUserName());
				ps.setInt(3, lvmaxvalue);
				ps.executeUpdate();
				output_message = "linguistic variable successful added";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// System.out.println("error " + e.getMessage());
				output_message = e.getMessage();

			}

			break;
		case "delete":
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				Connection cn = orc.getCnn();
				PreparedStatement ps = cn
						.prepareStatement("DELETE FROM linguistic_variable_table WHERE linguistic_variable_id=?");
				ps.setInt(1, id);
				ps.executeUpdate();
				output_message = "linguistic variable successful deleted";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// System.out.println("error " + e.getMessage());
				output_message = e.getMessage();
			}
			break;
		case "update":
			// System.out.println(request.getParameter("id").replaceAll("\\s", ""));
			int idd = Integer.parseInt(request.getParameter("id").replaceAll("\\s", ""));
			String lvnamee = request.getParameter("lvname").replaceAll("\\s", "");
			int lvmaxvaluee = Integer.parseInt(request.getParameter("lvmaxvalue").replaceAll("\\s", ""));
			try {
				Connection cn = orc.getCnn();
				PreparedStatement ps = cn.prepareStatement(
						"UPDATE linguistic_variable_table SET linguistic_variable_name=? , MAXIMUM_VALUE=? WHERE linguistic_variable_id=?");
				ps.setString(1, lvnamee);
				ps.setInt(2, lvmaxvaluee);
				ps.setInt(3, idd);
				ps.executeUpdate();
				output_message = "linguistic variable successful updated";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// System.out.println("error " + e.getMessage());
				output_message = e.getMessage();
			}
			break;
		default:
			System.out.println("no match");
		}
		response.getWriter().write(output_message);
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
