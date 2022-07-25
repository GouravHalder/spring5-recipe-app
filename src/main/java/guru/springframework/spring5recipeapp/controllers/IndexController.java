package guru.springframework.spring5recipeapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

public class IndexController {
	
	@RequestMapping({"","/","index"})
	public String getIndexPage() {
		System.out.println("In getIndexPage() of IndexController");
		return "index";
	}

}
