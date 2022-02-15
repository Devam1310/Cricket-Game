package MyCricketGame;

import java.util.Scanner;


public class Team{
    public String teamName;
    public int score,wickets,teamPoints=0;
    public boolean isWinner=false;
    public Team(String teamName){
        this.teamName=teamName;
    }

    class Player{
        private final String playerName;
        private String playerRole;
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
        public void setPlayerRole(String playerRole){
            this.playerRole=playerRole;
        }
        public String getPlayerRole(){
            return playerRole;
        }
    }

    Player[] teamPlayers=new Player[11];
    void createTeam(){
        System.out.println("Please enter the player details of the team " + this.teamName);
        Scanner input=new Scanner(System.in);
        for(int i=0;i<11;i++){
            this.teamPlayers[i]=getPlayer(input,i+1);
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

    private Player getPlayer(Scanner input, int playerNumber)
    {
        System.out.print("Please enter the name of player "+ String.valueOf(playerNumber) + ": ");
        String playerName=input.nextLine();
        Player player=new Player(playerName);
        player.playerRole=getPlayerRole(input,playerName);
        return player;
    }

    private static String getPlayerRole(Scanner input,String playerName){
        System.out.println("Please enter the role of "+ playerName);
        int flag=0;
        do{
            System.out.print(" Press 1 for BATSMAN, 2 for BOWLER and 3 for ALL ROUNDER: ");
            try{
                int choice=input.nextInt();
                input.nextLine();
                if(choice==1){
                    return "Batsman";
                }
                else if(choice==2){
                    return "Bowler";
                }
                else if(choice==3){
                    return "All rounder";
                }
                else{
                    System.out.println("Please enter correct details");
                }
            }catch (Exception e){
                System.out.println("Please enter correct details.");
            }

        }while(flag==0);
        return "";
    }

}