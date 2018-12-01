package pl.piotrjaniszewski.shoppingtips.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Comment  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Integer rating=0;

    @ManyToOne(fetch = FetchType.EAGER)
    private Opinion opinion;

    @ManyToOne(fetch = FetchType.EAGER)
    private User creator;

    private Boolean deleted=false;
    private LocalDateTime creationDateTime = LocalDateTime.now();
}
