package cn.et.springmvc.lesson06.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {
	
	public static void parseObject(){
		Map map=new HashMap();
		map.put("id", 1);
		map.put("username", "A");
		JSONObject jo=JSONObject.fromObject(map);
		System.out.println(jo.toString());
	}
	
	
	public static void parseJsonArray(){
	
		Map map=new HashMap();
		map.put("id", 1);
		map.put("username", "A");
		
		Map address=new HashMap();
		address.put("city", "sz");
		address.put("stree", "gl");
		map.put("address", address);
		
	
		
		JSONObject ja=JSONObject.fromObject(map);
		System.out.println(ja.toString());
	}
	

	public static void main(String[] args) {
		
		parseJsonArray();
	}
}
