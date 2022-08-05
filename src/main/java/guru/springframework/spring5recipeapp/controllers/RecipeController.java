package guru.springframework.spring5recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.spring5recipeapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RecipeController {
	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}
	@RequestMapping({"/recipe/show/{id}"})
	public String showById(@PathVariable String id, Model model) {
		//recipeService.findById(new Long(id));
		model.addAttribute("recipe",recipeService.findById(new Long(id)));
		return "recipe/show";
	}

}
