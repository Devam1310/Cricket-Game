package com.cricketgame.CricketTournament.controller;

import com.cricketgame.CricketTournament.beans.MatchInformation;
import com.cricketgame.CricketTournament.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

  private final MatchService matchService;

  @Autowired
  public MatchController(MatchService matchService) {
    this.matchService = matchService;
  }

  @PostMapping
  public void save(@RequestBody final MatchInformation match) {
    matchService.save(match);
  }

  @GetMapping
  public Iterable<MatchInformation> show_matches() {
    return matchService.findAllMatches();
  }

  @GetMapping("/{Id}")
  public MatchInformation findById(@PathVariable final String Id) {
    return matchService.findById(Id);
  }

  @PostMapping("/{teamName1}/{teamName2}")
  public MatchInformation playMatch(
      @PathVariable final String teamName1, @PathVariable final String teamName2) {
    MatchInformation match = matchService.getScoreBoard(teamName1, teamName2);
    matchService.save(match);
    return match;
  }

  @GetMapping("/{teamName}")
  public List<MatchInformation> showAllMatchesOfTeam(@PathVariable final String teamName) {
    return matchService.showAllMatchesOfTeam(teamName);
  }
}
