package com.octest.servletes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.beans.User;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//User user = new User();
		User user = new User();
		user.setEmail(request.getParameter("useremail"));
		
		user.setPassword(request.getParameter("userpassword"));
		/*
		user.setFirst_Name(request.getParameter("userfirstname"));
		user.setLast_Name(request.getParameter("userlastname"));*/
		request.setAttribute("user",user);
		this.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		this.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
	}

}
