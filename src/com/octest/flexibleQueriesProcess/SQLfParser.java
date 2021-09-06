package com.octest.flexibleQueriesProcess;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.util.TablesNamesFinder;

public class SQLfParser {

	String SQLfQuery;
	Statement statement;
	List<String> ColumnsList = new ArrayList<>();
	List<String> TablesList = new ArrayList<>();
	String[] WhereCondsList;

	public SQLfParser(String sQLfQuery) throws JSQLParserException {
		super();
		SQLfQuery = sQLfQuery;
		statement = CCJSqlParserUtil.parse(SQLfQuery);
	}

	public List<String> getTableList() {
		if (statement instanceof Select) {
			TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
			TablesList = tablesNamesFinder.getTableList(statement);
		}
		return TablesList;
	}

	public List<String> getColumnsList() throws JSQLParserException {

		List<SelectItem> selectCols = ((PlainSelect) ((Select) this.statement).getSelectBody()).getSelectItems();
		for (SelectItem selectItem : selectCols)
			ColumnsList.add(selectItem.toString());
		return ColumnsList;
	}

	public String[] getWhereCondsList() {
		PlainSelect ps = (PlainSelect) ((Select) statement).getSelectBody();
		Expression expr = ps.getWhere();
		if (expr == null) {
			return null;
		} else {
			String wherecon = expr.toString();
			/* Split where conditions String with 'OR' 'AND' 'and' 'or' operators */
			WhereCondsList = wherecon.split(" AND | OR | and | or ");
			return WhereCondsList;
		}
	}

	public static void main(String[] args) throws JSQLParserException {
		SQLfParser p = new SQLfParser("SELECT col1,col2,col3 FROM movie,emp where (age='young')");

		for (String col : p.getColumnsList())
			System.out.println(col);
		for (String tbl : p.getTableList())
			System.out.println(tbl);
		/*
		 * for (String cond : p.getWhereCondsList()) System.out.println(cond);
		 */
		// System.out.print(p.getWhereCondsList().toString());
		for (String cnd : p.getWhereCondsList())
			System.out.println(cnd);

		String cnd = "(age = 'young') < 1";
		// String segments[] = cnd.split(")");
		System.out.println("++++" + StringUtils.substringBetween(cnd, ") ", " ") + "++++");

	}

}
