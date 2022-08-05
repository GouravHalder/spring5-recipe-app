package guru.springframework.spring5recipeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootApplication
public class Spring5RecipeAppApplication {

	public static void main(String[] args) {  
		System.out.println("My Gourav");
		log.debug("My Gourav in Spring5RecipeAppApplication SLF4J");
		SpringApplication.run(Spring5RecipeAppApplication.class, args);
	}

}
