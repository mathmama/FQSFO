package com.octest.beans;

import java.sql.SQLException;

public class SQLftoSQL {
	String SQLf;

	public String getSQLf() {
		return SQLf;
	}

	public void setSQLf(String sQLf) {
		SQLf = sQLf;
	}

	public String getSQL() {
		return SQL;
	}

	public void setSQL(String sQL) {
		SQL = sQL;
	}

	String SQL;

	public void getFrom() {
		String[] tables = SQLf.split("FROM");
	}

	public static void main(String[] args) throws SQLException {
		String SQLf = "select * FROM emp WHERE (age='young')>=0.5 AND (salary='small')> 0.2 groupe by name";
		String SQLf2 = SQLf.replace(" is ", ".");
		String SQLf3 = SQLf2.replace(" thb ", "\">");
		String SQLf4 = SQLf3.replace(" thl ", "\"<");

		System.out.println(SQLf4);

		// String[] res = SQLf4.split("FROM");

		String[] res2 = SQLf4.split("WHERE");
		/*** for get tables ****/

		String[] tables1 = res2[0].split(",");
		String[] tables = tables1[0].split("FROM");
		for (int i = 0; i < tables.length; i++) {
			System.out.println("table " + tables[i]);
		}

		/*** for get conditions *******/

		String[] conditions = res2[1].split("AND");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < conditions.length; i++) {
			System.out.println(conditions[i]);
			conditions[i] = "\"" + conditions[i];
			conditions[i] = conditions[i].replaceAll("\\s", "");
			sb.append(conditions[i]);
			sb.append(" AND ");

		}

		String str = sb.toString();
		System.out.println(res2[0] + " where " + str);

		String aa = "select * FROM emp WHERE (age='young')>=0.5 AND (salary='small')> 0.2";
		int startIndext = aa.indexOf("where") + 5;
		int endIndex = aa.indexOf("group");
		if (endIndex < 0)
			endIndex = aa.indexOf("order");
		if (endIndex < 0)
			endIndex = aa.length();
		String whereCondition = aa.substring(startIndext, endIndex);
		System.out.println("whereCondition: " + whereCondition);

	}
}
