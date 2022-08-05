package guru.springframework.spring5recipeapp.commands;

import java.math.BigDecimal;

import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class IngridientCommand {
	private Long id;
	private String description;
	private BigDecimal amount;
	private UnitOfMeasure uom;
}
