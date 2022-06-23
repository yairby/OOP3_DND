package Game;

import Board.GameBoard;
import Board.TileFactory;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Player;

import java.util.List;
import java.util.Scanner;

public class StartGame {
    public void StartGame(){
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
        List<Enemy> enemies= tileFactory.listEnemies();
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
        GameBoard gameBoard =new GameBoard(level1,player,enemies);
        System.out.println(gameBoard.printBoard());
        System.out.println(player.toString());

    }
}
