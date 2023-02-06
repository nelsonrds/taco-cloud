package nelson.tacocloud.service;

import nelson.tacocloud.model.Ingredient;


public interface IngredientService {
    Iterable<Ingredient> findAll();

    Ingredient addIngredient(Ingredient ingredient);
}
