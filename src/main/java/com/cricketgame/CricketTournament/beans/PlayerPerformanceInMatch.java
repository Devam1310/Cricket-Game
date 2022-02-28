package com.cricketgame.CricketTournament.beans;

public class PlayerPerformanceInMatch {

  private String playerId;
  private String playerName;
  private int runsScored = 0;
  private int wickets = 0;
  private int ballsFaced = 0;
  private int runsGiven = 0;
  private int oversThrown = 0;

  public PlayerPerformanceInMatch(String playerId, String playerName) {
    this.playerId = playerId;
    this.playerName = playerName;
  }

  public String getPlayerId() {
    return playerId;
  }

  public String getPlayerName() {
    return playerName;
  }

  public int getRunsScored() {
    return runsScored;
  }

  public int getWickets() {
    return wickets;
  }

  public int getBallsFaced() {
    return ballsFaced;
  }

  public int getRunsGiven() {
    return runsGiven;
  }

  public int getOversThrown() {
    return oversThrown;
  }

  public void setPlayerId(String playerId) {
    this.playerId = playerId;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public void setRunsScored(int runsScored) {
    this.runsScored = runsScored;
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

  public void setOversThrown(int oversThrown) {
    this.oversThrown = oversThrown;
  }

  public void incrementRunsScored(int runsScored) {
    this.runsScored += runsScored;
  }

  public void incrementWickets(int wickets) {
    this.wickets += wickets;
  }

  public void incrementBallsFaced(int ballsFaced) {
    this.ballsFaced += ballsFaced;
  }

  public void incrementRunsGiven(int runsGiven) {
    this.runsGiven += runsGiven;
  }

  public void incrementOversThrown(int oversThrown) {
    this.oversThrown += oversThrown;
  }
}
