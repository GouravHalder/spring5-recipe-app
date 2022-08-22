package guru.springframework.spring5recipeapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

	public NotFoundException() {
		super();
		System.out.println("1.NotFoundExceptionNotFoundExceptionNotFoundExceptionNotFoundException");
		// TODO Auto-generated constructor stub
	}
	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
		System.out.println("2. NotFoundExceptionNotFoundExceptionNotFoundExceptionNotFoundException");
		// TODO Auto-generated constructor stub
	}

	public NotFoundException(String message) {
		super(message);
		System.out.println("3. NotFoundExceptionNotFoundExceptionNotFoundExceptionNotFoundException");
		// TODO Auto-generated constructor stub
	}
	
}
