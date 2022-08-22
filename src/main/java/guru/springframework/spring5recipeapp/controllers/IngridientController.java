package guru.springframework.spring5recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.spring5recipeapp.commands.IngridientCommand;
import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.converters.RecipeToRecipeCommand;
import guru.springframework.spring5recipeapp.service.IngridientService;
import guru.springframework.spring5recipeapp.service.RecipeService;
import guru.springframework.spring5recipeapp.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class IngridientController {
	
	private final RecipeService recipeService;
	private final IngridientService ingridientService;
	private final UnitOfMeasureService unitOfMeasureService;
	public IngridientController(RecipeService recipeService, IngridientService ingridientService,UnitOfMeasureService unitOfMeasureService) {
		super();
		this.recipeService = recipeService;
		this.ingridientService = ingridientService;
		this.unitOfMeasureService=unitOfMeasureService;
	}
	@GetMapping
	@RequestMapping({"/recipe/{recipeId}/ingridients"})
	public String listOfIngridients(@PathVariable String recipeId,Model model) {
		log.debug("In listOfIngridients() of IngridientController");
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
		return "recipe/ingridient/list";
	}
	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/{bombie}/show")
	public String showRecipeIngredient(@PathVariable String recipeId,  @PathVariable String bombie, Model model) {
		log.debug("In showRecipeIngredient() of IngridientController");
		log.debug("recipeId "+recipeId);
		log.debug("ingridientId "+bombie);
		log.debug("In showRecipeIngredient() of IngridientController");
		model.addAttribute("ingridient",ingridientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),Long.valueOf(bombie)));
		return "recipe/ingridient/show";
	}
	
	  @RequestMapping("recipe/{recipeId}/ingredient/{ingridientId}/update") 
	  public String updateRecipeIngredient (@PathVariable String recipeId, @PathVariable String ingridientId, Model model) {
	  log.debug("In updateRecipeIngredient() of IngridientController");
	  log.debug("recipeId "+recipeId); log.debug("ingridientId "+ingridientId);
	  log.debug("In updateRecipeIngredient() of IngridientController");
	  model.addAttribute("ingridient",ingridientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),Long.valueOf(ingridientId))); 
	  model.addAttribute("uomList", unitOfMeasureService.listAllUoms()); 
	  return "recipe/ingridient/ingredientform";
	  
	  }
	  @RequestMapping("recipe/{recipeId}/ingredient")
	  public String saveOrUpdate (@ModelAttribute IngridientCommand command) {
		  log.debug("In saveOrUpdate() of IngridientController");
		  IngridientCommand savedCommand= ingridientService.saveIngredientCommand(command);
		  return "redirect:/recipe/"+savedCommand.getRecipeId()+"/ingredient/"+savedCommand.getId()+"/show";
	  }
	  @GetMapping
	  @RequestMapping("recipe/{recipeId}/ingredient/new") 
	  public String newIngredient(@PathVariable String recipeId, Model model){
		  log.debug("In newIngredient() of IngridientController");
		  RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
		  IngridientCommand ingridientCommand = new IngridientCommand();
		  ingridientCommand.setRecipeId(recipeCommand.getId());
		  ingridientCommand.setUom(new UnitOfMeasureCommand());
		  model.addAttribute("ingridient",ingridientCommand);
		  model.addAttribute("uomList",unitOfMeasureService.listAllUoms());
		  return "recipe/ingridient/ingredientform";
	  }
	  @RequestMapping("recipe/{recipeId}/ingredient/{ingridientId}/delete") 
	  public String deleteIngredient(@PathVariable String recipeId,@PathVariable String ingridientId, Model model) {
		  log.debug("In deleteIngredient() of IngridientController");
		  ingridientService.deleteById(Long.valueOf(recipeId),Long.valueOf(ingridientId));
		  return "redirect:/recipe/"+recipeId+"/ingridients";
		  //return "recipe/"+recipeId+"/ingridients";
		  
	  }
}
