package com.cmpe275.Lab2.controller;

import com.cmpe275.Lab2.entity.Address;
import com.cmpe275.Lab2.entity.Team;
import com.cmpe275.Lab2.service.PlayerService;
import com.cmpe275.Lab2.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @GetMapping("/{id}")
    public Team getTeam(@PathVariable long id) {
        Team t = teamService.getTeam(id);
        if (t == null) { throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        ); }
        return t;
    }

    @PostMapping("")
    public Team createTeam(@RequestParam(value = "name") String name,
                           @RequestParam(value = "description", required = false) String description,
                           @RequestParam(value = "street", required = false) String street,
                           @RequestParam(value = "city", required = false) String city,
                           @RequestParam(value = "state", required = false) String state,
                           @RequestParam(value = "zip", required = false) String zip,
                           @RequestParam(value = "players", required = false) List<Long> ids) {
        Team t = new Team();
        t.setPlayers(playerService.getPlayersById(ids));
        t.setDescription(description);
        Address address = new Address();
        address.setCity(city);
        address.setState(state);
        address.setStreet(street);
        address.setZip(zip);
        t.setAddress(address);
        t.setName(name);
        teamService.createTeam(t);
        return t;
    }

    @PutMapping("/{id}")
    public Team updateTeam(@PathVariable long id,
                           @RequestParam(value = "name") String name,
                           @RequestParam(value = "description", required = false) String description,
                           @RequestParam(value = "street", required = false) String street,
                           @RequestParam(value = "city", required = false) String city,
                           @RequestParam(value = "state", required = false) String state,
                           @RequestParam(value = "zip", required = false) String zip,
                           @RequestParam(value = "players", required = false) List<Long> ids) {
        Team t = new Team();
        t.setId(id);
        t.setPlayers(playerService.getPlayersById(ids));
        t.setDescription(description);
        Address address = new Address();
        address.setCity(city);
        address.setState(state);
        address.setStreet(street);
        address.setZip(zip);
        t.setAddress(address);
        t.setName(name);
        teamService.updateTeam(t);
        return t;
    }

    @DeleteMapping("/{id}")
    public Team deleteTeam(@PathVariable long id) {
        Team t = teamService.getTeam(id);
        if (t == null) { throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        ); }
        teamService.deleteTeamById(id);
        return t;
    }
}
