package com.invillia.projetospring.service;

import com.invillia.projetospring.model.Team;
import com.invillia.projetospring.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    Team team = new Team();

    public void save(Team team) {
        teamRepository.save(team);
    }

    public void delete(Long id) {
        teamRepository.deleteById(id);
    }

    public void update(Team team) {
        Team team1 = teamRepository.findById(team.getId()).orElseThrow(IllegalArgumentException::new);
        team1.setName(team.getName());
//        people1.setCreatedAt(people.getCreatedAt());
        teamRepository.save(team1);
    }

    public List<Team> print() {
        return teamRepository.findAll();
    }


    public Optional<Team> findById(long id) {

        return teamRepository.findById(id);
    }
}
