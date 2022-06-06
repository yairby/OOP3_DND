package Game;

import Board.GameTiles;
import Board.Tile;
import BusinessLayer.Units.Enemies.Monster;
import BusinessLayer.Units.Players.Player;
import BusinessLayer.Units.Unit;
import BusinessLayer.Units.Wall;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Move {
    public void move(GameTiles gameTiles, Unit unit,String move){

        if(move.equals("w")){
            if(!(gameTiles.getBoardController()[unit.getY() - 1][unit.getX()].getType()=='#')) {
                gameTiles.getBoardController()[unit.getY()][unit.getX()] = new Tile('.', unit.getX(), unit.getY());
                gameTiles.getBoardController()[unit.getY() - 1][unit.getX()] = unit;
                unit.setY(unit.getY() - 1);
            }

        }
        else if(move.equals("s")){
            if(!(gameTiles.getBoardController()[unit.getY() + 1][unit.getX()].getType()=='#')) {

                gameTiles.getBoardController()[unit.getY()][unit.getX()] = new Tile('.', unit.getX(), unit.getY());
                gameTiles.getBoardController()[unit.getY() + 1][unit.getX()] = unit;
                unit.setY(unit.getY() + 1);
            }
        }
        else if(move.equals("a")){
            if(!(gameTiles.getBoardController()[unit.getY() ][unit.getX()-1].getType()=='#')) {

                    gameTiles.getBoardController()[unit.getY()][unit.getX()] = new Tile('.', unit.getX(), unit.getY());
                    gameTiles.getBoardController()[unit.getY()][unit.getX() - 1] = unit;
                    unit.setX(unit.getX() - 1);
                }
        }
        else if(move.equals("d")){
            if(!(gameTiles.getBoardController()[unit.getY() ][unit.getX()+1].getType()=='#')) {

                gameTiles.getBoardController()[unit.getY()][unit.getX()] = new Tile('.', unit.getX(), unit.getY());
                gameTiles.getBoardController()[unit.getY()][unit.getX() + 1] = unit;
                unit.setX(unit.getX() + 1);
            }
        }
        else if(move.equals("q")){

        }
        else if(move.equals("e")){

        }
        else {
            System.out.println("invalid move");
        }
    }
    public void moveMonsters(GameTiles gameTiles){
        ArrayList<Monster> arrayList=new ArrayList<Monster>();
        for(int i=0;i<gameTiles.getBoardController().length;i++){
            for(int j=0;j<gameTiles.getBoardController()[i].length;j++){
                Tile tile=gameTiles.getBoardController()[i][j];
                if(tile.getType()=='s'||tile.getType()=='k'||tile.getType()=='q'||tile.getType()=='z'||tile.getType()=='b'||tile.getType()=='g'||tile.getType()=='w'||tile.getType()=='M'||tile.getType()=='C'||tile.getType()=='K'){

                }
            }
        }
    }
    public void moveMonster(Monster m, Player player){
        double range=m.range(player);
        if(range<m.getVisionRange()){

        }
        else{
            Random r=new Random();
            int i=r.nextInt(4);
            if(i==0){

            }
            if(i==1){

            }
            if(i==2){

            }
            if(i==3){

            }
        }

    }

}
