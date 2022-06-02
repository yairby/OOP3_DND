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
//        gameTiles.initBoard(s);
    //    gameTiles.printBoard();
        System.out.println(s.substring(2,3).equals("\n"));
    }
}
