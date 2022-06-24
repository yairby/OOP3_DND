package GameController;

import Board.GameTiles;
import Board.Tile;
import BusinessLayer.Units.Players.Player;
import DataAccessLayer.LevelLoader;
import UI.MessageCallback;

import java.util.*;
import java.util.stream.Collectors;

public class GameManager {
    private Scanner scanner=new Scanner(System.in);
    private static final int maxLevel=4;
    private MessageCallback msgCB;
    public GameManager(MessageCallback msgCB){
        this.msgCB=msgCB;
    }
    public void StartGame(int level){
        TileFactory tileFactory=new TileFactory();
        //loading the players
        int j=0;
        for (Player t: tileFactory.listPlayers()) {
            msgCB.call(j+1+"."+t.toString()+"     ");
            j++;
        }
        //choosing the player
        msgCB.call("Choose a hero from the list:");
        boolean isChosen=false;
        int choose=-1;
        while (!isChosen){
            try {
                choose = scanner.nextInt();
                if(choose > 0 && choose <= j) {
                    isChosen = true;
                }else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please choose a hero number from the list.");
                scanner.next();
            }
        }

        //inform the user about the selected player
        List<Player> players= tileFactory.listPlayers();
        Player player= players.get(choose-1);
        msgCB.call("You have selected: "+player.getName());

        //load the first level
        LevelLoader levels=new LevelLoader();
        String levelMap= LevelLoader.LoadLevel(level).stream().collect(Collectors.joining("\n"));
        GameTiles gameTiles=new GameTiles(levelMap,player);
        msgCB.call(gameTiles.printBoard());
        msgCB.call(player.toString());

        //start game action
        boolean win=false,passLevel=false,death=false;
        while (!win && !death){
            while (!passLevel && !death){
                String s=scanner.next();
                Move m=new Move();
                m.move(gameTiles,player,s);
                m.moveMonsters(gameTiles,player);
                msgCB.call(gameTiles.printBoard());
                msgCB.call(player.toString());
                if(player.getHealth().getAmount()==0){
                    death=true;
                }
                else if(gameTiles.getEnemies().isEmpty()){
                    passLevel=true;
                    //player.setLevel(player.getLevel()+1);
                }
                else if(player.getHealth().getAmount()<=0)
                    death=true;
            }
            if(!death) {
                player.setLevel(player.getLevel() + 1);
                if (player.getLevel() == 5) {
                    win = true;
                }
                else{
                    msgCB.call("You dead! \n Do you want to start a new game? \n Press 'Y' to restart or any other key to exit");
                    String startNew=scanner.nextLine();
                    if(startNew.equals("Y")||startNew.equals("y")){
                        msgCB.call("Starting new game!");
                        StartGame(1);
                    }else{
                        msgCB.call("Thanks for playing our game! Goodbye!");
                        System.exit(0);
                    }
                }
            }

        }
        if(death){
            gameTiles.getBoardController()[player.getY()][player.getX()]=new Tile('X',player.getX(),player.getY());
            System.out.println(gameTiles.printBoard());
            System.out.println("game over");
        }
        else{
            System.out.println("Win");
        }

    }
    

}
