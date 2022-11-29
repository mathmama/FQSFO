package com.octest.labo;

import java.util.Arrays;

public class SplitTests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String condition = "(release_period = 'new')>0.6 ";
		String[] res = condition.split("=|>=|>");
		System.out.println(Arrays.toString(res));
		System.out.println("Attribut:" + res[0].replaceAll("['-(-\\s+]", "") + "####");
		System.out.println("EvaluateValue:" + res[1].replaceAll("['-)-\\s+]", "") + "####");
		if (res.length > 2)
			System.out.println("Threshold:" + res[2].replaceAll(" ", "") + "####");

	}

}
