package nelson.tacocloud.util;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import nelson.tacocloud.model.Ingredient;
import nelson.tacocloud.udt.IngredientUDT;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class TacoUDRUtils {

    public static IngredientUDT toIngredientUDT(Ingredient ingredient) {
        return new IngredientUDT(ingredient.getName(), ingredient.getType());
    }

}
