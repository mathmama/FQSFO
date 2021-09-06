package com.octest.servletes;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.octest.beans.OracleConnection;

/**
 * Servlet implementation class ConnectionDao
 */
@WebServlet("/ConnectionDao")
public class ConnectionDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnectionDao() {
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
		String DbmsVersion = request.getParameter("DbmsVersion");

		if (DbmsVersion.equals("oracle")) {
			// System.out.println(DbmsVersion);
			OracleConnection orc = new OracleConnection();
			orc.setHostname(request.getParameter("HostName"));
			orc.setUserName(request.getParameter("ConnexionUserName"));
			orc.setUserPassword(request.getParameter("ConnexionPassword"));
			orc.setPortNumber(request.getParameter("PortNumber"));
			orc.setSid(request.getParameter("Sid"));

			orc.ConnectionCheckup();
			System.out.println("SQLerrors:" + orc.getError());
			Connection cn = orc.getCnn();
			HttpSession session = request.getSession();
			session.setAttribute("conn", cn);
			session.setAttribute("cnerrors", orc.getError());
			/*
			 * if (cn != null) { HttpSession session = request.getSession();
			 * session.setAttribute("conn", cn);
			 * 
			 * } else { response.getWriter().write("False"); //
			 * System.out.println("SQLerrors:" + orc.getError()); HttpSession session =
			 * request.getSession(); session.setAttribute("cnerrors", orc.getError());
			 * 
			 * }
			 */
			System.out.println(request.getParameter("HostName"));
			request.setAttribute("HostName", request.getParameter("HostName"));
		}
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String DbmsVersion = request.getParameter("DbmsVersion");

		if (DbmsVersion.equals("oracle")) {
			// System.out.println(DbmsVersion);
			OracleConnection orc = new OracleConnection();
			orc.setHostname(request.getParameter("HostName"));
			orc.setUserName(request.getParameter("ConnexionUserName"));
			orc.setUserPassword(request.getParameter("ConnexionPassword"));
			orc.setPortNumber(request.getParameter("PortNumber"));
			orc.setSid(request.getParameter("Sid"));

			orc.ConnectionCheckup();
			System.out.println("SQLerrors:" + orc.getError());
			Connection cn = orc.getCnn();
			if (cn != null) {
				/*
				 * HttpSession session = request.getSession(); session.setAttribute("conn", cn);
				 */
				response.getWriter().write("True");
			} else {
				response.getWriter().write(orc.getError());
				// System.out.println("SQLerrors:" + orc.getError());
				/*
				 * HttpSession session = request.getSession(); session.setAttribute("cnerrors",
				 * orc.getError());
				 */

			}
		}
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request,
		// response);
		// doGet(request, response);
	}

}
