package pl.piotrjaniszewski.shoppingtips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piotrjaniszewski.shoppingtips.domain.Category;
import pl.piotrjaniszewski.shoppingtips.domain.Producer;
import pl.piotrjaniszewski.shoppingtips.domain.Product;
import pl.piotrjaniszewski.shoppingtips.domain.User;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    Optional<Product> findByBarcode(String barcode);
    Optional<Product> findByCategory(Category category);
    Optional<Product> findByProducer(Producer producer);
    Set<Product> findAllByAccepted(Boolean accepted);
    Set<Product> findAllByNameContaining(String name);
    Set<Product> findAllByDescriptionContaining(String desc);
    Set<Product> findAllByCreator(User user);
}
