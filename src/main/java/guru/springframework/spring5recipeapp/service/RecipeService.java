package guru.springframework.spring5recipeapp.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.spring5recipeapp.domain.Recipe;
public interface RecipeService {
	public Set<Recipe> getRecipes();
}
