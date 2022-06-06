package Game;

import Board.GameTiles;
import Board.Tile;
import Board.TileFactory;
import BusinessLayer.Units.Enemies.Monster;
import BusinessLayer.Units.Players.Player;
import BusinessLayer.Units.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        String level1="#################################################\n" +
                "#....s...###..........................#.........#\n" +
                "#........#B#....##..........##........#.........#\n" +
                "#........#......##..........##........#.........#\n" +
                "#........#............................#.........#\n" +
                "#........#............................#.........#\n" +
                "#........#......##..........##........#.........#\n" +
                "#........#......##s........k##........#.........#\n" +
                "#........#s.................##.......k#.........#\n" +
                "#@...........................Q.................q#\n" +
                "#........#s.................##.......k#.........#\n" +
                "#........#......##s........k##........#.........#\n" +
                "#........#......##..........##........#.........#\n" +
                "#........#............................#.........#\n" +
                "#........#............................#.........#\n" +
                "#........#......##..........##........#.........#\n" +
                "#........#B#....##..........##........#.........#\n" +
                "#....s...###..........................#.........#\n" +
                "#################################################";
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
                System.out.println(gameTiles.printBoard());
                death=true;
            }
            if(level==5){
                win=true;
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
