package com.octest.forms;

import javax.servlet.http.HttpServletRequest;

public class ConnectionForm {

	private String resultat;

	public void verifierIdentifiants(HttpServletRequest request) {
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		if(pwd==email){
			resultat = "You are successful connected !";
		}
		else {
			resultat = "connection denied !!";
		}
	}
	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
}
