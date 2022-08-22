package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.commands.IngridientCommand;

public interface IngridientService {
	IngridientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
	IngridientCommand saveIngredientCommand(IngridientCommand command);
	void deleteById(Long recipeId, Long ingridientId);
}
