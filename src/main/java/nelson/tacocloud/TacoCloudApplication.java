package nelson.tacocloud;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import nelson.tacocloud.model.Ingredient;
import nelson.tacocloud.model.Ingredient.Type;
import nelson.tacocloud.repository.IngredientRepository;

@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repository) {
		return args -> {
			repository.save(new Ingredient("TEST", "THISISTEST", Type.CHEESE));
			System.out.println(repository.findByType(Type.CHEESE));
		};
	}

	@Bean
	public ApplicationRunner dataLoaderApp(IngredientRepository repository){
		return args -> {
			System.out.println(repository.findByType(Type.CHEESE));
		};
	}

}
