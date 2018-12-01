package pl.piotrjaniszewski.shoppingtips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piotrjaniszewski.shoppingtips.domain.Role;
import pl.piotrjaniszewski.shoppingtips.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String userName);
    Optional<User> findByRolesContaining(Role role);//todo sprawdzic czy bangladesz
}
