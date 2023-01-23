package nelson.tacocloud.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import nelson.tacocloud.model.Ingredient;


public class JdbcIngredientRepository {
    // private JdbcTemplate jdbcTemplate;

    // public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
    //     this.jdbcTemplate = jdbcTemplate;
    // }

    // @Override
    // public List<Ingredient> findAll() {
    //     return jdbcTemplate.query("select id, name, type from Ingredient",
    //             this::mapRowToIngredient);
    // }

    // @Override
    // public Optional<Ingredient> findById(String id) {
    //     List<Ingredient> result = jdbcTemplate.query("select id, name, type from Ingredient where id = ?",
    //             this::mapRowToIngredient, id);
    //     return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    // }

    // @Override
    // public Ingredient save(Ingredient ingredient) {
    //     jdbcTemplate.update("insert into Ingredient (id, name, type) values (?, ?, ?)", ingredient.getId(),
    //             ingredient.getName(), ingredient.getType().toString());
    //     return ingredient;
    // }

    // private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
    //     return new Ingredient(rs.getString("id"), rs.getString("name"), Ingredient.Type.valueOf(rs.getString("type")));
    // }

}
