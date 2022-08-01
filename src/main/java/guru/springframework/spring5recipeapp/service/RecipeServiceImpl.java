package guru.springframework.spring5recipeapp.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepos;
@Service
public class RecipeServiceImpl implements RecipeService{

	private final RecipeRepos recipeRepos;
	
	
	public RecipeServiceImpl(RecipeRepos recipeRepos) {
		super();
		this.recipeRepos = recipeRepos;
	}


	@Override
	public Set<Recipe> getRecipes() {
		// TODO Auto-generated method stub
		HashSet<Recipe> recipeSet = new HashSet<>();
		recipeRepos.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

}
