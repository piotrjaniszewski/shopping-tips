package pl.piotrjaniszewski.shoppingtips.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Product> products = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "categoryAbove")
    private Set<Category> subcategories = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Category categoryAbove;

    public Category addSubcategory(Category subcategory){
        subcategory.setCategoryAbove(this);
        subcategories.add(subcategory);
        return this;
    }

    public Category addProduct(Product product){
        product.setCategory(this);
        products.add(product);
        return this;
    }
}
