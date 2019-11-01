package com.invillia.projetospring.repository;

import com.invillia.projetospring.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {

    //Optional<People> findByName(String name);
}
