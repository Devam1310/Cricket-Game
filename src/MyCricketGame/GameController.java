package MyCricketGame;

import java.util.Random;

public class GameController{
    private static boolean tossResult(String teamCall){
        String coinSign=getHeadOrTail();

        System.out.println("And the coin signal is : " + coinSign);
        return teamCall.equals(coinSign);
    }
    private static String getHeadOrTail(){
        if(Math.random()<0.5){
            return "Head";
        }
        return "Tail";
    }
    private static String decideTeamToCallForToss(Team team1,Team team2){
        if(Math.random()<0.5){
            return team1.teamName;
        }
        return team2.teamName;
    }
    private static String chooseBatOrBowl(){
        if(Math.random()<0.5){
            return "Bat";
        }
        return "Bowl";
    }

    String decideFirstTeamToBat(Team team1, Team team2){

        System.out.println("Time for the Toss");

        String teamToCallForToss=decideTeamToCallForToss(team1,team2);
        String otherTeam=team1.teamName;
        if(team1.teamName.equals(teamToCallForToss)){
            otherTeam=team2.teamName;
        }

        String teamCall=getHeadOrTail();
        String teamChoiceToBatOrBowl=chooseBatOrBowl();

        System.out.println(teamToCallForToss + " has called " + teamCall);
        if(tossResult(teamCall)){
            if(teamChoiceToBatOrBowl.equals("Bat")){
                return teamToCallForToss;
            }
            return otherTeam;
        }else{
            if(teamChoiceToBatOrBowl.equals("Bat")){
                return otherTeam;
            }
            return teamToCallForToss;
        }

    }
    private static Character ballResult(){
        Character[] possibleResult={'0','1','2','3','4','6','W'};
        Random random = new Random();
        int index = random.nextInt(possibleResult.length);
        return possibleResult[index];
    }

    int batting(Team bat,Team bow,int target)
    {
        int curRuns=0;
        int wicket=0,totWickets=10;
        int overs=0,totOvers=20;
        int striker=0,nonStriker=1,newBatsman=2,temp;

        while(overs<totOvers && curRuns<target && wicket<totWickets){
            System.out.println("Score of " + bat.teamName + " is : " + String.valueOf(curRuns)+ "/" + String.valueOf(wicket));
            System.out.println(bat.teamPlayers[striker].getPlayerName() + " is on the strike");
            int bowlerIndex=5 + (int)(Math.random() * ((10 - 5) + 1));
            bow.teamPlayers[bowlerIndex].overs+=1;
            System.out.println(bow.teamPlayers[bowlerIndex].getPlayerName() + " is going to bowl over number : " + String.valueOf(overs+1));
            for(int balls=0;balls<6;balls++){
                bat.teamPlayers[striker].ballsFaced+=1;
                Character result=ballResult();
                System.out.println("over : "+String.valueOf(overs)+" ball : "+String.valueOf(balls+1)+ " result : "+ result);
                if(result!='W'){
                    curRuns=incrementRuns(bat,bow,bowlerIndex,striker,Character.getNumericValue(result),curRuns);
                    if(result=='1' || result=='3'){
                        temp=nonStriker;
                        nonStriker=striker;
                        striker=temp;
                    }
                }else{
                    wicket+=1;
                    bow.teamPlayers[bowlerIndex].wickets+=1;
                    System.out.println(bat.teamPlayers[striker].getPlayerName() + " is out for " + bat.teamPlayers[striker].runs + " runs.");
                    striker=newBatsman;
                    newBatsman+=1;
                    if(wicket<10)System.out.println("New batsman is : " + bat.teamPlayers[striker].getPlayerName());
                }
                if(wicket==10 || curRuns>target)break;

            }
            overs+=1;
            temp=striker;
            striker=nonStriker;
            nonStriker=temp;
        }
        bat.score=curRuns;
        bat.wickets=wicket;
        System.out.println(bat.teamName + " has scored " + String.valueOf(curRuns) + "/" + String.valueOf(wicket)
                + " in " + String.valueOf(overs) + " overs.");
        return curRuns;
    }
    private static int incrementRuns(Team bat,Team bow,int bowlerIndex, int striker,int run,int curRuns){
        bat.teamPlayers[striker].runs+=run;
        bow.teamPlayers[bowlerIndex].runsGiven+=run;
        curRuns+=run;
        return curRuns;
    }
    void teamPerfomance(Team T){
        System.out.println("Team : " + T.teamName);
        System.out.println("Score : "+ String.valueOf(T.score) + "/" + String.valueOf(T.wickets));
        System.out.println("Perfomance of players of : "+ T.teamName);
        System.out.println("Player Name      Runs Balls Overs Wickets RunsGiven");
        for(int i=0;i<=10;i++){
            System.out.println(T.teamPlayers[i].getPlayerName() + " " + String.valueOf(T.teamPlayers[i].runs) + " "
                    + String.valueOf(T.teamPlayers[i].ballsFaced) + " " + String.valueOf(T.teamPlayers[i].overs) + " "
                    + String.valueOf(T.teamPlayers[i].wickets) + " " + String.valueOf(T.teamPlayers[i].runsGiven));
        }
    }
}
