package com.cmpe275.Lab2.service;

import com.cmpe275.Lab2.entity.Player;
import com.cmpe275.Lab2.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository repository;

    public Player createPlayer(Player player) {
        return repository.save(player);
    }

    public Player getPlayerById(long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Player> getPlayersById(List<Long> ids) {
        return repository.findAllById(ids);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public boolean playerExistsById(long id) {
        return repository.existsById(id);
    }

    public Player updatePlayer(Player player) {
//        Player existingPlayer = repository.findById(player.getId()).orElse(null);
//        existingPlayer.setAddress(player.getAddress());
//        existingPlayer.setEmail(player.getEmail());
//        existingPlayer.setDescription(player.getDescription());
//        existingPlayer.setFirstname(player.getFirstname());
//        existingPlayer.setTeam(player.getTeam());
//        existingPlayer.setLastname(player.getLastname());
        return repository.save(player);
    }

    public void makeOpponents(Long id1, Long id2) {
        Player player1 = repository.findById(id1).orElse(null);
        Player player2 = repository.findById(id2).orElse(null);
        if (player1.getOpponents().contains(player2) || player2.getOpponents().contains(player1))
            throw new DataIntegrityViolationException("Players are already Opponents");
        player1.getOpponents().add(player2);
        player2.getOpponents().add(player1);
        repository.save(player1);
        repository.save(player2);
    }

    public void removeOpponents(Long id1, Long id2) {
        Player player1 = repository.findById(id1).orElse(null);
        Player player2 = repository.findById(id2).orElse(null);
        if (player1.getOpponents().contains(player2) && player2.getOpponents().contains(player1)) {
            player1.getOpponents().remove(player2);
            player2.getOpponents().remove(player1);
            repository.save(player1);
            repository.save(player2);
        } else {
            throw new DataIntegrityViolationException("Players are not Opponents");
        }
    }
}
