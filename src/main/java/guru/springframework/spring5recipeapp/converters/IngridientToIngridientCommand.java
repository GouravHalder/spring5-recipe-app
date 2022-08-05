package guru.springframework.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.spring5recipeapp.commands.IngridientCommand;
import guru.springframework.spring5recipeapp.domain.Ingridient;
import lombok.Synchronized;
@Component
public class IngridientToIngridientCommand implements Converter<Ingridient, IngridientCommand>{
	@Synchronized
    @Nullable
    @Override
	public IngridientCommand convert(Ingridient source) {
		if (source == null)
		{
			return null;
		}
		final IngridientCommand ingridientCommand = new IngridientCommand();
		ingridientCommand.setId(source.getId());
		ingridientCommand.setDescription(source.getDescription());
		ingridientCommand.setAmount(source.getAmount());
		ingridientCommand.setUom(source.getUom());
		//source.getRecipe();
		return ingridientCommand;
	}
}
