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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String name;
    private String LastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "creator")
    private Set<Opinion> opinions = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "creator")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "creator")
    private Set<Product> products = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "creator")
    private Set<Producer> producer = new HashSet<>();

    private Boolean banned = false;
    private LocalDateTime creationDateTime = LocalDateTime.now();

    public User addRole(Role role){
        role.getUsers().add(this);
        roles.add(role);
        return this;
    }
}