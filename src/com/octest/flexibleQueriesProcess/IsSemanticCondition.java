package com.octest.flexibleQueriesProcess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IsSemanticCondition {
	String TableName;
	String AttributName;
	String OntologyType;
	Connection cn;

	public String getTableName() {
		return TableName;
	}

	public void setTableName(String tableName) {
		TableName = tableName;
	}

	public String getAttributName() {
		return AttributName;
	}

	public void setAttributName(String attributName) {
		AttributName = attributName;
	}

	public String getOntologyType() {
		return OntologyType;
	}

	public void setOntologyType(String ontologyType) {
		OntologyType = ontologyType;
	}

	public Connection getCn() {
		return cn;
	}

	public void setCn(Connection cn) {
		this.cn = cn;
	}

	public int getOntologyRef() {
		return OntologyRef;
	}

	public void setOntologyRef(int ontologyRef) {
		OntologyRef = ontologyRef;
	}

	public int getSemanticMeasure() {
		return SemanticMeasure;
	}

	public void setSemanticMeasure(int semanticMeasure) {
		SemanticMeasure = semanticMeasure;
	}

	int OntologyRef;
	int SemanticMeasure;

	public IsSemanticCondition(String tableName, String attributName, Connection cn) {
		super();
		TableName = tableName;
		AttributName = attributName;
		this.cn = cn;
	}

	public boolean IsSemanticCondition() throws SQLException {
		PreparedStatement ps = cn.prepareStatement(
				"SELECT  \"ontology_type\",\"ontology_ref\",\"semantic_measure\" FROM semantic_predicate WHERE \"table_name\" = ? AND \"column_name\"= ? ");
		ps.setString(1, this.TableName);
		ps.setString(2, this.AttributName);
		ResultSet rs = ps.executeQuery();
		if (rs.next() == false) {
			return false;
		} else {
			// rs.next();
			this.OntologyType = rs.getString(1);
			this.OntologyRef = rs.getInt(2);
			this.SemanticMeasure = rs.getInt(3);
			return true;
		}

	}

}
