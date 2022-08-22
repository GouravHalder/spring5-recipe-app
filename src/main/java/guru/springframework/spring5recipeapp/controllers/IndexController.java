package guru.springframework.spring5recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.spring5recipeapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class IndexController {
	
	private final RecipeService recipeService;
	public IndexController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@RequestMapping({"","/","index"})
	public String getIndexPage(Model model) {
		log.debug("In getIndexPage() of IndexController");
		log.debug("In getIndexPage() of IndexController");
		log.debug("In getIndexPage() of IndexController");
		log.debug("In getIndexPage() of IndexController");
		log.debug("In getIndexPage() of IndexController");
		log.debug("In getIndexPage() of IndexController");
		log.debug("In getIndexPage() of IndexController");
		log.info("In getIndexPage() of IndexController INFO");
		log.info("In getIndexPage() of IndexController INFO");
		log.info("In getIndexPage() of IndexController INFO");
		log.info("In getIndexPage() of IndexController INFO");
		log.info("In getIndexPage() of IndexController INFO");
		log.debug("In getIndexPage() of IndexController");
		log.debug("In getIndexPage() of IndexController");
		log.debug("In getIndexPage() of IndexController");
		log.debug("In getIndexPage() of IndexController");
		System.out.println("In getIndexPage() of IndexController");
		model.addAttribute("recipes", recipeService.getRecipes());
		return "index";
	}

}
