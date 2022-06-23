package Game;

import Board.GameTiles;
import Board.Tile;
import Board.TileFactory;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Enemies.Monster;
import BusinessLayer.Units.Players.Player;
import BusinessLayer.Units.Unit;
import DataAccessLayer.DataAccessLayer;

import java.util.*;
import java.util.function.Supplier;

public class GameController {
    private Scanner scanner=new Scanner(System.in);
    public void play(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Select player");
        TileFactory tileFactory=new TileFactory();
        int j=0;
        for (Player t: tileFactory.listPlayers()) {
            System.out.println(j+1+"."+t.toString()+"     ");
            j++;
        }
        boolean a=false;
        int i=-1;
        while (!a){
            String  s=scanner.nextLine();
            try {
                i=Integer.valueOf(s);
                if(i<j+1&&i>=1){
                    a=true;
                }
                else {
                    System.out.println("choose a hero from the list");
                }
            } catch (Exception e) {
                System.out.println("invalid input");
            }
        }

        List<Player> players= tileFactory.listPlayers();
        Player player= players.get(i-1);
        System.out.println("you have selected");
        System.out.println(player.getName());
        DataAccessLayer data=new DataAccessLayer();
        List<String> file=data.readAllLines("C:\\Users\\yairb\\Downloads\\FinalAIproject\\OOP3_DND\\OOP3\\src\\DataAccessLayer\\level1.txt");
        String level1="";
        for(int u=0;u<file.size();u++){
            level1=level1+file.get(u)+"\n";
        }
        GameTiles gameTiles=new GameTiles(level1,player);
        System.out.println(gameTiles.printBoard());
        System.out.println(player.toString());
        int level=1;
        boolean win=false,passLevel=false,death=false;
        while (!win&&!death){
            while (!passLevel&&!death){

                String s=scanner.nextLine();
                Move m=new Move();
                m.move(gameTiles,player,s);
                m.moveMonsters(gameTiles,player);
                System.out.println(gameTiles.printBoard());
                System.out.println(player.toString());
                if(player.getHealth().getAmount()==0){
                    death=true;
                }
                else if(gameTiles.getEnemies().isEmpty()){
                    passLevel=true;
                    player.setLevel(player.getLevel()+1);

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
                    file=data.readAllLines("C:\\Users\\yairb\\Downloads\\FinalAIproject\\OOP3_DND\\OOP3\\src\\DataAccessLayer\\level"+player.getLevel()+".txt");
                    level1="";
                    for(int u=0;u<file.size();u++){
                        level1=level1+file.get(u)+"\n";
                    }
                    gameTiles=new GameTiles(level1,player);
                    System.out.println(gameTiles.printBoard());
                    System.out.println(player.toString());
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
