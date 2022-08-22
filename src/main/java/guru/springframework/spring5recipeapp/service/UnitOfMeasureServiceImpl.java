package guru.springframework.spring5recipeapp.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import guru.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepos;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{
	private final UnitOfMeasureRepos unitOfMeasureRepos;
	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
	public UnitOfMeasureServiceImpl(UnitOfMeasureRepos unitOfMeasureRepos,
			UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
		super();
		this.unitOfMeasureRepos = unitOfMeasureRepos;
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}
	@Override
	public Set<UnitOfMeasureCommand> listAllUoms() {
		// TODO Auto-generated method stub
		return StreamSupport.stream(unitOfMeasureRepos.findAll()
                .spliterator(), false)
                .map(unitOfMeasureToUnitOfMeasureCommand::convert)
                .collect(Collectors.toSet());
	}
	
}
