package com.buzas.springdata.authorities;

import com.buzas.springdata.users.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AuthorityDto {

    private Long id;

    private String authority;

    public AuthorityDto(String authority) {
        this.authority = authority;
    }

    public AuthorityDto() {
    }

    @Override
    public String toString() {
        return "AuthorityDto{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                '}';
    }
}
