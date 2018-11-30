package pl.piotrjaniszewski.shoppingtips.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String barcode;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Producer producer;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductOpinion> opinions;

    private LocalDateTime date;
    private boolean accepted;

    public Product(){
        date = LocalDateTime.now();
        accepted = false;
    }

    public Product addOpinion(ProductOpinion opinion){
        opinion.setProduct(this);
        opinions.add(opinion);
        return this;
    }
}
