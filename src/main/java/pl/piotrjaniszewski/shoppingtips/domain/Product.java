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
    private Set<ProductOpinion> opinions = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private User creator;

    private LocalDateTime creationDateTime = LocalDateTime.now();
    private LocalDateTime lastUpdateTime = LocalDateTime.now();

    private boolean accepted = false;

    public Product addOpinion(ProductOpinion opinion){
        opinion.setProduct(this);
        opinions.add(opinion);
        return this;
    }

    public void setCategory(Category category){
        category.addProduct(this);
        this.category=category;
    }

    public void setProducer(Producer producer){
        producer.addProduct(this);
        this.producer=producer;
    }

    public void updateTime(){
        lastUpdateTime = LocalDateTime.now();
    }
}
