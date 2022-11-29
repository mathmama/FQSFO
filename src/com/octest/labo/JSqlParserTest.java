package com.octest.labo;

import java.util.Arrays;
import java.util.List;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.operators.relational.ComparisonOperator;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.TablesNamesFinder;

public class JSqlParserTest {

	public static void main(String[] args) throws JSQLParserException {
		// TODO Auto-generated method stub

		String SQLfquery = "SELECT * FROM movie WHERE (release_period='new')>=0.2 AND (genre='Action')";

		Statement statement = CCJSqlParserUtil.parse(SQLfquery);

		// "SELECT mm,rr FROM table1 where age='young' and genre='Action' "
		Select selectStatement = (Select) statement;
		// get tables names:
		TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();

		List<String> tableList = tablesNamesFinder.getTableList(selectStatement);
		for (String item : tableList) {
			System.out.println("Tables: " + item);
		}

		// where condtions :
		PlainSelect ps = (PlainSelect) selectStatement.getSelectBody();
		Expression expr0 = ps.getWhere();
		String wherecon = expr0.toString();

		System.out.println("######## Split where conditions ############");
		// System.out.println(wherecon);

		String[] ConditionsList = wherecon.split(" AND | OR | and | or ");
		System.out.println(Arrays.toString(ConditionsList));

		System.out.println("########End of Split where conditions ############");

		/*****************************************************************/
		for (String condition : ConditionsList) {
			// if(condition.contains("(")) {}

			System.out.println("############# " + condition + " ################");
			Expression expr = CCJSqlParserUtil.parseCondExpression(condition);

			expr.accept(new ExpressionVisitorAdapter() {

				@Override
				protected void visitBinaryExpression(BinaryExpression expr) {

					if (expr instanceof ComparisonOperator) {

						System.out.println("left=" + expr.getLeftExpression() + "  op:" + expr.getStringExpression()
								+ "  right=" + expr.getRightExpression());
					}

					super.visitBinaryExpression(expr);
				}
			});
		}

		/*
		 * System.out.println("###########################"); expr0.accept(new
		 * ExpressionVisitorAdapter() {
		 * 
		 * @Override public void visit(AndExpression expr) { if
		 * (expr.getLeftExpression() instanceof AndExpression) {
		 * expr.getLeftExpression().accept(this); } else if ((expr.getLeftExpression()
		 * instanceof EqualsTo)) { expr.getLeftExpression().accept(this);
		 * System.out.println(expr.getLeftExpression()); }
		 * expr.getRightExpression().accept(this);
		 * System.out.println(expr.getRightExpression()); }
		 * 
		 * @Override public void visit(EqualsTo expr) {
		 * System.out.println(expr.getLeftExpression());
		 * System.out.println(expr.getStringExpression());
		 * System.out.println(expr.getRightExpression()); } });
		 * 
		 * // System.out.println(wherecon.getLeftExpression())
		 * 
		 * // get select columns :u /*
		 * System.out.println(ps.getSelectItems().get(0).toString());
		 * System.out.println(ps.getSelectItems().get(1).toString());
		 */
	}

}
