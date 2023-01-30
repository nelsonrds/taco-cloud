package nelson.tacocloud;

import java.util.Arrays;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import nelson.tacocloud.model.Ingredient;
import nelson.tacocloud.model.RegistrationForm;
import nelson.tacocloud.model.Taco;
import nelson.tacocloud.model.Ingredient.Type;
import nelson.tacocloud.repository.IngredientRepository;
import nelson.tacocloud.repository.TacoRepository;
import nelson.tacocloud.repository.UserRepository;

@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(IngredientRepository ingredientRepository, UserRepository userRepository,
			PasswordEncoder passwordEncoder, TacoRepository tacoRepository) {
		return args -> {
			Ingredient flour = ingredientRepository.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
			Ingredient corn = ingredientRepository.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
			Ingredient beef = ingredientRepository.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
			Ingredient carnitas = ingredientRepository.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
			Ingredient tomatoes = ingredientRepository.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
			Ingredient lettuce = ingredientRepository.save(new Ingredient("LECT", "Lettuce", Type.VEGGIES));
			Ingredient cheddar = ingredientRepository.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
			Ingredient jack = ingredientRepository.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
			Ingredient salsa = ingredientRepository.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
			Ingredient sourCream = ingredientRepository.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

			Taco taco1 = new Taco();
			taco1.setName("Carnivore");
			taco1.setIngredients(Arrays.asList(flour, beef, carnitas, sourCream, salsa, cheddar));
			tacoRepository.save(taco1);

			Taco taco2 = new Taco();
			taco2.setName("Bovine Bounty");
			taco2.setIngredients(Arrays.asList(corn, beef, cheddar, jack, sourCream));
			tacoRepository.save(taco2);

			Taco taco3 = new Taco();
			taco3.setName("Veg-Out");
			taco3.setIngredients(Arrays.asList(flour, corn, tomatoes, lettuce, salsa));
			tacoRepository.save(taco3);

			userRepository.save(
					new RegistrationForm("nelson", "nelson", "nelson", "nelsoN", "nelson", "nelson", "nelson", "nelson",
							"ROLE_USER")
							.toUser(passwordEncoder));
			userRepository.save(
					new RegistrationForm("nelson2", "nelson", "nelson", "nelsoN", "nelson", "nelson", "nelson",
							"nelson", "ROLE_ADMIN")
							.toUser(passwordEncoder));
		};
	}

	@Bean
	@Profile("!prod")
	public ApplicationRunner dataLoaderApp(IngredientRepository repository) {
		return args -> {
			System.out.println(repository.findByType(Type.CHEESE));
		};
	}

}
