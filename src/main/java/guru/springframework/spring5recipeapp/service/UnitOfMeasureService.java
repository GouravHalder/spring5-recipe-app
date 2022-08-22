package guru.springframework.spring5recipeapp.service;

import java.util.Set;

import guru.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {
	public Set<UnitOfMeasureCommand> listAllUoms();
}
