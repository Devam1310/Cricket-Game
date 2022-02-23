package com.cricketgame.CricketTournament;

public class TournamentMatches {
  private Team copyTeam1;
  private Team copyTeam2;

  TournamentMatches(Team team1, Team team2) {
    this.copyTeam1 = new Team(team1.getTeamName());
    this.copyTeam2 = new Team(team2.getTeamName());
    setTeamDetails(this.copyTeam1, team1);
    setTeamDetails(this.copyTeam2, team2);
  }

  public Team getCopyTeam1() {
    return copyTeam1;
  }

  public void setCopyTeam1(Team copyTeam1) {
    this.copyTeam1 = copyTeam1;
  }

  public Team getCopyTeam2() {
    return copyTeam2;
  }

  public void setCopyTeam2(Team copyTeam2) {
    this.copyTeam2 = copyTeam2;
  }

  private static void setTeamDetails(Team copyTeam, Team team) {
    copyTeam.setWickets(team.getWickets());
    copyTeam.setScore(team.getScore());
    copyTeam.setWinner(team.isWinner());
    copyTeam.setTeamPoint(team.getTeamPoint());
    for (int player = 0; player < 11; player++) {
      copyTeam.teamPlayers[player] = setPlayerDetails(team.teamPlayers[player]);
    }
  }

  private static Player setPlayerDetails(Player player) {
    Player copyPlayer = new Player(player.getPlayerName());
    copyPlayer.setPlayerRole(player.getPlayerRole());
    copyPlayer.setRunsGiven(player.getRunsGiven());
    copyPlayer.setRuns(player.getRuns());
    copyPlayer.setOvers(player.getOvers());
    copyPlayer.setBallsFaced(player.getBallsFaced());
    copyPlayer.setWickets(player.getWickets());
    return copyPlayer;
  }
}
