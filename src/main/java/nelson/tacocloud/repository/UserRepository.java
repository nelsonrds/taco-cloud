package nelson.tacocloud.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import nelson.tacocloud.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> readByUsername(String username);
}
