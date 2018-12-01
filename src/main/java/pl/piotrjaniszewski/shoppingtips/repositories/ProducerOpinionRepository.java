package pl.piotrjaniszewski.shoppingtips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piotrjaniszewski.shoppingtips.domain.Producer;
import pl.piotrjaniszewski.shoppingtips.domain.ProducerOpinion;

import java.util.Optional;

@Repository
public interface ProducerOpinionRepository extends JpaRepository<ProducerOpinion, Long> {
    Optional<ProducerOpinion> findByGrade(Byte grade);
    Optional<ProducerOpinion> findByProducer(Producer producer);
}
