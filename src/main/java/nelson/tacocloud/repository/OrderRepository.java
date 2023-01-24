package nelson.tacocloud.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import nelson.tacocloud.model.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, UUID> {

    
}
