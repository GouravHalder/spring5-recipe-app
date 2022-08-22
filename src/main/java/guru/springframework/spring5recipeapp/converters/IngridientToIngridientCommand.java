package guru.springframework.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import guru.springframework.spring5recipeapp.commands.IngridientCommand;
import guru.springframework.spring5recipeapp.domain.Ingridient;
@Component
public class IngridientToIngridientCommand implements Converter<Ingridient, IngridientCommand>{
	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
	
	
	
	public IngridientToIngridientCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
		super();
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}



	public IngridientCommand convert(Ingridient source) {
		if (source == null)
		{
			return null;
		}
		final IngridientCommand ingridientCommand = new IngridientCommand();
		ingridientCommand.setId(source.getId());
		ingridientCommand.setDescription(source.getDescription());
		ingridientCommand.setAmount(source.getAmount());
		ingridientCommand.setUom(unitOfMeasureToUnitOfMeasureCommand.convert(source.getUom()));
		if (source.getRecipe() != null) {
			ingridientCommand.setRecipeId(source.getRecipe().getId());
        }
		//source.getRecipe();
		return ingridientCommand;
	}
}
 