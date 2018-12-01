package pl.piotrjaniszewski.shoppingtips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piotrjaniszewski.shoppingtips.domain.Product;
import pl.piotrjaniszewski.shoppingtips.domain.ProductOpinion;
import pl.piotrjaniszewski.shoppingtips.domain.User;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductOpinionRepository extends JpaRepository<ProductOpinion, Long> {
    Optional<ProductOpinion> findByGrade(Byte grade);
    Set<ProductOpinion> findAllByProduct(Product product);
    Set<ProductOpinion> findAllByCreator(User user);
}
