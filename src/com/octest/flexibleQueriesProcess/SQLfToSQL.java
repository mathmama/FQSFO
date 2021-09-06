package com.octest.flexibleQueriesProcess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import net.sf.jsqlparser.JSQLParserException;
import slib.utils.ex.SLIB_Ex_Critic;

public class SQLfToSQL {
	String SQLfQuery;
	Connection cn;

	public SQLfToSQL(String sQlfQuery, Connection cn) {
		super();
		SQLfQuery = sQlfQuery;
		this.cn = cn;
	}

	public String SQLftoSQL() throws SLIB_Ex_Critic, SQLException, JSQLParserException {

		String SQLQuery = this.SQLfQuery;
		String booleancondition;

		SQLfParser parser = new SQLfParser(SQLfQuery);
		// get columns List
		List<String> ColumnsList = parser.getColumnsList();
		// get tables List */
		List<String> TablesList = parser.getTableList();
		// get where conditions List //
		String[] WhereCondsList = parser.getWhereCondsList();
		if (WhereCondsList == null) {
			return SQLQuery;
		} else {
			/* translate each condition into a boolean condition. */
			for (String condition : WhereCondsList) {
				// we are juste considere mono query with one table */
				if (condition.contains("(")) {

					ToBooleanCondition toBoo = new ToBooleanCondition(condition, TablesList.get(0).toLowerCase(),
							this.cn);

					booleancondition = toBoo.getBooleanCondition();
					condition = condition.replaceAll(" ", "");
					System.out.println(condition + ">>>" + booleancondition);
					SQLQuery = SQLQuery.replace(condition, booleancondition);

					/* if the are a fuzzy condition you must use fuzzy view INSTEAD OF table */
					if (toBoo.isIsfuzzy())
						SQLQuery = SQLQuery.replace(TablesList.get(0), "f" + TablesList.get(0));
				}

			}
		}

		return SQLQuery;
	}

}
