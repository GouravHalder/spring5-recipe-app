package guru.springframework.spring5recipeapp.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.domain.Difficulty;
import guru.springframework.spring5recipeapp.domain.Ingridient;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5recipeapp.repositories.CategoryRepos;
import guru.springframework.spring5recipeapp.repositories.RecipeRepos;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepos;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent>{
	private final CategoryRepos categoryRepos;
	private final RecipeRepos recipeRepos; 
	private final UnitOfMeasureRepos unitOfMeasureRepos;
	public RecipeBootstrap(CategoryRepos categoryRepos, RecipeRepos recipeRepos,
			UnitOfMeasureRepos unitOfMeasureRepos) {
		super();
		this.categoryRepos = categoryRepos;
		this.recipeRepos = recipeRepos;
		this.unitOfMeasureRepos = unitOfMeasureRepos; 
	}
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		log.debug("onApplicationEvent() loading Bootstrap");
		recipeRepos.saveAll(getRecipes());
	}
	public List<Recipe> getRecipes(){
		// TODO Auto-generated method stub
		List<Recipe> recipes= new ArrayList<>(2);
		Optional<UnitOfMeasure> optionalUomTSP = unitOfMeasureRepos.findByDescription("Teaspoon");
		if (!optionalUomTSP.isPresent()) {
			throw new RuntimeException("Tea SP not present");
		}
			
		Optional<UnitOfMeasure> optionalUomTBSP = unitOfMeasureRepos.findByDescription("Tablespoon");
		if (!optionalUomTBSP.isPresent()) {
			throw new RuntimeException("Table spoon SP not present");
		}
		Optional<UnitOfMeasure> optionalUomCUP = unitOfMeasureRepos.findByDescription("Cup");
		if (!optionalUomCUP.isPresent()) {
			throw new RuntimeException("Cup not present");
		}
		Optional<UnitOfMeasure> optionalUomPINCH = unitOfMeasureRepos.findByDescription("Pinch");
		if (!optionalUomPINCH.isPresent()) {
			throw new RuntimeException("Pinch SP not present");
		}
		Optional<UnitOfMeasure> optionalUomOUNCE = unitOfMeasureRepos.findByDescription("Ounce");
		if (!optionalUomOUNCE.isPresent()) {
			throw new RuntimeException("Tea SP not present");
		}
		Optional<Category> optionalCatInd = categoryRepos.findBydesc("Indian");
		if (!optionalCatInd.isPresent()) {
			throw new RuntimeException("Indian Category not present");
		}
		Optional<Category> optionalCatMex = categoryRepos.findBydesc("Mexican");
		if (!optionalCatMex.isPresent()) {
			throw new RuntimeException("Mexican Category not present");
		}
		Recipe guaRecipe=new Recipe();
		guaRecipe.setDescription("Perfect Guacamole");
		guaRecipe.setPrepTime(10);
		guaRecipe.setCookTime(10);
		guaRecipe.setDifficulty(Difficulty.EASY);
		guaRecipe.setServings(2);
		guaRecipe.setDescription("Chicken Curry");
		guru.springframework.spring5recipeapp.domain.Notes guaNotes = new guru.springframework.spring5recipeapp.domain.Notes();
		guaNotes.setRecipeNotes("This is Recipe Notes");
		//guaNotes.setRecipe(guaRecipe);
		guaRecipe.setNotes(guaNotes);
		//guaRecipe.getIngridients().add(new Ingridient("1st Ingridient", new BigDecimal(2), optionalUomTSP.get(),guaRecipe));
		guaRecipe.addIngridient(new Ingridient("1st Ingridient", new BigDecimal(2), optionalUomTSP.get()));
		guaRecipe.getCategory().add(optionalCatInd.get());
		guaRecipe.getCategory().add(optionalCatMex.get());
		System.out.println("Adding Guarecipe is List");
		recipes.add(guaRecipe);
		
		Recipe tacoRecipe=new Recipe();
		tacoRecipe.setDescription("Chocken Taco");
		tacoRecipe.setPrepTime(20);
		tacoRecipe.setCookTime(20);
		tacoRecipe.setDifficulty(Difficulty.MODERATE);
		tacoRecipe.setServings(5);
		tacoRecipe.setDescription("Mutton Biriyani");
		guru.springframework.spring5recipeapp.domain.Notes tacoNotes = new guru.springframework.spring5recipeapp.domain.Notes();
		tacoNotes.setRecipeNotes("This is TACO Notes");
		tacoNotes.setRecipe(tacoRecipe);
		tacoRecipe.setNotes(tacoNotes);
		//tacoRecipe.getIngridients().add(new Ingridient("1st Ingridient", new BigDecimal(2), optionalUomTSP.get(),tacoRecipe));
		//tacoRecipe.getIngridients().add(new Ingridient("2nd Ingridient", new BigDecimal(2), optionalUomTBSP.get(),tacoRecipe));
		//tacoRecipe.getIngridients().add(new Ingridient("3rd Ingridient", new BigDecimal(2), optionalUomCUP.get(),tacoRecipe));
		tacoRecipe.addIngridient(new  Ingridient("1st Ingridient", new BigDecimal(1), optionalUomTSP.get()));
		tacoRecipe.addIngridient(new  Ingridient("2nd Ingridient", new BigDecimal(2), optionalUomTSP.get()));
		tacoRecipe.addIngridient(new  Ingridient("3rd Ingridient", new BigDecimal(3), optionalUomTSP.get()));
		tacoRecipe.getCategory().add(optionalCatInd.get());
		System.out.println("Adding tacoRecipe is List");
		recipes.add(tacoRecipe);
		return recipes;
	}
	
	
}
