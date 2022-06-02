package Game;

import Board.Tile;
import Board.TileFactory;
import BusinessLayer.Units.Players.Player;

import java.util.Scanner;

public class StartGame {
    public void StartGame(){

        System.out.println("Select player");
        TileFactory tileFactory=new TileFactory();
        for (Player t: tileFactory.listPlayers()) {
            System.out.println("1"+t.toString()+"     ");
        }


    }
}
