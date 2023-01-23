package nelson.tacocloud.repository;

import java.util.List;
import java.util.Optional;

import nelson.tacocloud.model.Ingredient;

public interface IngredientRepository {
    List<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}
