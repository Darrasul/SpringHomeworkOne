package com.buzas.springdata.services;

import com.buzas.springdata.authorities.Authority;
import com.buzas.springdata.authorities.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityRepository authRepo;

    public List<Authority> findAll() {
        return authRepo.findAll();
    }

    public List<Authority> findAllByUserId(long id) {
        return authRepo.findAllByUserId(id);
    }

}
