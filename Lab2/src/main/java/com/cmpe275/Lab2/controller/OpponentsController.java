package com.cmpe275.Lab2.controller;

import com.cmpe275.Lab2.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/opponents")
public class OpponentsController {

    @Autowired
    PlayerService playerService;

    @PutMapping("/{id1}/{id2}")
    public String addOpponent(@PathVariable long id1, @PathVariable long id2) {
        if (!playerService.playerExistsById(id1) || !playerService.playerExistsById(id2)) { throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        ); }
        playerService.makeOpponents(id1, id2);
        return "Opponent Added";
    }

    @DeleteMapping("/{id1}/{id2}")
    public String removeOpponent(@PathVariable long id1, @PathVariable long id2) {
        if (!playerService.playerExistsById(id1) || !playerService.playerExistsById(id2)) { throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        ); }
        playerService.removeOpponents(id1, id2);
        return "Opponent Removed";
    }
}
