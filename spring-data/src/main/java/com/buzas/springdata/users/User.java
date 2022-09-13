package com.buzas.springdata.users;

import com.buzas.springdata.authorities.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

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

    @ManyToMany
    private List<Authority> authorities;

    public User(String username, String password, boolean enabled, List<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public User(String username, String password, List<Authority> authorities) {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + getUsername() + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
