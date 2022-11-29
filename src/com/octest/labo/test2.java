package com.octest.labo;

import com.slib.sml.SMComputationOWL;

import slib.utils.ex.SLIB_Exception;

public class test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String _ontoFile = "C:/Users/MATHMAMA/Downloads/Travel-Domain.owl";
		SMComputationOWL sm = new SMComputationOWL(_ontoFile, "SIM_PAIRWISE");
		try {
			sm.initialise();
			System.out.println(sm.getsimilarity_degree("Country", "City"));
		} catch (SLIB_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
