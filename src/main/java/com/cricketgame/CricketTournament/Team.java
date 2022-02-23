package com.cricketgame.CricketTournament;

import java.util.Arrays;

public class Team {
  private String teamName;
  private int score = 0;
  private int wickets = 0;
  private int teamPoint = 0;
  private boolean isWinner = false;
  Player[] teamPlayers = new Player[11];

  public Team() {}

  public Team(String teamName) {
    this.teamName = teamName;
  }

  public String getTeamName() {
    return this.teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  public Player[] getTeamPlayers() {
    return teamPlayers;
  }

  public void setTeamPlayers(Player[] teamPlayers) {
    this.teamPlayers = teamPlayers;
  }

  public int getTeamPoint() {
    return teamPoint;
  }

  public void setTeamPoint(int teamPoint) {
    this.teamPoint = teamPoint;
  }

  public void incrementTeamPoint(int teamPoint) {
    this.teamPoint += teamPoint;
  }

  public boolean isWinner() {
    return isWinner;
  }

  public void setWinner(boolean winner) {
    isWinner = winner;
  }

  public int getScore() {
    return this.score;
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

  @Override
  public String toString() {
    return "Team{"
        + "teamName='"
        + teamName
        + '\''
        + ", score="
        + score
        + ", wickets="
        + wickets
        + ", teamPoint="
        + teamPoint
        + ", isWinner="
        + isWinner
        + ", teamPlayers="
        + Arrays.toString(teamPlayers)
        + '}';
  }
}
