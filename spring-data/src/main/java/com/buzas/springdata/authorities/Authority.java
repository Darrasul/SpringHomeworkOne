package com.buzas.springdata.authorities;

import com.buzas.springdata.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String authority;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;

    public Authority(String authority, Set<User> users) {
        this.authority = authority;
        this.users = users;
    }

    public Authority(String authority) {
        this.authority = authority;
    }

    public Authority() {
    }

    @Override
    public String toString() {
        return authority.substring(5) + "(" + id + ")";
    }
}
