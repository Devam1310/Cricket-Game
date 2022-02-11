package MyCricketGame;

import java.util.Scanner;
import java.util.*;

public class Team{
    public String teamName;
    public int score,wickets;
    public Team(String teamName){
        this.teamName=teamName;
    }
    class Player{
        public String playerName;
        public String playerRole;
        public int runs=0;
        public int wickets=0;
        public int ballsFaced=0;
        public int runsGiven=0;
        public int overs=0;

        public Player(String playerName){
            this.playerName=playerName;
        }
        public String getPlayerName() {
            return playerName;
        }
    }
    Player[] teamPlayers=new Player[11];
    void createTeam(){
        System.out.println("Please enter the player details of the team " + this.teamName);
        Scanner input=new Scanner(System.in);
        for(int i=0;i<11;i++){
            System.out.print("Please enter the name of player "+ String.valueOf(i+1) + ": ");
            this.teamPlayers[i]=new Player(input.nextLine());
            int flag=0;
            do{
                System.out.println("Please enter the role of "+ this.teamPlayers[i].getPlayerName());
                System.out.print(" Press 1 for BATSMAN, 2 for BOWLER and 3 for ALL ROUNDER: ");
                try{
                    int choice=input.nextInt();
                    input.nextLine();
                    if(choice==1){
                        this.teamPlayers[i].playerRole="Batsman";
                        flag=1;
                    }
                    else if(choice==2){
                        this.teamPlayers[i].playerRole="Bowler";
                        flag=1;
                    }
                    else if(choice==3){
                        this.teamPlayers[i].playerRole="All rounder";
                        flag=1;
                    }
                    else{
                        System.out.println("Please enter correct details");
                    }
                }catch (Exception e){
                    System.out.println("Please enter correct details.");
                }

            }while(flag==0);
        }
    }
    void showTeam(){
        System.out.println("Team Name: "+ this.teamName);
        System.out.println("Player Details of "+ this.teamName);
        System.out.println("Player Name                                Player Role");
        for(int i=0;i<11;i++){
            System.out.println(this.teamPlayers[i].playerName+"                              "+this.teamPlayers[i].playerRole);
        }
    }

}