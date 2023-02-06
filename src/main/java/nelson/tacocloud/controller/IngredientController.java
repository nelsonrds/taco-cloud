package nelson.tacocloud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import nelson.tacocloud.model.Ingredient;
import nelson.tacocloud.repository.IngredientRepository;

@RestController
@RequestMapping(produces = "application/json", path = "/api/ingredients")
public class IngredientController {

    private IngredientRepository repository;

    public IngredientController(IngredientRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Ingredient> allIngredients() {
        return repository.findAll();
    }

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient saveIngredient(@RequestBody final Ingredient ingredient) {
        return repository.save(ingredient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable("id") final String ingredientId) {
        repository.deleteById(ingredientId);
    }
}
