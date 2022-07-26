package guru.springframework.spring5recipeapp.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5recipeapp.repositories.CategoryRepos;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepos;

@Controller
public class IndexController {
	
	private final CategoryRepos categoryRepos;
	private final UnitOfMeasureRepos unitOfMeasureRepos;
	
	
	
	public IndexController(CategoryRepos categoryRepos, UnitOfMeasureRepos unitOfMeasureRepos) {
		super();
		this.categoryRepos = categoryRepos;
		this.unitOfMeasureRepos = unitOfMeasureRepos;
		System.out.println("Bombie");
	}



	@RequestMapping({"","/","index"})
	public String getIndexPage() {
		System.out.println("In getIndexPage() of IndexController");
		Optional<Category> optionalCategory = categoryRepos.findBydesc("Indian");
		Optional<UnitOfMeasure> optionalUom = unitOfMeasureRepos.findByDescription("Cup");
		System.out.println("optionalCategory is "+optionalCategory.get().getId());
		System.out.println("optionalUom is "+optionalUom.get().getId());
		return "index";
	}

}
