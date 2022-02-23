package com.cricketgame.CricketTournament;

import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class GameController {
  private static Team[] tournamentTeams;

  @PostMapping(value = "/createteam")
  public static Team[] createteam(@RequestBody Team[] teams) {

    for (int team = 0; team < teams.length; team++) {
      System.out.println(teams[team]);
    }

    tournamentTeams = teams;

    return teams;
  }

  @RequestMapping("/tournament")
  public TournamentMatches[] beginTournament() {

    int numberOfTeams = tournamentTeams.length;
    int totalNumberOfMatches = numberOfTeams * ((numberOfTeams - 1) * 2);
    TournamentMatches[] matches = new TournamentMatches[totalNumberOfMatches];

    int match = 0;
    for (int team1 = 0; team1 < numberOfTeams; team1++) {
      for (int team2 = 0; team2 < numberOfTeams; team2++) {
        if (team1 != team2) {
          matches[match] = beginTheMatch(tournamentTeams[team1], tournamentTeams[team2]);
          clearMatchPerformance(tournamentTeams[team1], tournamentTeams[team2]);
          match += 1;
        }
      }
    }
    viewTournamentPointsTable(tournamentTeams, numberOfTeams);
    return matches;
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

  private static String decideTeamToCallForToss(Team team1, Team team2) {
    if (Math.random() < 0.5) {
      return team1.getTeamName();
    }
    return team2.getTeamName();
  }

  private static String chooseBatOrBowl() {
    if (Math.random() < 0.5) {
      return "Bat";
    }
    return "Bowl";
  }

  String decideFirstTeamToBat(Team team1, Team team2) {

    System.out.println("Time for the Toss");

    String teamToCallForToss = decideTeamToCallForToss(team1, team2);
    String otherTeam = team1.getTeamName();
    if (team1.getTeamName().equals(teamToCallForToss)) {
      otherTeam = team2.getTeamName();
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

  private static Character ballResult() {
    Character[] possibleResult = {'0', '1', '2', '3', '4', '6', 'W'};
    Random random = new Random();
    int index = random.nextInt(possibleResult.length);
    return possibleResult[index];
  }

  int batting(Team bat, Team bow, int target) {
    int curRuns = 0;
    int wicket = 0, totWickets = 10;
    int overs = 0, totOvers = 20;
    int striker = 0, nonStriker = 1, newBatsman = 2, temp;

    while (overs < totOvers && curRuns < target && wicket < totWickets) {
      System.out.println(
          "Score of "
              + bat.getTeamName()
              + " is : "
              + String.valueOf(curRuns)
              + "/"
              + String.valueOf(wicket));
      System.out.println(bat.teamPlayers[striker].getPlayerName() + " is on the strike");
      int bowlerIndex = 5 + (int) (Math.random() * ((10 - 5) + 1));
      bow.teamPlayers[bowlerIndex].incrementOvers(1);
      System.out.println(
          bow.teamPlayers[bowlerIndex].getPlayerName()
              + " is going to bowl over number : "
              + String.valueOf(overs + 1));
      for (int balls = 0; balls < 6; balls++) {
        bat.teamPlayers[striker].incrementBallsFaced(1);
        Character result = ballResult();
        System.out.println(
            "over : "
                + String.valueOf(overs)
                + " ball : "
                + String.valueOf(balls + 1)
                + " result : "
                + result);
        if (result != 'W') {
          curRuns =
              incrementRuns(
                  bat, bow, bowlerIndex, striker, Character.getNumericValue(result), curRuns);
          if (result == '1' || result == '3') {
            temp = nonStriker;
            nonStriker = striker;
            striker = temp;
          }
        } else {
          wicket += 1;
          bow.teamPlayers[bowlerIndex].incrementWickets(1);
          System.out.println(
              bat.teamPlayers[striker].getPlayerName()
                  + " is out for "
                  + bat.teamPlayers[striker].getRuns()
                  + " runs.");
          striker = newBatsman;
          newBatsman += 1;
          if (wicket < 10)
            System.out.println("New batsman is : " + bat.teamPlayers[striker].getPlayerName());
        }
        if (wicket == 10 || curRuns > target) break;
      }
      overs += 1;
      temp = striker;
      striker = nonStriker;
      nonStriker = temp;
    }
    bat.setScore(curRuns);
    bat.setWickets(wicket);
    System.out.println(
        bat.getTeamName()
            + " has scored "
            + String.valueOf(curRuns)
            + "/"
            + String.valueOf(wicket)
            + " in "
            + String.valueOf(overs)
            + " overs.");
    return curRuns;
  }

  private static int incrementRuns(
      Team bat, Team bow, int bowlerIndex, int striker, int run, int curRuns) {
    bat.teamPlayers[striker].incrementRuns(run);
    bow.teamPlayers[bowlerIndex].incrementRunsGiven(run);
    curRuns += run;
    return curRuns;
  }

  void teamPerformance(Team T) {
    System.out.println("Team : " + T.getTeamName());
    System.out.println(
        "Score : " + String.valueOf(T.getScore()) + "/" + String.valueOf(T.getWickets()));
    System.out.println("Perfomance of players of : " + T.getTeamName());
    System.out.println("Player Name      Runs Balls Overs Wickets RunsGiven");
    for (int i = 0; i <= 10; i++) {
      System.out.println(
          T.teamPlayers[i].getPlayerName()
              + " "
              + String.valueOf(T.teamPlayers[i].getRuns())
              + " "
              + String.valueOf(T.teamPlayers[i].getBallsFaced())
              + " "
              + String.valueOf(T.teamPlayers[i].getOvers())
              + " "
              + String.valueOf(T.teamPlayers[i].getWickets())
              + " "
              + String.valueOf(T.teamPlayers[i].getRunsGiven()));
    }
  }

  private static void viewTournamentPointsTable(Team[] tournamentTeams, int numberOfTeams) {
    System.out.println("Team Name         Points");
    for (int team = 0; team < numberOfTeams; team++) {
      System.out.println(
          tournamentTeams[team].getTeamName()
              + "  "
              + String.valueOf(tournamentTeams[team].getTeamPoint()));
    }
  }

  private static TournamentMatches beginTheMatch(Team team1, Team team2) {
    GameController game = new GameController();

    String firstTeamToBat = game.decideFirstTeamToBat(team1, team2);

    if (team1.getTeamName() == firstTeamToBat) {
      getMatchResult(game, team1, team2);
    } else {
      getMatchResult(game, team2, team1);
    }
    return new TournamentMatches(team1, team2);
  }

  private static void getMatchResult(GameController game, Team team1, Team team2) {
    int target, chase;
    target = game.batting(team1, team2, Integer.MAX_VALUE);
    chase = game.batting(team2, team1, target);
    if (target > chase) {
      System.out.println(
          team1.getTeamName()
              + " has won the game by "
              + String.valueOf(team1.getScore() - team2.getScore())
              + " runs.");
      team1.setWinner(true);
      team1.incrementTeamPoint(2);
    } else if (chase > target) {
      System.out.println(
          team2.getTeamName()
              + " has won the game by "
              + String.valueOf(10 - team2.getWickets())
              + " wickets.");
      team2.incrementTeamPoint(2);
      team2.setWinner(true);
    }

    // game.teamPerfomance(team1);
    // game.teamPerfomance(team2);
  }

  private static void clearMatchPerformance(Team team1, Team team2) {
    clearTeamDetails(team1);
    clearTeamDetails(team2);
  }

  private static void clearTeamDetails(Team team) {
    team.setWinner(false);
    team.setScore(0);
    team.setWickets(0);
    for (int player = 0; player < 11; player++) {
      clearPlayerDetails(team.teamPlayers[player]);
    }
  }

  private static void clearPlayerDetails(Player player) {
    player.setWickets(0);
    player.setBallsFaced(0);
    player.setOvers(0);
    player.setRuns(0);
    player.setRunsGiven(0);
  }
}
