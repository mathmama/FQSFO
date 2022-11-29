package com.octest.labo;

import org.json.simple.JSONArray;

public class JOINTJSONARRAY {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s1 = "[{name: \"Bob\", car: \"Ford\"},{name: \"Mary\", car: \"Fiat\"}]";
		String s2 = "[{name: \"Mack\", car: \"VW\"},{name: \"Steve\", car: \"Mercedes Benz\"}]";

		JSONArray sourceArray = new JSONArray();
		JSONArray destinationArray = new JSONArray();

		for (int i = 0; i < ((CharSequence) sourceArray).length(); i++) {
			// destinationArray.put(((Object) sourceArray).getJSONObject(i));
		}

		String s3 = destinationArray.toString();

	}

}
