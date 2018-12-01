package pl.piotrjaniszewski.shoppingtips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piotrjaniszewski.shoppingtips.domain.Producer;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
    Optional<Producer> findByName(String name);
    Set<Producer> findAllByNameContaining(String name);
}
