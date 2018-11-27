package pl.piotrjaniszewski.shoppingtips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piotrjaniszewski.shoppingtips.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByName(String name);
}
