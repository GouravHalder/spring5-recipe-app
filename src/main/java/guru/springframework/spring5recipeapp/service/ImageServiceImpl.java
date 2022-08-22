package guru.springframework.spring5recipeapp.service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepos;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class ImageServiceImpl implements ImageService {

	private final RecipeRepos recipeRepository;


	public ImageServiceImpl(RecipeRepos recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}

	@Transactional
	@Override
	public void saveImageFile(Long recipeId, MultipartFile file) {
		// TODO Auto-generated method stub
		System.out.println("In the saveImageFile() of  ImageServiceImpl"+file);
		try {
            Recipe recipe = recipeRepository.findById(recipeId).get();
            System.out.println("Lenghth of Array is "+file.getBytes().length);
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            recipe.setImage(byteObjects);

            recipeRepository.save(recipe);
        } catch (IOException e) {
            //todo handle better
            log.error("Error occurred", e);

            e.printStackTrace();
        }
	}

}
