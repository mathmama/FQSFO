package com.octest.flexibleQueriesProcess;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

import com.octest.beans.SMComputation;

import slib.utils.ex.SLIB_Ex_Critic;

public class ToBooleanCondition {

	String condition;
	// String booleancondition;
	String TableName;
	Connection cn;
	boolean isfuzzy = false;

	public ToBooleanCondition(String condition, String tableName, Connection cn) {
		super();
		this.condition = condition;
		TableName = tableName;
		this.cn = cn;
	}

	public String getBooleanCondition() throws SQLException, SLIB_Ex_Critic {
		String BooleanCondition = this.condition;
		String[] res;
		String AttributName = null;
		String EvaluateValue = null;
		double Threshold = 0;
		String operator = ">";

		res = this.condition.split("=|!=|>=|<=|>|<");
		// System.out.println("res " + res[0] + " " + res[1] + " " + res[2]);
		if (StringUtils.substringBetween(this.condition, ") ", " ") != null)
			operator = StringUtils.substringBetween(this.condition, ") ", " ");

		if (res[0] != null)
			AttributName = res[0].replaceAll("['-(-\\s+]", "");
		if (res[1] != null)
			EvaluateValue = res[1].replaceAll("['-)-\\s+]", "");
		if (res.length > 2)
			// to get Threshold from arguments after delete

			Threshold = Double.parseDouble(res[2].replaceAll(" ", ""));

		IsSemanticCondition SemCond = new IsSemanticCondition(this.TableName, AttributName, this.cn);
		IsFuzzyCondition FuzzyCond = new IsFuzzyCondition(this.TableName, AttributName, this.cn);

		if (FuzzyCond.IsFuzzyCondition()) {

			System.out.println("Att " + AttributName.toLowerCase() + " Eval value " + EvaluateValue + " Ope " + operator
					+ " Thr " + Threshold);
			// boolean condition composition
			BooleanCondition = "\"" + AttributName.toLowerCase() + "." + EvaluateValue.toLowerCase() + "\"" + operator
					+ Threshold;
			// to make change of table name
			this.isfuzzy = true;
			return BooleanCondition;

		} else if (SemCond.IsSemanticCondition()) {
			// System.out.println(this.TableName + "#" + AttributName + "#" + EvaluateValue
			// + "#" + Threshold);
			SMComputation SMM = new SMComputation(this.TableName, AttributName, EvaluateValue, Threshold, operator,
					SemCond.getOntologyType(), SemCond.getOntologyRef(), SemCond.getSemanticMeasure(), this.cn);

			if (SMM.ReturnSemanticValues() == "") {
				BooleanCondition = AttributName + " IN ('')";
				return BooleanCondition;
			}
			BooleanCondition = AttributName + " IN  (''," + SMM.ReturnSemanticValues() + ")";
			return BooleanCondition;

		} else {
			throw new IllegalArgumentException("the attribute " + AttributName + " is not in the  database catalog");
		}
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public boolean isIsfuzzy() {
		return isfuzzy;
	}

	public void setIsfuzzy(boolean isfuzzy) {
		this.isfuzzy = isfuzzy;
	}

}
