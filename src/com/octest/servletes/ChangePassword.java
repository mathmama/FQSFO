package com.octest.servletes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.beans.ChangePasswordDao;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		this.getServletContext().getRequestDispatcher("/WEB-INF/changepassword.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//System.out.println("new pass "+request.getAttribute("password"));
		ChangePasswordDao changepassworddao = new ChangePasswordDao();
		changepassworddao.validate(request);
		int status =  changepassworddao.getStatus();
		//System.out.println("status"+changepassworddao.getStatus());
		if (status==1) {
			response.getWriter().write("your password successfully changed");
		}else  {
			response.getWriter().write("connexin problems !! .please check your connexion adapter");	
		}
		//request.setAttribute("statuschangepassword",changepassworddao.getStatus());
		//this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	}

}
