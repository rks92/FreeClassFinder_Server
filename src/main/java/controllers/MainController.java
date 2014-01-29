package controllers;

import java.util.Vector;

import models.ClassModel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	public static Vector<ClassModel> classList = new Vector<ClassModel>();
	
	@RequestMapping(value = {"/", "/FreeClassFinder", "/FreeClassFinder/"})
	public String homePage(){
		
		return "home";
	}
}
