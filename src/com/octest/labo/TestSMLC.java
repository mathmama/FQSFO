package com.octest.labo;

import com.slib.sml.SMComputationOWL;

import slib.utils.ex.SLIB_Exception;

public class TestSMLC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// String _ontoFile = "C:/Users/MATHMAMA/Downloads/Travel-Domain.owl";
		// String _ontoFile =
		// "C:\\Users\\MATHMAMA\\Downloads\\DatasetOntology\\movieontology.owl";
		String _ontoFile = "C:\\Users\\MATHMAMA\\Desktop\\My Thesis\\Final Thesis V2\\Ontology dataset\\MovieOntology.owl";
		SMComputationOWL sm = new SMComputationOWL(_ontoFile, "SIM_PAIRWISE");
		try {
			sm.initialise();
			System.out.println(sm.getsimilarity_degree("Action", "Zombie")); // 0.6131471927654586
		} catch (SLIB_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
