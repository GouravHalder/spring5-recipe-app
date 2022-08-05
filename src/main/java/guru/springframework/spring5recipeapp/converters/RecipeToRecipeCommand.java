package guru.springframework.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.domain.Recipe;
import lombok.Synchronized;
@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>{
	private final NotesToNotesCommand notesToNotesCommand;
	private final CategoryToCategoryCommand categoryConveter;
	private final IngridientToIngridientCommand ingridientToIngridientCommand;
	
	
	
	public RecipeToRecipeCommand(NotesToNotesCommand notesToNotesCommand, CategoryToCategoryCommand categoryConveter,
			IngridientToIngridientCommand ingridientToIngridientCommand) {
		super();
		this.notesToNotesCommand = notesToNotesCommand;
		this.categoryConveter = categoryConveter;
		this.ingridientToIngridientCommand = ingridientToIngridientCommand;
	}


	@Synchronized
    @Nullable
    @Override
	public RecipeCommand convert(Recipe source) {
		if (source == null)
		{
			return null;
		}
		final RecipeCommand recipecommand = new RecipeCommand();
		recipecommand.setId(source.getId());
		recipecommand.setDescription(source.getDescription());
		recipecommand.setPrepTime(source.getPrepTime());
		recipecommand.setCookTime(source.getCookTime());
		recipecommand.setDifficulty(source.getDifficulty());
		recipecommand.setDirections(source.getDirections());
		recipecommand.setServings(source.getServings());
		recipecommand.setSource(source.getSource());
		recipecommand.setUrl(source.getUrl());
		recipecommand.setNotes(notesToNotesCommand.convert(source.getNotes()));
		if (source.getCategory() != null && source.getCategory().size() > 0){
            source.getCategory()
                    .forEach((Category category) -> recipecommand.getCategory().add(categoryConveter.convert(category)));
        }

        if (source.getIngridients() != null && source.getIngridients().size() > 0){
            source.getIngridients()
                    .forEach(ingredient -> recipecommand.getIngridients().add(ingridientToIngridientCommand.convert(ingredient)));
        }
        return recipecommand;
	}
}
