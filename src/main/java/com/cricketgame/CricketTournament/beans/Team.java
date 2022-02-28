package com.cricketgame.CricketTournament.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = "team_1")
@Setting(settingPath = "static/es-settings.json")
public class Team {

  @Id
  @Field(type = FieldType.Text)
  private String teamId;

  @Field(type = FieldType.Text)
  private String teamName;

  @Field(type = FieldType.Integer)
  private int totalMatchesPlayed = 0;

  @Field(type = FieldType.Integer)
  private int totalWins = 0;

  public Team() {}

  public String getTeamId() {
    return teamId;
  }

  public void setTeamId(String teamId) {
    this.teamId = teamId;
  }

  public Team(String teamName) {
    this.teamName = teamName;
  }

  public String getTeamName() {
    return this.teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  public int getTotalMatchesPlayed() {
    return totalMatchesPlayed;
  }

  public void setTotalMatchesPlayed(int totalMatchesPlayed) {
    this.totalMatchesPlayed = totalMatchesPlayed;
  }

  public int getTotalWins() {
    return totalWins;
  }

  public void setTotalWins(int totalWins) {
    this.totalWins = totalWins;
  }

  public void incrementTotalMatchesPlayed(int totalMatchesPlayed) {
    this.totalMatchesPlayed += totalMatchesPlayed;
  }

  public void incrementTotalWins(int totalWins) {
    this.totalWins += totalWins;
  }

  @Override
  public String toString() {
    return "Team{"
        + "teamId='"
        + teamId
        + '\''
        + ", teamName='"
        + teamName
        + '\''
        + ", totalMatchesPlayed="
        + totalMatchesPlayed
        + ", totalWins="
        + totalWins
        + '}';
  }
}
