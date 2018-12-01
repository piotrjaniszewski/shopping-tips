package pl.piotrjaniszewski.shoppingtips.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public abstract class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Byte grade;
    private Integer rating=0;
    private String advantages;
    private String disadvantages;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "opinion")
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private User creator;

    private Boolean deleted=false;
    private LocalDateTime creationDateTime = LocalDateTime.now();

    public Opinion addComment(Comment comment){
        comment.setOpinion(this);
        comments.add(comment);
        return this;
    }
}
