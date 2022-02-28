package com.cricketgame.CricketTournament.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = "player_1")
@Setting(settingPath = "static/es-settings.json")
public class Player {

  @Id
  @Field(type = FieldType.Text)
  private String playerId;

  @Field(type = FieldType.Text)
  private String playerName;

  @Field(type = FieldType.Text)
  private String playerTeam;

  @Field(type = FieldType.Text)
  private String playerRole;

  @Field(type = FieldType.Integer)
  private int totalMatchesPlayed = 0;

  @Field(type = FieldType.Integer)
  private int runs = 0;

  @Field(type = FieldType.Integer)
  private int wickets = 0;

  @Field(type = FieldType.Integer)
  private int ballsFaced = 0;

  @Field(type = FieldType.Integer)
  private int runsGiven = 0;

  @Field(type = FieldType.Integer)
  private int overs = 0;

  public Player() {}

  public Player(String playerName) {
    this.playerName = playerName;
  }

  public String getPlayerId() {
    return playerId;
  }

  public void setPlayerId(String playerId) {
    this.playerId = playerId;
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

  public String getPlayerTeam() {
    return playerTeam;
  }

  public void setPlayerTeam(String playerTeam) {
    this.playerTeam = playerTeam;
  }

  public int getTotalMatchesPlayed() {
    return totalMatchesPlayed;
  }

  public void setTotalMatchesPlayed(int totalMatchesPlayed) {
    this.totalMatchesPlayed = totalMatchesPlayed;
  }

  public void incrementTotalMatchesPlayed(int totalMatchesPlayed) {
    this.totalMatchesPlayed += totalMatchesPlayed;
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
