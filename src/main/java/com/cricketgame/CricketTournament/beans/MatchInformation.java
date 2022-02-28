package com.cricketgame.CricketTournament.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = "match_1")
@Setting(settingPath = "static/es-settings.json")
public class MatchInformation {

  @Id
  @Field(type = FieldType.Text)
  private String matchId;

  @Field(type = FieldType.Keyword)
  private String winningTeamId;

  @Field(type = FieldType.Keyword)
  private String loosingTeamId;

  @Field(type = FieldType.Text)
  private String winningTeamName;

  @Field(type = FieldType.Text)
  private String loosingTeamName;

  @Field(type = FieldType.Integer)
  private int winningTeamScore;

  @Field(type = FieldType.Integer)
  private int loosingTeamScore;

  @Field(type = FieldType.Integer)
  private int winningTeamWickets;

  @Field(type = FieldType.Integer)
  private int loosingTeamWickets;

  @Field(type = FieldType.Integer)
  private int winningTeamOvers;

  @Field(type = FieldType.Integer)
  private int loosingTeamOvers;

  @Field(type = FieldType.Text)
  private String manOfTheMatch;

  public MatchInformation() {}

  public String getMatchId() {
    return matchId;
  }

  public void setMatchId(String matchId) {
    this.matchId = matchId;
  }

  public String getWinningTeamId() {
    return winningTeamId;
  }

  public void setWinningTeamId(String winningTeamId) {
    this.winningTeamId = winningTeamId;
  }

  public String getLoosingTeamId() {
    return loosingTeamId;
  }

  public void setLoosingTeamId(String loosingTeamId) {
    this.loosingTeamId = loosingTeamId;
  }

  public String getWinningTeamName() {
    return winningTeamName;
  }

  public void setWinningTeamName(String winningTeamName) {
    this.winningTeamName = winningTeamName;
  }

  public String getLoosingTeamName() {
    return loosingTeamName;
  }

  public void setLoosingTeamName(String loosingTeamName) {
    this.loosingTeamName = loosingTeamName;
  }

  public int getWinningTeamScore() {
    return winningTeamScore;
  }

  public void setWinningTeamScore(int winningTeamScore) {
    this.winningTeamScore = winningTeamScore;
  }

  public int getLoosingTeamScore() {
    return loosingTeamScore;
  }

  public void setLoosingTeamScore(int loosingTeamScore) {
    this.loosingTeamScore = loosingTeamScore;
  }

  public int getWinningTeamWickets() {
    return winningTeamWickets;
  }

  public void setWinningTeamWickets(int winningTeamWickets) {
    this.winningTeamWickets = winningTeamWickets;
  }

  public int getLoosingTeamWickets() {
    return loosingTeamWickets;
  }

  public void setLoosingTeamWickets(int loosingTeamWickets) {
    this.loosingTeamWickets = loosingTeamWickets;
  }

  public int getWinningTeamOvers() {
    return winningTeamOvers;
  }

  public void setWinningTeamOvers(int winningTeamOvers) {
    this.winningTeamOvers = winningTeamOvers;
  }

  public int getLoosingTeamOvers() {
    return loosingTeamOvers;
  }

  public void setLoosingTeamOvers(int loosingTeamOvers) {
    this.loosingTeamOvers = loosingTeamOvers;
  }

  public String getManOfTheMatch() {
    return manOfTheMatch;
  }

  public void setManOfTheMatch(String manOfTheMatch) {
    this.manOfTheMatch = manOfTheMatch;
  }
}
