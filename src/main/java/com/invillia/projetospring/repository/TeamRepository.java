package com.invillia.projetospring.repository;

import com.invillia.projetospring.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
