package nelson.tacocloud.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import nelson.tacocloud.model.Ingredient;
import nelson.tacocloud.model.Ingredient.Type;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

    List<Ingredient> findByType(Type type);
}
