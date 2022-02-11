package MyCricketGame;
import java.util.*;

public class CricketGame{
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        Team team1 = getTeam(input, "1");
        Team team2 = getTeam(input, "2");

        team1.showTeam();
        team2.showTeam();

        beginTheMatch(team1,team2);
    }
    private static void beginTheMatch(Team team1,Team team2){
        GameController game=new GameController();

        String firstTeamToBat=game.decideFirstTeamToBat(team1,team2);
        int target=0,chase=0;
        if(team1.teamName==firstTeamToBat){
            getMatchResult(game,team1,team2);
        }else{
            getMatchResult(game,team2,team1);
        }
    }
    private static void getMatchResult(GameController game,Team team1,Team team2){
        int target,chase;
        target=game.batting(team1,team2,Integer.MAX_VALUE);
        chase=game.batting(team2,team1,target);
        if(target>chase){
            System.out.println(team1.teamName + " has won the game by " + String.valueOf(team1.score-team2.score) + " runs.");
        }else if(chase>target){
            System.out.println(team2.teamName + " has won the game by " + String.valueOf(10-team2.wickets) + " wickets.");
        }else{
            System.out.println("The match is drawn");
        }
        game.teamPerfomance(team1);
        game.teamPerfomance(team2);
    }
    private static Team getTeam(Scanner input, String teamNumber) {
        System.out.print("Please enter the name of team : " + teamNumber);
        String teamName = input.nextLine();
        Team team=new Team(teamName);
        team.createTeam();
        return team;
    }
}



