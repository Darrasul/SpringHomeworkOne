package com.buzas.springdata.users;

import com.buzas.springdata.authorities.AuthorityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "Username must contains something")
    private String username;

    @NotBlank(message = "specify password")
    private String password;

    private boolean enabled;

    public UserDto(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = true;
    }

    public UserDto() {
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
