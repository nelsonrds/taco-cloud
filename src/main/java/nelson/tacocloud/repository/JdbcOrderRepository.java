package nelson.tacocloud.repository;




public class JdbcOrderRepository  {

    // private JdbcOperations jdbcOperations;

    // public JdbcOrderRepository(JdbcOperations jdbcOperations) {
    //     this.jdbcOperations = jdbcOperations;
    // }

    // @Override
    // @Transactional
    // public TacoOrder save(TacoOrder tacoOrder) {

    //     PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(
    //             "insert into Taco_Order (delivery_Name,"
    //                     + "delivery_Street,"
    //                     + "delivery_City,"
    //                     + "delivery_State,"
    //                     + "delivery_Zip,"
    //                     + "cc_number,"
    //                     + "cc_expiration,"
    //                     + "cc_cvv,"
    //                     + "placed_at) values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
    //             Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
    //             Types.VARCHAR, Types.TIMESTAMP);
    //     preparedStatementCreatorFactory.setReturnGeneratedKeys(true);
    //     tacoOrder.setPlacedAt(new Date());

    //     PreparedStatementCreator preparedStatementCreator = preparedStatementCreatorFactory
    //             .newPreparedStatementCreator(Arrays.asList(tacoOrder.getDeliveryName(), tacoOrder.getDeliveryStreet(),
    //                     tacoOrder.getDeliveryCity(), tacoOrder.getDeliveryState(), tacoOrder.getDeliveryZip(),
    //                     tacoOrder.getCcNumber(), tacoOrder.getCcExpiration(), tacoOrder.getCcCVV(),
    //                     tacoOrder.getPlacedAt()));

    //     GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

    //     jdbcOperations.update(preparedStatementCreator, keyHolder);
    //     final Long orderId = keyHolder.getKey().longValue();
    //     tacoOrder.setId(orderId);

    //     List<Taco> tacos = tacoOrder.getTacos();
    //     int i = 0;
    //     for (Taco taco : tacos) {
    //         saveTaco(orderId, i++, taco);
    //     }

    //     return tacoOrder;
    // }

    // private Long saveTaco(Long orderId, int orderKey, Taco taco) {
    //     taco.setCreatedAt(new Date());

    //     PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(
    //             "insert into Taco (name, created_at, taco_order, taco_order_key) values (?, ?, ?, ?)",
    //             Types.VARCHAR, Types.TIMESTAMP, Types.BIGINT, Types.BIGINT);
    //     preparedStatementCreatorFactory.setReturnGeneratedKeys(true);

    //     PreparedStatementCreator preparedStatementCreator = preparedStatementCreatorFactory
    //             .newPreparedStatementCreator(Arrays.asList(taco.getName(), taco.getCreatedAt(), orderId, orderKey));

    //     GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

    //     jdbcOperations.update(preparedStatementCreator, keyHolder);
    //     final Long tacoId = keyHolder.getKey().longValue();
    //     taco.setId(tacoId);

    //     saveIngredientRefs(tacoId, taco.getIngredients());

    //     return tacoId;
    // }

    // private void saveIngredientRefs(Long tacoId, List<Ingredient> ingredientRefs) {
    //     int key = 0;
    //     for (Ingredient ingredientRef : ingredientRefs) {
    //         jdbcOperations.update("insert into Ingredient_Ref (ingredient, taco, taco_key) values (?, ?, ?)",
    //                 ingredientRef.getName(), tacoId, key++);
    //     }
    // }

}
