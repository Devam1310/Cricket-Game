package com.cricketgame.CricketTournament.service;

import com.cricketgame.CricketTournament.beans.Team;
import com.cricketgame.CricketTournament.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
  private final TeamRepository teamRepository;

  @Autowired
  public TeamService(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  public void saveTeam(final Team team) {
    teamRepository.save(team);
  }

  public void deleteTeam(final Team team) {
    teamRepository.delete(team);
  }

  public Team findById(final String Id) {
    return teamRepository.findById(Id).orElse(null);
  }

  public Team findByTeamName(String teamName) {
    return teamRepository.findByTeamName(teamName);
  }

  public Iterable<Team> findAllTeams() {
    return teamRepository.findAll();
  }
}
