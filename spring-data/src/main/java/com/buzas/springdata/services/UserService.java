package com.buzas.springdata.services;

import com.buzas.springdata.users.User;
import com.buzas.springdata.users.UserDto;
import com.buzas.springdata.users.UserDtoMapper;
import com.buzas.springdata.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final UserDtoMapper mapper;
    private final PasswordEncoder encoder;

    public List<UserDto> findAll() {
        return userRepo.findAll()
                .stream().map(mapper::map).toList();
    }

    public UserDto findById(long id) {
        return mapper.map(userRepo.findById(id)
                .orElseThrow(() -> new FindException("No such user with id: " + id)));
    }

    public org.springframework.security.core.userdetails.User findUserByUsernameSecure(String username) {
        return userRepo.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("ADMIN"))
                )).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public void save(UserDto userDto) {
        userRepo.save(mapper.map(userDto, encoder));
    }

    public void deleteById(long id) {
        userRepo.deleteById(id);
    }
}
