package com.cricketgame.CricketTournament.service;

import com.cricketgame.CricketTournament.beans.*;
import com.cricketgame.CricketTournament.helper.Toss;
import com.cricketgame.CricketTournament.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MatchService {

  private final MatchRepository matchRepository;
  private final TeamService teamService;
  private final PlayerService playerService;

  @Autowired
  public MatchService(
      MatchRepository matchRepository, TeamService teamService, PlayerService playerService) {
    this.matchRepository = matchRepository;
    this.teamService = teamService;
    this.playerService = playerService;
  }

  public void save(final MatchInformation match) {
    matchRepository.save(match);
  }

  public MatchInformation findById(final String Id) {
    return matchRepository.findById(Id).orElse(null);
  }

  public Iterable<MatchInformation> findAllMatches() {
    return matchRepository.findAll();
  }

  public List<MatchInformation> showAllMatchesOfTeam(final String teamName) {
    List<MatchInformation> matches = new ArrayList<MatchInformation>();
    matches.add(matchRepository.findAllByWinningTeamName(teamName));
    matches.add(matchRepository.findAllByLoosingTeamName(teamName));
    return matches;
  }

  public MatchInformation getScoreBoard(final String teamName1, final String teamName2) {

    Team team1 = teamService.findByTeamName(teamName1);
    Team team2 = teamService.findByTeamName(teamName2);

    Player[] team1Players = playerService.findByPlayerTeam(teamName1);
    Player[] team2Players = playerService.findByPlayerTeam(teamName2);

    TeamPerfomanceInMatch team1Performance =
        new TeamPerfomanceInMatch(team1.getTeamId(), team1.getTeamName());
    TeamPerfomanceInMatch team2Performance =
        new TeamPerfomanceInMatch(team2.getTeamId(), team2.getTeamName());

    String firstTeamToBat =
        Toss.decideFirstTeamToBat(team1Performance.getTeamName(), team2Performance.getTeamName());

    PlayerPerformanceInMatch[] team1PlayerPerformance =
        getPlayerPerformanceObjects(teamName1, team1Players);

    PlayerPerformanceInMatch[] team2PlayerPerformance =
        getPlayerPerformanceObjects(teamName2, team2Players);

    if (team1.getTeamName().equals(firstTeamToBat)) {
      startMatch(
          team1Performance, team1PlayerPerformance, team2Performance, team2PlayerPerformance);
    } else {
      startMatch(
          team2Performance, team2PlayerPerformance, team1Performance, team1PlayerPerformance);
    }

    updateTeamData(team1, team1Performance);
    updateTeamData(team2, team2Performance);
    updatePlayerData(team1Players, team1PlayerPerformance);
    updatePlayerData(team2Players, team2PlayerPerformance);

    if (team1Performance.isWinner())
      return generateMatchSummary(team1Performance, team2Performance, team1PlayerPerformance);
    return generateMatchSummary(team2Performance, team1Performance, team2PlayerPerformance);
  }

  private PlayerPerformanceInMatch[] getPlayerPerformanceObjects(
      String teamName, Player[] teamPlayers) {

    PlayerPerformanceInMatch[] teamPlayerPerformance =
        new PlayerPerformanceInMatch[teamPlayers.length];
    for (int player = 0; player < teamPlayers.length; player++) {
      teamPlayerPerformance[player] =
          new PlayerPerformanceInMatch(
              teamPlayers[player].getPlayerId(), teamPlayers[player].getPlayerName());
    }
    return teamPlayerPerformance;
  }

  private static void startMatch(
      TeamPerfomanceInMatch team1Performance,
      PlayerPerformanceInMatch[] team1PlayerPerformance,
      TeamPerfomanceInMatch team2Performance,
      PlayerPerformanceInMatch[] team2PlayerPerformance) {
    int target =
        teamBatting(
            team1Performance,
            team1PlayerPerformance,
            team2Performance,
            team2PlayerPerformance,
            Integer.MAX_VALUE);
    int chase =
        teamBatting(
            team2Performance,
            team2PlayerPerformance,
            team1Performance,
            team1PlayerPerformance,
            target);

    if (target > chase) {
      team1Performance.setWinner(true);
    } else {
      team2Performance.setWinner(true);
    }
  }

  private static int teamBatting(
      TeamPerfomanceInMatch battingTeam,
      PlayerPerformanceInMatch[] battingTeamPlayers,
      TeamPerfomanceInMatch bowlingTeam,
      PlayerPerformanceInMatch[] bowlingTeamPlayers,
      int target) {

    int curRuns = 0;
    int wicket = 0, totWickets = 10;
    int overs = 0, totOvers = 20;
    int striker = 0, nonStriker = 1, newBatsman = 2, temp;

    while (overs < totOvers && curRuns < target && wicket < totWickets) {

      int bowlerIndex = 5 + (int) (Math.random() * ((10 - 5) + 1));
      bowlingTeamPlayers[bowlerIndex].incrementOversThrown(1);
      for (int balls = 0; balls < 6; balls++) {
        battingTeamPlayers[striker].incrementBallsFaced(1);
        Character result = ballResult();
        if (result != 'W') {
          int runsOnThisDelivery = Character.getNumericValue(result);
          curRuns += runsOnThisDelivery;
          battingTeamPlayers[striker].incrementRunsScored(runsOnThisDelivery);
          bowlingTeamPlayers[bowlerIndex].incrementRunsGiven(runsOnThisDelivery);
          if (result == '1' || result == '3') {
            temp = nonStriker;
            nonStriker = striker;
            striker = temp;
          }
        } else {
          wicket += 1;
          bowlingTeamPlayers[bowlerIndex].incrementWickets(1);
          striker = newBatsman;
          newBatsman += 1;
        }
        if (wicket == 10 || curRuns > target) break;
      }
      overs += 1;
      temp = striker;
      striker = nonStriker;
      nonStriker = temp;
    }
    battingTeam.setOvers(overs);
    battingTeam.setScore(curRuns);
    battingTeam.setWickets(wicket);

    return curRuns;
  }

  private static Character ballResult() {
    Character[] possibleResult = {'0', '1', '2', '3', '4', '6', 'W'};
    Random random = new Random();
    int index = random.nextInt(possibleResult.length);
    return possibleResult[index];
  }

  private MatchInformation generateMatchSummary(
      TeamPerfomanceInMatch winner,
      TeamPerfomanceInMatch looser,
      PlayerPerformanceInMatch[] winningTeamPlayerPerformance) {
    MatchInformation match = new MatchInformation();

    match.setWinningTeamId(winner.getTeamId());
    match.setLoosingTeamId(looser.getTeamId());

    match.setWinningTeamName(winner.getTeamName());
    match.setLoosingTeamName(looser.getTeamName());

    match.setWinningTeamOvers(winner.getOvers());
    match.setLoosingTeamOvers(looser.getOvers());

    match.setWinningTeamScore(winner.getScore());
    match.setLoosingTeamScore(looser.getScore());

    match.setWinningTeamWickets(winner.getWickets());
    match.setLoosingTeamWickets(looser.getWickets());

    match.setManOfTheMatch(findHighestScorer(winningTeamPlayerPerformance));

    return match;
  }

  private static String findHighestScorer(PlayerPerformanceInMatch[] playerPerformance) {
    String highestRunScorerName = null;

    int highestRuns = playerPerformance[0].getRunsScored();
    highestRunScorerName = playerPerformance[0].getPlayerName();
    for (int player = 1; player <= 10; player++) {
      if (playerPerformance[player].getRunsScored() > highestRuns) {
        highestRuns = playerPerformance[player].getRunsScored();
        highestRunScorerName = playerPerformance[player].getPlayerName();
      }
    }
    return highestRunScorerName;
  }

  private void updateTeamData(Team team, TeamPerfomanceInMatch teamPerformance) {
    teamService.deleteTeam(team);
    team.incrementTotalMatchesPlayed(1);
    team.incrementTotalWins(teamPerformance.isWinner() ? 1 : 0);
    teamService.saveTeam(team);
  }

  private void updatePlayerData(
      Player[] teamPlayers, PlayerPerformanceInMatch[] teamPlayerPerformance) {
    for (int player = 0; player < teamPlayerPerformance.length; player++) {
      playerService.deletePlayer(teamPlayers[player]);
      teamPlayers[player].incrementTotalMatchesPlayed(1);
      teamPlayers[player].incrementOvers(teamPlayerPerformance[player].getOversThrown());
      teamPlayers[player].incrementRuns(teamPlayerPerformance[player].getRunsScored());
      teamPlayers[player].incrementWickets(teamPlayerPerformance[player].getWickets());
      teamPlayers[player].incrementBallsFaced(teamPlayerPerformance[player].getBallsFaced());
      teamPlayers[player].incrementRunsGiven(teamPlayerPerformance[player].getRunsGiven());
      playerService.savePlayer(teamPlayers[player]);
    }
  }
}
