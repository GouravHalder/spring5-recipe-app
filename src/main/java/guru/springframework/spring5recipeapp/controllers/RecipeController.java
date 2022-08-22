package guru.springframework.spring5recipeapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.exceptions.NotFoundException;
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
	
	@GetMapping({"/recipe/{id}/show"})
	public String showById(@PathVariable String id, Model model) {
		//recipeService.findById(new Long(id));
		System.out.println("#####In showById()######");
		model.addAttribute("recipe",recipeService.findById(Long.valueOf(id)));
		return "recipe/show";
	}
	
	@GetMapping("recipe/new")
	public String newRecipe(Model model) {
		RecipeCommand recipeCommand= new RecipeCommand();
		model.addAttribute("recipe", recipeCommand);
		return "recipe/recipeform";
	}
	
	@PostMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		System.out.println("######In saveOrUpdate ######" +savedCommand.getId() );
		return "redirect:/recipe/"+savedCommand.getId()+"/show/";
	}
	
	@GetMapping({"/recipe/{id}/update"})
	public String updateRecipe (@PathVariable String id ,Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		return "recipe/recipeform";
	}
	@GetMapping({"/recipe/{id}/delete"})
	public String deleteRecipe (@PathVariable String id ,Model model) {
		recipeService.deleteById(Long.valueOf(id));
		model.addAttribute("recipes", recipeService.getRecipes());
		return "index";
	}
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public String handelNotFound(Model model,Exception ex)
	{
		System.out.println("In handelNotFound() of RecipeController class-GH32939");
		log.error("In handelNotFound() of RecipeController class");
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("404error");
		model.addAttribute("ex",ex);
		return "404error";
	}
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView handelNumberFormat(NumberFormatException e)
	{
		System.out.println("In handelNumberFormat() of RecipeController class-GH32939");
		log.error("In handelNumberFormat() of RecipeController class");
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("e",e);
		mnv.setViewName("400error");
		return mnv;
	}
}
