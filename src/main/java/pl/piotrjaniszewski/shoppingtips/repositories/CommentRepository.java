package pl.piotrjaniszewski.shoppingtips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piotrjaniszewski.shoppingtips.domain.Comment;
import pl.piotrjaniszewski.shoppingtips.domain.Opinion;
import pl.piotrjaniszewski.shoppingtips.domain.User;

import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Set<Comment> findAllByOpinion(Opinion opinion);
    Set<Comment> findAllByCreator(User user);
}
