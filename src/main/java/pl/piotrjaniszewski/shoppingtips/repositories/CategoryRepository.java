package pl.piotrjaniszewski.shoppingtips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piotrjaniszewski.shoppingtips.domain.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(String name);
    Optional<Category> findById(Long id);
    Optional<Category> findAllByCategoryAbove(Category categoryAboce);
}
