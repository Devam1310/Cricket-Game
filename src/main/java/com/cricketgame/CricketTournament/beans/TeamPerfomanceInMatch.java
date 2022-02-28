package com.cricketgame.CricketTournament.beans;

public class TeamPerfomanceInMatch {
  private String teamId;
  private String teamName;
  private int score = 0;
  private int wickets = 0;
  private int overs = 0;
  private boolean isWinner = false;

  public TeamPerfomanceInMatch(String teamId, String teamName) {
    this.teamId = teamId;
    this.teamName = teamName;
  }

  public String getTeamId() {
    return teamId;
  }

  public void setTeamId(String teamId) {
    this.teamId = teamId;
  }

  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getWickets() {
    return wickets;
  }

  public void setWickets(int wickets) {
    this.wickets = wickets;
  }

  public int getOvers() {
    return overs;
  }

  public void setOvers(int overs) {
    this.overs = overs;
  }

  public boolean isWinner() {
    return isWinner;
  }

  public void setWinner(boolean winner) {
    isWinner = winner;
  }
}
