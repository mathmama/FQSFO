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
 * Servlet implementation class LinguisticValueServlet
 */
@WebServlet("/LinguisticValueServlet")
public class LinguisticValueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LinguisticValueServlet() {
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
		// System.out.println("yess");
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String output_message = null;

		OracleConnection orc = (OracleConnection) session.getAttribute("oracleconnection");
		switch (action) {
		case "add":
			int lvid = Integer.parseInt(request.getParameter("lvid"));
			String lvvname = request.getParameter("lvvname");
			String lvvmtype = request.getParameter("lvvmtype");
			int alpha = Integer.parseInt(request.getParameter("alpha"));
			int beta = Integer.parseInt(request.getParameter("beta"));
			int gamma = Integer.parseInt(request.getParameter("gamma"));
			int lambda = Integer.parseInt(request.getParameter("lambda"));

			try {
				Connection cn = orc.getCnn();
				PreparedStatement ps = cn.prepareStatement(
						"INSERT INTO linguistic_value_table(LINGUISTIC_VARIABLE_ID,LINGUISTIC_VALUE,MEMBERSHIP_FUNCTION_TYPE,ALPHA,BETA,GAMMA,LAMBDA) VALUES(?,?,?,?,?,?,?)");
				ps.setInt(1, lvid);
				ps.setString(2, lvvname);
				ps.setString(3, lvvmtype);
				ps.setInt(4, alpha);
				ps.setInt(5, beta);
				ps.setInt(6, gamma);
				ps.setInt(7, lambda);
				ps.executeUpdate();
				output_message = "linguistic value successful added";
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
						.prepareStatement("DELETE FROM linguistic_value_table WHERE linguistic_value_id=?");
				ps.setInt(1, id);
				ps.executeUpdate();
				output_message = "linguistic value successful deleted";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
