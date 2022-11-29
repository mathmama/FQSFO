package com.octest.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.openrdf.query.algebra.Compare;

import com.slib.sml.SMComputationOWL;

import slib.utils.ex.SLIB_Ex_Critic;
import slib.utils.ex.SLIB_Exception;

public class SMComputation {
	String TableName;
	String ColumnName;
	String OntologyType;
	int OntologyRef;
	int SemanticMeasure;
	String Evalvalue;
	double Threshold;
	String CndOperator;
	Connection cn;

	public SMComputation(String tableName, String columnName, String evalvalue, double threshold, String operator,
			String ontologyType, int ontologyRef, int semanticMeasure, Connection cn) {
		super();
		TableName = tableName;
		ColumnName = columnName;
		Evalvalue = evalvalue;
		Threshold = threshold;
		CndOperator = operator;
		this.cn = cn;
		OntologyType = ontologyType;
		OntologyRef = ontologyRef;
		SemanticMeasure = semanticMeasure;
	}

	public String ReturnSemanticValues() throws SQLException, SLIB_Ex_Critic {
		String InList = "";
		String url_owl;
		double sm_degree;
		Compare cc = new Compare();

		switch (this.OntologyType) {
		case "owl":
			// System.out.println("one");
			// get informations from owl_ontology ;
			PreparedStatement pss = cn.prepareStatement("SELECT  URL  FROM owl_ontology WHERE \"OWL_ID\"= ? ");
			pss.setInt(1, this.OntologyRef);
			ResultSet rss = pss.executeQuery();
			rss.next();
			url_owl = rss.getString(1);

			System.out.println("URL: " + url_owl);
			/*** switch measure type ***/
			SMComputationOWL sm = new SMComputationOWL(url_owl, "SIM_PAIRWISE");
			try {
				sm.initialise();
			} catch (SLIB_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("############## oui ######################");
			PreparedStatement ps2 = cn.prepareStatement(
					"SELECT DISTINCT \"" + this.ColumnName.toUpperCase() + "\" from " + this.TableName);
			// PreparedStatement ps2 = cn.prepareStatement("SELECT DISTINCT \"?\" from ?");
			// ps2.setString(1, column_name);
			// ps2.setString(2, table_name);

			ResultSet rs3 = ps2.executeQuery();
			while (rs3.next()) {
				// String inst=rs.getString(1);
				/*
				 * System.out.println("Sim : " + moviegenre + "/" + rs.getNString(1) + " :" +
				 * sm.getsimilarity_degree(moviegenre, rs.getNString(1)));
				 */
				sm_degree = sm.getsimilarity_degree(Evalvalue, rs3.getString(1));
				if (Operator.parseOperator(CndOperator).apply(sm_degree, Threshold))
					// if (sm_degree >= Threshold)

					InList = "'" + rs3.getString(1) + "'," + InList;

			}
			if ((InList != null) && (InList.length() > 0))
				// to delete last ' symbole in InList string
				InList = InList.substring(0, InList.length() - 1);
			// System.out.println(InList);

			break;
		case "rdf":
			System.out.println("two");
			break;
		case "wordnet":
			System.out.println("three");
			break;
		case "yago":
			System.out.println("three");
			break;
		case "mesh":
			System.out.println("three");
			break;
		case "go":
			System.out.println("three");
			break;
		case "snomed-ct":
			System.out.println("three");
			break;
		default:
			System.out.println("no match");
		}
		return InList;

	}

}
