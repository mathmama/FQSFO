package com.octest.flexibleQueriesProcess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.TablesNamesFinder;
import slib.utils.ex.SLIB_Ex_Critic;

public class SQLfToSQL2OLD {
	String SQLfQuery;
	Connection cn;

	public SQLfToSQL2OLD(String sQlfQuery, Connection cn) {
		super();
		SQLfQuery = sQlfQuery;
		this.cn = cn;
	}

	public String SQLftoSQL() throws SLIB_Ex_Critic, SQLException, JSQLParserException {

		String SQLQuery = this.SQLfQuery;
		String booleancondition;

		Statement statement = CCJSqlParserUtil.parse(SQLfQuery);
		if (statement instanceof Select) {
			// "SELECT mm,rr FROM table1 where age='young' and genre='Action' "
			Select selectStatement = (Select) statement;
			// get tables names:
			TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
			List<String> tableList = tablesNamesFinder.getTableList(selectStatement);
			/*
			 * for (String item : tableList) { System.out.println("Tables: " + item); }
			 */

			// where condtions :
			PlainSelect ps = (PlainSelect) selectStatement.getSelectBody();
			Expression expr = ps.getWhere();
			if (expr == null) {
				return SQLQuery;
			}
			String wherecon = expr.toString();
			/* Split where conditions String with 'OR' 'AND' 'and' 'or' operators */
			String[] ConditionsList = wherecon.split(" AND | OR | and | or ");

			/* translate each condition into a boolean condition. */
			for (String condition : ConditionsList) {
				// we are juste considere mono query with one table */
				if (condition.contains("(")) {

					ToBooleanCondition toBoo = new ToBooleanCondition(condition, tableList.get(0).toLowerCase(),
							this.cn);

					booleancondition = toBoo.getBooleanCondition();
					condition = condition.replaceAll(" ", "");
					System.out.println(condition + ">>>" + booleancondition);
					SQLQuery = SQLQuery.replace(condition, booleancondition);

					/* if the are a fuzzy condition you must use fuzzy view INSTEAD OF table */
					if (toBoo.isIsfuzzy())
						SQLQuery = SQLQuery.replace(tableList.get(0), "f" + tableList.get(0));
				}

			}
		}

		return SQLQuery;
	}

}
