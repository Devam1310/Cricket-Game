package MyCricketGame;
import java.util.*;

public class CricketGame{
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        System.out.print("Please enter the total number of teams: ");
        int numberOfTeams=input.nextInt();
        input.nextLine();
        int totalNumberOfMatches = numberOfTeams*((numberOfTeams-1)*2);

        Team[] tournamentTeams=new Team[numberOfTeams];
        TournamentMatches[] matches=new TournamentMatches[totalNumberOfMatches];


        for(int team=0;team<numberOfTeams;team++){
            System.out.println("Enter the details of team : "+String.valueOf(team+1));
            tournamentTeams[team]=getTeam(input,team+1);
        }

        beginTournament(tournamentTeams,numberOfTeams,matches);

    }
    private static void beginTournament(Team[] tournamentTeams,int numberOfTeams,TournamentMatches[] matches){

        int match=0;
        for(int team1=0;team1<numberOfTeams;team1++){
            for(int team2=0;team2<numberOfTeams;team2++){
                if(team1!=team2){
                    beginTheMatch(tournamentTeams[team1],tournamentTeams[team2]);
                    matches[match]=new TournamentMatches(tournamentTeams[team1],tournamentTeams[team2]);
                    match+=1;
                }
            }
        }
        viewTournamentPointsTable(tournamentTeams,numberOfTeams);
    }
    private static void viewTournamentPointsTable(Team[] tournamentTeams,int numberOfTeams){
        System.out.println("Team Name         Points");
        for(int team=0;team<numberOfTeams;team++){
            System.out.println(tournamentTeams[team].teamName + "  " + String.valueOf(tournamentTeams[team].teamPoints));
        }
    }
    private static void beginTheMatch(Team team1,Team team2){
        GameController game=new GameController();

        String firstTeamToBat=game.decideFirstTeamToBat(team1,team2);

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
            team1.isWinner=true;
            team1.teamPoints+=2;
        }else if(chase>target){
            System.out.println(team2.teamName + " has won the game by " + String.valueOf(10-team2.wickets) + " wickets.");
            team2.teamPoints+=2;
            team2.isWinner=true;
        }else{
            System.out.println("The match is drawn");
            team1.teamPoints+=1;
            team2.teamPoints+=1;
        }

        //game.teamPerfomance(team1);
        //game.teamPerfomance(team2);
    }
    private static Team getTeam(Scanner input, int teamNumber) {
        System.out.print("Please enter the name of team " + String.valueOf(teamNumber) + " : ");
        String teamName = input.nextLine();
        Team team=new Team(teamName);
        team.createTeam();
        return team;
    }
}



