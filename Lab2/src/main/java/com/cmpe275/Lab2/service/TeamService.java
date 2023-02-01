package com.cmpe275.Lab2.service;

import com.cmpe275.Lab2.entity.Team;
import com.cmpe275.Lab2.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team getTeam(long id) { return teamRepository.findById(id).orElse(null); }

    public void deleteTeamById(long id) {
        teamRepository.deleteById(id);
    }

    public Team updateTeam(Team team) {
        Team existingTeam = teamRepository.findById(team.getId()).orElse(null);
        existingTeam.setAddress(team.getAddress());
        existingTeam.setDescription(team.getDescription());
        existingTeam.setName(team.getName());
        existingTeam.setPlayers(team.getPlayers());
        return teamRepository.save(existingTeam);
    }
}
