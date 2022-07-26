package guru.springframework.spring5recipeapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.spring5recipeapp.domain.Category;

public interface CategoryRepos extends CrudRepository<Category, Long> {
	Optional<Category> findBydesc(String desc);

}
