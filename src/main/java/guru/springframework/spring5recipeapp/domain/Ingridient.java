package guru.springframework.spring5recipeapp.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = ("recipe"))
@Entity
public class Ingridient {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	private BigDecimal amount;
	@OneToOne
	private UnitOfMeasure uom;
	@ManyToOne
	private Recipe recipe;
	
	
	
	
	public Ingridient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ingridient(String description, BigDecimal amount, UnitOfMeasure uom) {
		this.description = description;
		this.amount = amount;
		this.uom = uom;
	}
}
