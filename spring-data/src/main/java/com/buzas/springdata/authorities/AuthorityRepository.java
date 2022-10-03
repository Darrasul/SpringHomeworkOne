package com.buzas.springdata.authorities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>, QuerydslPredicateExecutor<Authority> {
    @Query(value = """
        select * from authorities a join users_authorities ua on a.id = ua.authorities_id where users_id = :id
""", nativeQuery = true)
    public List<Authority> findAllByUserId(long id);
}
