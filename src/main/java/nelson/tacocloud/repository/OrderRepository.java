package nelson.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;

import nelson.tacocloud.model.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, String> {

    
}
