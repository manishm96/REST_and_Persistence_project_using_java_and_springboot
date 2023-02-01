package com.cmpe275.Lab2.controller;

import com.cmpe275.Lab2.entity.Address;
import com.cmpe275.Lab2.entity.Player;
import com.cmpe275.Lab2.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable long id) {
        Player p = playerService.getPlayerById(id);
        if (p == null) { throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        ); }
        return playerFixer(p);
    }

    @PostMapping("")
    public Player createPlayer(@RequestParam(value = "firstname") String firstname,
                               @RequestParam(value = "lastname") String lastname,
                               @RequestParam(value = "email") String email,
                               @RequestParam(value = "description", required = false) String description,
                               @RequestParam(value = "street", required = false) String street,
                               @RequestParam(value = "city", required = false) String city,
                               @RequestParam(value = "state", required = false) String state,
                               @RequestParam(value = "zip", required = false) String zip,
                               @RequestParam(value = "team", required = false) Long team) {
        Player p = new Player();
        p.setLastname(lastname);
        p.setFirstname(firstname);
        p.setEmail(email);
        p.setDescription(description);
        Address address = new Address();
        address.setCity(city);
        address.setState(state);
        address.setStreet(street);
        address.setZip(zip);
        p.setAddress(address);
        playerService.createPlayer(p);
        return playerFixer(p);
    }

    @PutMapping ("/{id}")
    public Player updatePlayer(@PathVariable long id,
                               @RequestParam(value = "firstname") String firstname,
                               @RequestParam(value = "lastname") String lastname,
                               @RequestParam(value = "email") String email,
                               @RequestParam(value = "description", required = false) String description,
                               @RequestParam(value = "street", required = false) String street,
                               @RequestParam(value = "city", required = false) String city,
                               @RequestParam(value = "state", required = false) String state,
                               @RequestParam(value = "zip", required = false) String zip,
                               @RequestParam(value = "team", required = false) Long team) {
        Player p = getPlayer(id);
        if (p == null) if (p == null) { throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        ); }
        p.setLastname(lastname);
        p.setFirstname(firstname);
        p.setEmail(email);
        p.setDescription(description);
        Address address = new Address();
        address.setCity(city);
        address.setState(state);
        address.setStreet(street);
        address.setZip(zip);
        p.setAddress(address);
        playerService.updatePlayer(p);
        return playerFixer(playerService.getPlayerById(id));
    }

    @DeleteMapping("/{id}")
    public Player deletePlayer(@PathVariable long id) {
        Player p = playerService.getPlayerById(id);
        if (p == null) { throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        ); }
        playerService.deleteById(id);
        return playerFixer(p);
    }

    public Player playerFixer(Player p) {
        for (Player p1: p.getOpponents()) {
            p1.setOpponents(null);
        }
        return p;
    }
}
