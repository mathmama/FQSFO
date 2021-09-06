package com.octest.labo;

import com.octest.beans.Operator;
import com.slib.sml.SMComputationOWL;

import slib.utils.ex.SLIB_Exception;

//0.5578858913022597
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// String _ontoFile = "C:/Users/MATHMAMA/Downloads/WikiMovieOld.rdf";
		long start = System.currentTimeMillis();

		String _ontoFile = "C:\\Users\\MATHMAMA\\Downloads\\DatasetOntology\\movieontology.owl";
		SMComputationOWL sm = new SMComputationOWL(_ontoFile, "");

		try {
			sm.initialise();
			long end = System.currentTimeMillis();
			// System.out.println(sm.getsimilarity_degree("Reboot_filmsý",
			// "Indian_documentary_films"));
			System.out.println(sm.getsimilarity_degree("Brute_Action", "War"));

			System.out.println("Time : " + (end - start) + " MS");
		} catch (SLIB_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(Operator.parseOperator("==").apply(3, 2));
	}

}
