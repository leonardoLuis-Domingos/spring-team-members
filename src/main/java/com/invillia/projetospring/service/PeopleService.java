package com.invillia.projetospring.service;

import com.invillia.projetospring.exception.UserNotFoundException;
import com.invillia.projetospring.model.People;
import com.invillia.projetospring.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    People people = new People();

    public void save(People people) {
        peopleRepository.save(people);
    }

    public void delete(Long id) {
        peopleRepository.deleteById(id);
    }

    public void update(People people) {
        People people1 = peopleRepository.findById(people.getId()).orElseThrow(IllegalArgumentException::new);
        people1.setName(people.getName());
//        people1.setCreatedAt(people.getCreatedAt());
        peopleRepository.save(people1);
    }

    public List<People> print() {
        return peopleRepository.findAll();
    }

    public Optional<People> findById(long id) {

        return peopleRepository.findById(id);
    }
}
