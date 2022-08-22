package guru.springframework.spring5recipeapp.service;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.spring5recipeapp.commands.IngridientCommand;
import guru.springframework.spring5recipeapp.converters.IngridientCommandToIngridient;
import guru.springframework.spring5recipeapp.converters.IngridientToIngridientCommand;
import guru.springframework.spring5recipeapp.domain.Ingridient;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepos;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepos;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngridientServiceImpl implements IngridientService {

	private final IngridientToIngridientCommand ingredientToIngredientCommand;
	private final IngridientCommandToIngridient ingredientCommandToIngredient;
	private final RecipeRepos recipeRepository;
	private final UnitOfMeasureRepos unitOfMeasureRepository;

	public IngridientServiceImpl(IngridientToIngridientCommand ingredientToIngredientCommand,
			IngridientCommandToIngridient ingredientCommandToIngredient, RecipeRepos recipeRepository,
			UnitOfMeasureRepos unitOfMeasureRepository) {
		super();
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public IngridientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		// TODO Auto-generated method stub
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		if (!recipeOptional.isPresent()) {
			// todo impl error handling
			log.error("recipe id not found. Id: " + recipeId);
		}
		Recipe recipe = recipeOptional.get();
		Set<Ingridient> iSet = recipe.getIngridients();
		Iterator<Ingridient> it = iSet.iterator();
		while (it.hasNext()) {
			Ingridient ii = it.next();
			Long iiId = ii.getId();
			if (iiId == ingredientId) {
				return ingredientToIngredientCommand.convert(ii);
			}
		}

		return null;
	}
	/*
	 * @Override public IngridientCommand findByRecipeIdAndIngredientId(Long
	 * recipeId, Long ingredientId) { Optional<Recipe> recipeOptional =
	 * recipeRepository.findById(recipeId);
	 * 
	 * if (!recipeOptional.isPresent()) { // todo impl error handling
	 * log.error("recipe id not found. Id: " + recipeId); }
	 * 
	 * Recipe recipe = recipeOptional.get();
	 * 
	 * Optional<IngridientCommand> ingredientCommandOptional =
	 * recipe.getIngridients().stream() .filter(ingredient ->
	 * ingredient.getId().equals(ingredientId)) .map(ingredient ->
	 * ingredientToIngredientCommand.convert(ingredient)).findFirst();
	 * 
	 * if (!ingredientCommandOptional.isPresent()) { // todo impl error handling
	 * log.error("Ingredient id not found: " + ingredientId); }
	 * 
	 * return ingredientCommandOptional.get();
	 * 
	 * }
	 */

	@Override
	public IngridientCommand saveIngredientCommand(IngridientCommand command) {
		// TODO Auto-generated method stub
		log.debug("In saveIngredientCommand() of IMPL class "+command.getRecipeId());
		Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

		if (!recipeOptional.isPresent()) {

			// todo toss error if not found!
			log.error("Recipe not found for id: " + command.getRecipeId());
			return new IngridientCommand();
		} else {
			Recipe recipe = recipeOptional.get();

			Optional<Ingridient> ingredientOptional = recipe.getIngridients().stream()
					.filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst();

			if (ingredientOptional.isPresent()) {
			
				Ingridient ingredientFound = ingredientOptional.get();
				ingredientFound.setDescription(command.getDescription());
				ingredientFound.setAmount(command.getAmount());
				ingredientFound.setUom(unitOfMeasureRepository.findById(command.getUom().getId())
						.orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); // todo address this
			} else {
				// add new Ingredient
			
				recipe.addIngridient(ingredientCommandToIngredient.convert(command));

			}
			
			Recipe savedRecipe = recipeRepository.save(recipe);
			 Optional<Ingridient> savedIngredientOptional = savedRecipe.getIngridients().stream()
	                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
	                    .findFirst();

	            //check by description
	            if(!savedIngredientOptional.isPresent()){
	                //not totally safe... But best guess
	                savedIngredientOptional = savedRecipe.getIngridients().stream()
	                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
	                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
	                        .filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(command.getUom().getId()))
	                        .findFirst();
	            }

	            //to do check for fail
	            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
			 
			
		}
	}

	@Override
	public void deleteById(Long recipeId, Long ingridientId) {
		// TODO Auto-generated method stub
		
		log.debug("Deleting ingredient: " + recipeId + ":" + ingridientId);
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(recipeOptional.isPresent()){
            Recipe recipe = recipeOptional.get();
            log.debug("found recipe");

            Optional<Ingridient> ingredientOptional = recipe.getIngridients().stream().filter(ingredient -> ingredient.getId().equals(ingridientId)).findFirst();

            if(ingredientOptional.isPresent()){
                log.debug("found Ingredient");
                Ingridient ingredientToDelete = ingredientOptional.get();
                ingredientToDelete.setRecipe(null);
                recipe.getIngridients().remove(ingredientOptional.get());
                recipeRepository.save(recipe);
            }
        } else {
            log.debug("Recipe Id Not found. Id:" + recipeId);
        }
		
	}
}