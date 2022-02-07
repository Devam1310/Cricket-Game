import java.util.*;

class Team{
    public String teamName;
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
class GameController{
    boolean tossResult(){
        String teamCall,coinSign;
        if(Math.random()<0.5){
            teamCall="Head";
        }else{
            teamCall="Tail";
        }
        if(Math.random()<0.5){
            coinSign="Head";
        }else{
            coinSign="Tail";
        }
        System.out.println(teamCall);
        System.out.println("And the coin signal is : " + coinSign);
        if(teamCall==coinSign){
            //Team which has called for the toss won
            return true;
        }else{
            return false;
        }
    }
    String batFirst(Team T1, Team T2){
        System.out.println("Time for the Toss");
        if(Math.random()<0.5){
            // Team 1 Calls for the toss
            System.out.print(T1.teamName + " has called : ");
            if(tossResult()){
                System.out.println(T1.teamName + " won the toss.");
                // Team 1 has decided to bat first
                if(Math.random()<0.5){
                    //Team has decided to bat first
                    System.out.println(T1.teamName + " has decided to bat first");
                    return T1.teamName;
                }else{
                    //Team has decided to bowl first
                    System.out.println(T1.teamName + " has decided to bowl first");
                    return T2.teamName;
                }
            }
            else{
                System.out.println(T2.teamName + " won the toss.");
                if(Math.random()<0.5){
                    //Team has decided to bat first
                    System.out.println(T2.teamName + " has decided to bat first");
                    return T2.teamName;
                }else{
                    //Team has decided to bowl first
                    System.out.println(T2.teamName + " has decided to bowl first");
                    return T1.teamName;
                }
            }
        }else{
            // Team 2 Calls for the toss
            System.out.print(T2.teamName + " has called : ");
            if(tossResult()){
                System.out.println(T2.teamName + " won the toss.");
                // Team 1 has decided to bat first
                if(Math.random()<0.5){
                    //Team has decided to bat first
                    System.out.println(T2.teamName + " has decided to bat first");
                    return T2.teamName;
                }else{
                    //Team has decided to bowl first
                    System.out.println(T2.teamName + " has decided to bowl first");
                    return T1.teamName;
                }
            }
            else{
                System.out.println(T1.teamName + " won the toss.");
                if(Math.random()<0.5){
                    //Team has decided to bat first
                    System.out.println(T1.teamName + " has decided to bat first");
                    return T1.teamName;
                }else{
                    //Team has decided to bowl first
                    System.out.println(T1.teamName + " has decided to bat first");
                    return T2.teamName;
                }
            }
        }
    }
    Character balllResult(){
        Character[] possibleResult={'0','1','2','3','4','6','W'};
        Random random = new Random();
        int index = random.nextInt(possibleResult.length);
        return possibleResult[index];
    }
    int batting(Team bat,Team bow,int target)
    {
        if(target==0){
            target=Integer.MAX_VALUE;
        }
        int curRuns=0;
        int wicket=0,totWickets=10;
        int overs=0,totOvers=20;
        int striker=0,nonStriker=1,newBatsman=2;
        while(overs<totOvers && curRuns<target && wicket<totWickets){
            System.out.println(bat.teamPlayers[striker] + "is on the strike");
            int bowlerIndex=5 + (int)(Math.random() * ((10 - 5) + 1));
            System.out.println(bow.teamPlayers[bowlerIndex] + " is going to bowl over number : " + String.valueOf(overs+1));
            for(int balls=0;balls<6;balls++){
                Character result=balllResult();
                switch(result){

                }
            }
        }
    }
}
class CricketGame{
  public static void main(String[] args) {
      Scanner input=new Scanner(System.in);
      System.out.print("Please enter the name of team 1 : ");
      Team T1=new Team(input.nextLine());
      System.out.print("Please enter the name of team 2 : ");
      Team T2=new Team(input.nextLine());

      T1.createTeam();
      T2.createTeam();
      T1.showTeam();
      T2.showTeam();

      GameController G=new GameController();

      String firstTeamToBat=G.batFirst(T1,T2);
      int target=0;
      if(T1.teamName==firstTeamToBat){
        target=G.batting(T1,T2,target);
      }else{

      }
  }
}