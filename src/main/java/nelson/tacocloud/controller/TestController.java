// package nelson.tacocloud.controller;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import lombok.RequiredArgsConstructor;
// import nelson.tacocloud.model.Ingredient;
// import nelson.tacocloud.service.IngredientService;

// @RestController
// @RequestMapping("/api/test")
// public class TestController {

//     private IngredientService ingredientService;

//     public TestController(IngredientService ingredientService) {
//         this.ingredientService = ingredientService;
//     }

//     @GetMapping
//     public Iterable<Ingredient> getIngredients() {
//         return this.ingredientService.findAll();
//     }

// }
