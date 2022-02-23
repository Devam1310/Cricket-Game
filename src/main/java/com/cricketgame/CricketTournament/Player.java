package com.cricketgame.CricketTournament;

public class Player {
  private String playerName;
  private String playerRole;
  private int runs = 0;
  private int wickets = 0;
  private int ballsFaced = 0;
  private int runsGiven = 0;
  private int overs = 0;

  public Player() {}

  public Player(String playerName) {
    this.playerName = playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public String getPlayerRole() {
    return playerRole;
  }

  public void setPlayerRole(String playerRole) {
    this.playerRole = playerRole;
  }

  public int getRuns() {
    return runs;
  }

  public void incrementRuns(int runs) {
    this.runs += runs;
  }

  public int getWickets() {
    return wickets;
  }

  public void incrementWickets(int wickets) {
    this.wickets += wickets;
  }

  public int getBallsFaced() {
    return ballsFaced;
  }

  public void incrementBallsFaced(int ballsFaced) {
    this.ballsFaced += ballsFaced;
  }

  public int getRunsGiven() {
    return runsGiven;
  }

  public void incrementRunsGiven(int runsGiven) {
    this.runsGiven += runsGiven;
  }

  public int getOvers() {
    return overs;
  }

  public void incrementOvers(int overs) {
    this.overs += overs;
  }

  public String getPlayerName() {
    return this.playerName;
  }

  public void setRuns(int runs) {
    this.runs = runs;
  }

  public void setWickets(int wickets) {
    this.wickets = wickets;
  }

  public void setBallsFaced(int ballsFaced) {
    this.ballsFaced = ballsFaced;
  }

  public void setRunsGiven(int runsGiven) {
    this.runsGiven = runsGiven;
  }

  public void setOvers(int overs) {
    this.overs = overs;
  }

  @Override
  public String toString() {
    return "Player{"
        + "playerName='"
        + playerName
        + '\''
        + ", playerRole='"
        + playerRole
        + '\''
        + ", runs="
        + runs
        + ", wickets="
        + wickets
        + ", ballsFaced="
        + ballsFaced
        + ", runsGiven="
        + runsGiven
        + ", overs="
        + overs
        + '}';
  }
}
