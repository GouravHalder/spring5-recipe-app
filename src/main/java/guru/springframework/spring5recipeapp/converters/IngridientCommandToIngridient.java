package guru.springframework.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.spring5recipeapp.commands.IngridientCommand;
import guru.springframework.spring5recipeapp.domain.Ingridient;
import lombok.Synchronized;
@Component
public class IngridientCommandToIngridient implements Converter<IngridientCommand, Ingridient>{
	@Synchronized
    @Nullable
    @Override
	public Ingridient convert(IngridientCommand source) {
		if (source == null)
		{
			return null;
		}
		final Ingridient ingridient = new Ingridient();
		ingridient.setId(source.getId());
		ingridient.setDescription(source.getDescription());
		ingridient.setAmount(source.getAmount());
		ingridient.setUom(source.getUom());
		//source.getRecipe();
		return ingridient;
	}
}
