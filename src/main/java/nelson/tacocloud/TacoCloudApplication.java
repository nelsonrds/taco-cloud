package nelson.tacocloud;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import nelson.tacocloud.model.Ingredient;
import nelson.tacocloud.model.RegistrationForm;
import nelson.tacocloud.model.Ingredient.Type;
import nelson.tacocloud.repository.IngredientRepository;
import nelson.tacocloud.repository.UserRepository;

@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repository, UserRepository userRepository,
			PasswordEncoder passwordEncoder) {
		return args -> {
			repository.save(new Ingredient("TEST", "THISISTEST", Type.CHEESE));
			userRepository.save(
					new RegistrationForm("nelson", "nelson", "nelson", "nelsoN", "nelson", "nelson", "nelson", "nelson")
							.toUser(passwordEncoder));
			System.out.println(repository.findByType(Type.CHEESE));
		};
	}

	@Bean
	public ApplicationRunner dataLoaderApp(IngredientRepository repository) {
		return args -> {
			System.out.println(repository.findByType(Type.CHEESE));
		};
	}

}
