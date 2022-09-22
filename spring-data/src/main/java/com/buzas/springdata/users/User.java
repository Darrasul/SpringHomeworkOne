package com.buzas.springdata.users;

import com.buzas.springdata.authorities.Authority;
import com.buzas.springdata.orders.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username must contains something")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "specify password")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Authority> authorities;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Order> orders;

    public User(String username, String password, boolean enabled, Set<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public User(String username, String password, Set<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = true;
        this.authorities = authorities;
    }

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = true;
    }

    public User() {
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }
}
