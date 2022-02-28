package com.cricketgame.CricketTournament.controller;

import com.cricketgame.CricketTournament.beans.Team;
import com.cricketgame.CricketTournament.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
  private final TeamService teamService;

  @Autowired
  public TeamController(TeamService teamService) {
    this.teamService = teamService;
  }

  @PostMapping
  public void save_team(@RequestBody final Team team) {
    teamService.saveTeam(team);
  }

  @GetMapping
  public Iterable<Team> show_teams() {
    return teamService.findAllTeams();
  }

  @GetMapping("/{Id}")
  public Team findById(@PathVariable final String Id) {
    return teamService.findById(Id);
  }
}
