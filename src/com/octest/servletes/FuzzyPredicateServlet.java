package com.octest.servletes;

import java.io.IOException;
import java.sql.CallableStatement;
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
 * Servlet implementation class FuzzyPredicateServlet
 */
@WebServlet("/FuzzyPredicateServlet")
public class FuzzyPredicateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FuzzyPredicateServlet() {
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

			String tablename = request.getParameter("tablename");
			String columnname = request.getParameter("columnname");
			int lvid = Integer.parseInt(request.getParameter("lvid"));
			String status = request.getParameter("status");
			System.out.println(tablename + ':' + columnname + ':' + lvid + ':' + status);

			try {
				Connection cn = orc.getCnn();
				PreparedStatement ps = cn.prepareStatement(
						"INSERT INTO fuzzy_predicate_table(TABLE_NAME,COLUMN_NAME,LINGUISTIC_VARIABLE_ID,STATUS) VALUES(?,?,?,?)");
				ps.setString(1, tablename);
				ps.setString(2, columnname);
				ps.setInt(3, lvid);
				ps.setString(4, status);

				ps.executeUpdate();
				output_message = "Fuzzy predicate successful added";

				CallableStatement pstmt = cn.prepareCall("begin create_view(?,?); TO_FUZZY_TABLE(?);end;");
				pstmt.setString(1, columnname);
				pstmt.setString(2, tablename);
				pstmt.setString(3, tablename);

				pstmt.execute();
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
				PreparedStatement ps = cn.prepareStatement("DELETE FROM fuzzy_predicate_table WHERE POSSID=?");
				ps.setInt(1, id);
				ps.executeUpdate();
				output_message = "Fuzzy predicate successful deleted";
			} catch (SQLException e) {
				// System.out.println("error " + e.getMessage());
				output_message = e.getMessage();
			}

			break;
		case "update":
			System.out.println("three");
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
