/*
 * package com.octest.beans;
 * 
 * import java.util.HashMap; import java.util.Map;
 * 
 * interface Operator { boolean compare(int a, int b);
 * 
 * // public static final Map<String, Operator> opMap = null; }
 * 
 * public class Compare { Map<String, Operator> opMap = new HashMap<String,
 * Operator>();
 * 
 * public Map<String, Operator> getOpMap() { return opMap; }
 * 
 * public void setOpMap(Map<String, Operator> opMap) { this.opMap = opMap; }
 * 
 * public Compare() { opMap.put(">", new Operator() {
 * 
 * @Override public boolean compare(int a, int b) { return a > b; } });
 * opMap.put(">=", new Operator() {
 * 
 * @Override public boolean compare(int a, int b) { return a >= b; } });
 * opMap.put("<", new Operator() {
 * 
 * @Override public boolean compare(int a, int b) { return a < b; } });
 * opMap.put("<=", new Operator() {
 * 
 * @Override public boolean compare(int a, int b) { return a <= b; } });
 * opMap.put("=", new Operator() {
 * 
 * @Override public boolean compare(int a, int b) { return a == b; } });
 * 
 * }
 * 
 * public static void main(String[] args) { Compare com = new Compare();
 * System.out.printf("resul " + com.opMap.get("<=").compare(11, 8)); }
 * 
 * }
 */
