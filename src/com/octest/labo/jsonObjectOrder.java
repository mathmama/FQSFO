package com.octest.labo;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class jsonObjectOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		try {
			Field changeMap = jsonObject.getClass().getDeclaredField("map");
			changeMap.setAccessible(true);
			changeMap.set(jsonObject, new LinkedHashMap<>());
			changeMap.setAccessible(false);
		} catch (IllegalAccessException | NoSuchFieldException e) {
			System.out.println(e.getMessage());
		}
		jsonObject.put("one", "hi");
		jsonObject.put("two", "there");
		jsonObject.put("three", "whats");
		jsonObject.put("four", "up");

		System.out.println(jsonObject.toString());

		Map obj = new LinkedHashMap();
		obj.put("a", "foo1");
		obj.put("b", new Integer(100));
		obj.put("c", new Double(1000.21));
		obj.put("d", new Boolean(true));
		obj.put("e", "foo2");
		obj.put("f", "foo3");
		obj.put("g", "foo4");
		obj.put("h", "foo5");
		obj.put("x", null);

		System.out.println("Ordered Json :" + obj.toString());
	}

}
