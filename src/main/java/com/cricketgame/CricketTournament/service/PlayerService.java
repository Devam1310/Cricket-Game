package com.cricketgame.CricketTournament.service;

import com.cricketgame.CricketTournament.beans.Player;
import com.cricketgame.CricketTournament.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

  private final PlayerRepository playerRepository;

  @Autowired
  public PlayerService(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  public void savePlayer(final Player player) {
    playerRepository.save(player);
  }

  public void deletePlayer(final Player player) {
    playerRepository.delete(player);
  }

  public Player findById(final String Id) {
    return playerRepository.findById(Id).orElse(null);
  }

  public Player[] findByPlayerTeam(final String teamName) {
    return playerRepository.findByPlayerTeam(teamName);
  }

  public Iterable<Player> findAllPlayers() {
    return playerRepository.findAll();
  }
}
