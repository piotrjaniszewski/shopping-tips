package pl.piotrjaniszewski.shoppingtips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piotrjaniszewski.shoppingtips.domain.Product;
import pl.piotrjaniszewski.shoppingtips.domain.ProductOpinion;

import java.util.Optional;

@Repository
public interface ProductOpinionRepository extends JpaRepository<ProductOpinion, Long> {
    Optional<ProductOpinion> findByGrade(Byte grade);
    Optional<ProductOpinion> findByProduct(Product product);
}
