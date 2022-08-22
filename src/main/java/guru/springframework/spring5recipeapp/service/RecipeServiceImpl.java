package guru.springframework.spring5recipeapp.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.converters.RecipeCommandToRecipe;
import guru.springframework.spring5recipeapp.converters.RecipeToRecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.exceptions.NotFoundException;
import guru.springframework.spring5recipeapp.repositories.RecipeRepos;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

	private final RecipeRepos recipeRepos;
	private final RecipeToRecipeCommand recipeToRecipeCommand;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	
	public RecipeServiceImpl(RecipeRepos recipeRepos, RecipeToRecipeCommand recipeToRecipeCommand,
			RecipeCommandToRecipe recipeCommandToRecipe) {
		super();
		this.recipeRepos = recipeRepos;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
	}
	@Override
	public Set<Recipe> getRecipes() {
		// TODO Auto-generated method stub
		log.debug("In SLF4J loggin of getRecipes() in ReceiServiceImpl");
		HashSet<Recipe> recipeSet = new HashSet<>();
		recipeRepos.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}


	@Override
	public Recipe findById(Long l) {
		// TODO Auto-generated method stub
		log.debug("In SLF4J loggin of findById() in ReceiServiceImpl");
		//Recipe recipe= recipeRepos.findById(l);
		Optional<Recipe> optionalRecipe= recipeRepos.findById(l);
		if (!optionalRecipe.isPresent())
		{
			throw new NotFoundException("Id not Found for "+l);
		}
		return optionalRecipe.get();
	}


	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
		// TODO Auto-generated method stub
		//return recipeToRecipeCommand.convert(recipeRepos.save(recipeCommandToRecipe.convert(recipeCommand)));
		Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);
        Recipe savedRecipe = recipeRepos.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
	}
	@Override
	@Transactional
	public RecipeCommand findCommandById(Long l) {
		// TODO Auto-generated method stub
		log.debug("In SLF4J loggin of findCommandById() in ReceiServiceImpl");
		Optional<Recipe> optionalRecipe= recipeRepos.findById(l);
		if (!optionalRecipe.isPresent())
		{
			throw new RuntimeException();
		}
		return recipeToRecipeCommand.convert(optionalRecipe.get());
	}
	@Override
	public void deleteById(Long l) {
		// TODO Auto-generated method stub
		log.debug("In SLF4J loggin of deleteById() in ReceiServiceImpl");
		recipeRepos.deleteById(l);
	}

}
