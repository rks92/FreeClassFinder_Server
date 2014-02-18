package controllers;

import java.util.Vector;

import models.ClassModel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


@Controller
public class MainController {
	public static Vector<ClassModel> classList = new Vector<ClassModel>();
	
	@RequestMapping(value = {"/", "/FreeClassFinder", "/FreeClassFinder/"})
	public String homePage(){
		
		return "home";
	}
	
	@ResponseBody
	@RequestMapping(value = {"/test", "/FreeClassFinder/test", "/FreeClassFinder/test/"})
	public String Test(){
		String test = "[{Building:MC,Room:1011,StartTime:111111111,EndTime:22222222},{Building:QC,Room:1021,StartTime:111111111,EndTime:22222222},{Building:DC,Room:1011,StartTime:111111111,EndTime:22222222}]";
		Gson temp = new Gson();
		JsonArray tempArray1 = new JsonArray();
		
		JsonObject obj1 = new JsonObject();
		obj1.addProperty("Building", "MC");
		obj1.addProperty("ROOM", 1011);
		obj1.addProperty("StartTime", System.currentTimeMillis());
		obj1.addProperty("EndTime", System.currentTimeMillis() + 10000);
		
		JsonObject obj2 = new JsonObject();
		obj2.addProperty("Building", "DC");
		obj2.addProperty("ROOM", 1011);
		obj2.addProperty("StartTime", System.currentTimeMillis());
		obj2.addProperty("EndTime", System.currentTimeMillis() + 10000);
		
		JsonObject obj3 = new JsonObject();
		obj3.addProperty("Building", "QNC");
		obj3.addProperty("ROOM", 1013);
		obj3.addProperty("StartTime", System.currentTimeMillis());
		obj3.addProperty("EndTime", System.currentTimeMillis() + 10000);
		
		tempArray1.add(obj1);
		tempArray1.add(obj2);
		tempArray1.add(obj3);
		tempArray1.toString();
		return tempArray1.toString();
	}
}
