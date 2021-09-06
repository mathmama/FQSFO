package com.octest.beans;

import java.util.NoSuchElementException;

public enum Operator {
	LESS("<") {
		@Override
		public boolean apply(double left, double right) {
			// TODO Auto-generated method stub
			return left < right;
		}
	},
	LESSOREQUAL("<=") {
		@Override
		public boolean apply(double left, double right) {
			// TODO Auto-generated method stub
			return left <= right;
		}
	},
	EQUAL("=") {
		@Override
		public boolean apply(double left, double right) {
			return left == right;
		}
	},
	NOTEQUAL("!=") {
		@Override
		public boolean apply(double left, double right) {
			return left != right;
		}
	},
	GREATER(">") {
		@Override
		public boolean apply(double left, double right) {
			return left > right;
		}
	},
	GREATEROREQUAL(">=") {
		@Override
		public boolean apply(double left, double right) {
			return left >= right;
		}
	};

	private final String operator;

	private Operator(String operator) {
		this.operator = operator;
	}

	public static Operator parseOperator(String operator) {
		for (Operator op : values()) {
			if (op.operator.equals(operator))
				return op;
		}
		throw new NoSuchElementException(String.format("Unknown operator [%s]", operator));
	}

	public abstract boolean apply(double left, double right);

}
