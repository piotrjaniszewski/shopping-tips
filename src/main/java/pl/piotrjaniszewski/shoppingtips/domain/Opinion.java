package pl.piotrjaniszewski.shoppingtips.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public abstract class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Byte grade;

    private String advantages;
    private String disadvantages;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "opinion")
    private List<Comment> comments;

    public Opinion addComment(Comment comment){
        comment.setOpinion(this);
        comments.add(comment);
        return this;
    }
}
