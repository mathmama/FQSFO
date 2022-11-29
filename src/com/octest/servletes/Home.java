package com.octest.servletes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.octest.beans.ConnectionProvider;
import com.octest.beans.LoginDao;
import com.octest.beans.OracleConnection;

/**
 * Servlet implementation class Acceuil
 */
@WebServlet("/Acceuil")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
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
		/*
		 * PrintWriter out = response.getWriter(); out.println("Hello World !!");
		 */
		/*
		 * String username = request.getParameter("username");
		 * request.setAttribute("username", username);
		 */
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// System.out.println(request.getParameter("username"));
		this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// System.out.println(request.getParameter("username"));
		/*
		 * LoginDao logindao = new LoginDao(); logindao.validate(request);
		 * request.setAttribute("status",logindao.isStatus());
		 */
		// System.out.println("ouiiiii");
		if (request.getParameter("signin") != null) {
			// System.out.println("Sign in ckliked!!");
			LoginDao logindao = new LoginDao();
			logindao.validate(request);
			// System.out.println(logindao.getUser().getEmail());
			// request.setAttribute("user",logindao.getUser());
			HttpSession session = request.getSession();
			session.setAttribute("User", logindao.getUser());
			session.setAttribute("UserEmail", logindao.getUser().getEmail());

		}
		// Cookie cookie = new Cookie("User", logindao.getUser().getEmail());
		// request.setAttribute("HostName", request.getParameter("HostName"));
		if (request.getParameter("connectiondb") != null) {
			String DbmsVersion = request.getParameter("dbmsVersion");
			if (DbmsVersion.equals("oracle")) {
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
				session.setAttribute("oracleurl", orc.getConnectionUrl());
				session.setAttribute("username", orc.getUserName());
				session.setAttribute("password", orc.getUserPassword());
				session.setAttribute("oracleconnection", orc);
				// System.out.println(orc.getUserName());
				/*
				 * if (cn != null) { HttpSession session = request.getSession();
				 * session.setAttribute("conn", cn); System.out.println("True"); } else { //
				 * response.getWriter().write("False"); // System.out.println("SQLerrors:" +
				 * orc.getError()); HttpSession session = request.getSession();
				 * session.setAttribute("cnerrors", orc.getError());
				 * 
				 * }
				 */
			}

			/** ici ******/

		}

		if (request.getParameter("connectiondburl") != null) {
			if (request.getParameter("idcnx") != null) {
				int idcnx = Integer.parseInt(request.getParameter("idcnx"));
				try {

					Connection conn = ConnectionProvider.getCon();
					conn.setAutoCommit(false);
					Statement stat = conn.createStatement();
					String query = "select CONNECTIONSTRING  from CONNECTIONS where ID=" + idcnx + ";";
					ResultSet rs = stat.executeQuery(query);
					String cnxurl = rs.getString("CONNECTIONSTRING");
					String[] cnx = cnxurl.split(":");
					String HostName = cnx[0];
					String ConnexionUserName = cnx[1];
					String ConnexionPassword = cnx[2];
					String PortNumber = cnx[3];
					String Sid = cnx[4];

					OracleConnection orc = new OracleConnection();
					orc.setHostname(HostName);
					orc.setUserName(ConnexionUserName);
					orc.setUserPassword(ConnexionPassword);
					orc.setPortNumber(PortNumber);
					orc.setSid(Sid);

					orc.ConnectionCheckup();
					// System.out.println("SQLerrors:" + orc.getError());
					Connection cn = orc.getCnn();
					HttpSession session = request.getSession();
					session.setAttribute("conn", cn);
					session.setAttribute("cnerrors", orc.getError());
					session.setAttribute("oracleurl", orc.getConnectionUrl());
					session.setAttribute("username", orc.getUserName());
					session.setAttribute("password", orc.getUserPassword());
					session.setAttribute("oracleconnection", orc);

				} catch (Exception e) {
					System.out.println(e.getClass().getName() + ": " + e.getMessage());
				}

			}
		}

		if (request.getParameter("disconnecteddb") != null) {
			HttpSession session = request.getSession();
			session.removeAttribute("conn");
			session.removeAttribute("cnerrors");
			session.removeAttribute("oracleconnection");
			session.removeAttribute("nameuseroracle");
			session.removeAttribute("userpasswordoracle");
			System.out.println("disc");
			// response.sendRedirect("home");
		}

		if (request.getParameter("sqlfquery") != null) {
			String sqlfquery = request.getParameter("sqlfquery");
			System.out.println(sqlfquery);
			request.setAttribute("sqlfquery", sqlfquery);
			// response.getWriter().write("yes");
			// this.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request,
			// response);
		}
		request.setAttribute("HostName", request.getParameter("HostName"));
		request.setAttribute("ConnexionUserName", request.getParameter("ConnexionUserName"));
		request.setAttribute("ConnexionPassword", request.getParameter("ConnexionPassword"));
		request.setAttribute("PortNumber", request.getParameter("PortNumber"));
		request.setAttribute("Sid", request.getParameter("Sid"));

		this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
		// this.getServletContext().getRequestDispatcher("/WEB-INF/Home.jsp").forward(request,
		// response);

		// doGet(request, response);
	}

}
