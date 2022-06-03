package UI;

import Board.GameTiles;
import Game.StartGame;

import java.util.Random;

public class GameController {
    public static void main(String[] args) {
        StartGame startGame=new StartGame();
       // startGame.StartGame();
        GameTiles gameTiles=new GameTiles(2);
        String s="##\n##";
        String[] strings=s.split("\\n");
        for (int i=0;i<strings.length;i++){
            System.out.println(strings[i]);
        }
       gameTiles.initBoard(s);
       gameTiles.printBoard();

    }
}
