package nelson.tacocloud.repository;

import nelson.tacocloud.model.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder tacoOrder);

}
