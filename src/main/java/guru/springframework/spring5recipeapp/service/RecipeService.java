package guru.springframework.spring5recipeapp.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
public interface RecipeService {
	public Set<Recipe> getRecipes();
	Recipe findById(Long l);
	RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);   
	RecipeCommand findCommandById(Long l);
	void deleteById(Long l);
}
