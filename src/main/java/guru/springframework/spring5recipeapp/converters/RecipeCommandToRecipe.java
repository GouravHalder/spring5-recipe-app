package guru.springframework.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import lombok.Synchronized;
@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe>{
	 private final CategoryCommandToCategory categoryConveter;
	    private final IngridientCommandToIngridient ingredientConverter;
	    private final NotesCommandToNotes notesConverter;
	    
	public RecipeCommandToRecipe(CategoryCommandToCategory categoryConveter,
				IngridientCommandToIngridient ingredientConverter, NotesCommandToNotes notesConverter) {
			super();
			this.categoryConveter = categoryConveter;
			this.ingredientConverter = ingredientConverter;
			this.notesConverter = notesConverter;
		}
	@Synchronized
    @Nullable
    @Override
	public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setDescription(source.getDescription());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setDirections(source.getDirections());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getCategory() != null && source.getCategory().size() > 0){
            source.getCategory()
                    .forEach( category -> recipe.getCategory().add(categoryConveter.convert(category)));
        }

        if (source.getIngridients() != null && source.getIngridients().size() > 0){
            source.getIngridients()
                    .forEach(ingredient -> recipe.getIngridients().add(ingredientConverter.convert(ingredient)));
        }

        return recipe;
    }
}
