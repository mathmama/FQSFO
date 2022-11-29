package com.octest.flexibleQueriesProcess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IsFuzzyCondition {
	String TableName;
	String AttributName;
	Connection cn;

	public IsFuzzyCondition(String tableName, String attributName, Connection cn) {
		super();
		TableName = tableName;
		AttributName = attributName;
		this.cn = cn;
	}

	public boolean IsFuzzyCondition() throws SQLException {
		PreparedStatement ps = cn
				.prepareStatement("SELECT * FROM fuzzy_predicate_table WHERE TABLE_NAME = ? AND COLUMN_NAME= ? ");
		ps.setString(1, this.TableName);
		ps.setString(2, this.AttributName);
		ResultSet rs = ps.executeQuery();
		if (rs.next() == false)
			return false;

		return true;

	}
}
