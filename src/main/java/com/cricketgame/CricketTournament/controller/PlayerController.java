package com.cricketgame.CricketTournament.controller;

import com.cricketgame.CricketTournament.beans.Player;
import com.cricketgame.CricketTournament.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
  private final PlayerService playerService;

  @Autowired
  public PlayerController(PlayerService playerService) {
    this.playerService = playerService;
  }

  @PostMapping
  public void save_team(@RequestBody final Player player) {
    playerService.savePlayer(player);
  }

  @GetMapping
  public Iterable<Player> show_players() {
    return playerService.findAllPlayers();
  }

  @GetMapping("/{Id}")
  public Player findById(@PathVariable final String Id) {
    return playerService.findById(Id);
  }

  @GetMapping("/{teamName}")
  public Player[] teamPlayers(@PathVariable final String teamName) {
    return playerService.findByPlayerTeam(teamName);
  }
}
