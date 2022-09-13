package com.buzas.springdata.authorities;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AuthorityDtoMapper {
    AuthorityDto map(Authority authority);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", ignore = true)
    Authority map(AuthorityDto authorityDto);
}
