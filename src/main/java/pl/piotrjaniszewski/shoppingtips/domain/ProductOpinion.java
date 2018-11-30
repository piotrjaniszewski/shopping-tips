package pl.piotrjaniszewski.shoppingtips.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class ProductOpinion extends Opinion{

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
}
