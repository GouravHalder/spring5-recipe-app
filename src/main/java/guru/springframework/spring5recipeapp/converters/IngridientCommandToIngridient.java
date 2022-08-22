package guru.springframework.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import guru.springframework.spring5recipeapp.commands.IngridientCommand;
import guru.springframework.spring5recipeapp.domain.Ingridient;
import guru.springframework.spring5recipeapp.domain.Recipe;
@Component
public class IngridientCommandToIngridient implements Converter<IngridientCommand, Ingridient>{
	
    private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;
	


	public IngridientCommandToIngridient(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {
		super();
		this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
	}



	public Ingridient convert(IngridientCommand source) {
		if (source == null)
		{
			return null;
		}
		final Ingridient ingridient = new Ingridient();
		ingridient.setId(source.getId());
		ingridient.setDescription(source.getDescription());
		ingridient.setAmount(source.getAmount());
		ingridient.setUom(unitOfMeasureCommandToUnitOfMeasure.convert(source.getUom()));
		//ingridient.setUom(null);
		 if(source.getRecipeId() != null){
	            Recipe recipe = new Recipe();
	            recipe.setId(source.getRecipeId());
	            ingridient.setRecipe(recipe);
	            recipe.addIngridient(ingridient);
	        }
		return ingridient;
	}
}
