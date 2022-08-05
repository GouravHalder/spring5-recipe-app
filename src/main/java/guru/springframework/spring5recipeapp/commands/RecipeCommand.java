package guru.springframework.spring5recipeapp.commands;

import java.util.HashSet;
import java.util.Set;

import guru.springframework.spring5recipeapp.domain.Difficulty;
import guru.springframework.spring5recipeapp.domain.Notes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class RecipeCommand {
	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions;
	private Difficulty difficulty;
	private Set<IngridientCommand> ingridients = new HashSet<>();
	private NotesCommand notes;
	private Set<CategoryCommand> category = new HashSet<>();
}
