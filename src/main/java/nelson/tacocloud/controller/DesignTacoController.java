package nelson.tacocloud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.Errors;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import nelson.tacocloud.model.Ingredient;
import nelson.tacocloud.model.Taco;
import nelson.tacocloud.model.TacoOrder;
import nelson.tacocloud.model.Ingredient.Type;
import nelson.tacocloud.repository.IngredientRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private IngredientRepository ingredientRepository;

    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);

        // Arrays.asList(
        // new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
        // new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
        // new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
        // new Ingredient("CARN", "Carnitas", Type.PROTEIN),
        // new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
        // new Ingredient("LETC", "Lettuce", Type.VEGGIES),
        // new Ingredient("CHED", "Cheddar", Type.CHEESE),
        // new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
        // new Ingredient("SLSA", "Salsa", Type.SAUCE),
        // new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public String showDesignForm(Authentication authentication) {
        log.info("authenticated: " + authentication.getName());
        return "design";
    }

    @PostMapping
    public String processtaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        log.info("here!");
        if (errors.hasErrors())
            return "design";

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }

}
