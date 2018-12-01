package pl.piotrjaniszewski.shoppingtips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piotrjaniszewski.shoppingtips.domain.Producer;
import pl.piotrjaniszewski.shoppingtips.domain.ProducerOpinion;
import pl.piotrjaniszewski.shoppingtips.domain.User;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProducerOpinionRepository extends JpaRepository<ProducerOpinion, Long> {
    Optional<ProducerOpinion> findByGrade(Byte grade);
    Set<ProducerOpinion> findAllByProducer(Producer producer);
    Set<ProducerOpinion> findAllByCreator(User user);
}
