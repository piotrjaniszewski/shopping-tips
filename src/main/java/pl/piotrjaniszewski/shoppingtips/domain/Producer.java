package pl.piotrjaniszewski.shoppingtips.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "producer")
    private Set<ProducerOpinion> opinions;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "producer")
    private Set<Product> products;

    private Producer addProduct(Product product){
        product.setProducer(this);
        products.add(product);
        return this;
    }

    private Producer addOpinion(ProducerOpinion opinion){
        opinion.setProducer(this);
        opinions.add(opinion);
        return this;
    }
}
