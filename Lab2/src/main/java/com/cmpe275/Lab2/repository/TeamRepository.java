package com.cmpe275.Lab2.repository;

import com.cmpe275.Lab2.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
