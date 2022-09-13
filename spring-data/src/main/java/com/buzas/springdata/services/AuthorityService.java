package com.buzas.springdata.services;

import com.buzas.springdata.authorities.AuthorityDto;
import com.buzas.springdata.authorities.AuthorityDtoMapper;
import com.buzas.springdata.authorities.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityRepository authRepo;
    private final AuthorityDtoMapper mapper;

    public List<AuthorityDto> findAll() {
        return authRepo.findAll()
                .stream().map(mapper::map).toList();
    }

    public List<AuthorityDto> findAllByUserId(long id) {
        return authRepo.findAllByUserId(id)
                .stream().map(mapper::map).toList();
    }

}
