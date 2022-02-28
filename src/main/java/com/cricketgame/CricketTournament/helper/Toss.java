package com.cricketgame.CricketTournament.helper;

public class Toss {

  private Toss() {}

  public static String decideFirstTeamToBat(String teamName1, String teamName2) {

    System.out.println("Time for the Toss");

    String teamToCallForToss = decideTeamToCallForToss(teamName1, teamName2);
    String otherTeam = teamName1;
    if (teamName1.equals(teamToCallForToss)) {
      otherTeam = teamName2;
    }

    String teamCall = getHeadOrTail();
    String teamChoiceToBatOrBowl = chooseBatOrBowl();

    System.out.println(teamToCallForToss + " has called " + teamCall);
    if (tossResult(teamCall)) {
      if (teamChoiceToBatOrBowl.equals("Bat")) {
        return teamToCallForToss;
      }
      return otherTeam;
    } else {
      if (teamChoiceToBatOrBowl.equals("Bat")) {
        return otherTeam;
      }
      return teamToCallForToss;
    }
  }

  private static String decideTeamToCallForToss(String teamName1, String teamName2) {
    if (Math.random() < 0.5) {
      return teamName1;
    }
    return teamName2;
  }

  private static String chooseBatOrBowl() {
    if (Math.random() < 0.5) {
      return "Bat";
    }
    return "Bowl";
  }

  private static boolean tossResult(String teamCall) {
    String coinSign = getHeadOrTail();

    System.out.println("And the coin signal is : " + coinSign);
    return teamCall.equals(coinSign);
  }

  private static String getHeadOrTail() {
    if (Math.random() < 0.5) {
      return "Head";
    }
    return "Tail";
  }
}
