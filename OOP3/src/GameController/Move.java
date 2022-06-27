package GameController;

import BusinessLayer.Board.GameTiles;
import BusinessLayer.Board.Tile;
import BusinessLayer.Board.Unit;
import BusinessLayer.Units.Enemies.Boss;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Enemies.Monster;
import BusinessLayer.Units.HeroicUnit;
import BusinessLayer.Units.Players.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Move {
    private enum Moves {w,s,a,d}
    public void movePlayer(GameTiles Board, Player player, String move){
        Tile neighbor=getNeighbor(Board,player,move);
        if (neighbor!=null) {
            neighbor.accept(player);
            Board.UpdateLocationOfTile(player);
            Board.UpdateLocationOfTile(neighbor);
        }
        if(move.equals("e")){
            player.UseSpecialAbility(Board.getEnemies(),Board.getPlayer());
        }
    }

    private Tile getNeighbor(GameTiles Board, Unit u, String move){
        Tile neighbor=null;
        if(move.equals("w")){
            neighbor=Board.getTileInPosition(u.getY()-1, u.getX());
        }
        if(move.equals("s")){
            neighbor=Board.getTileInPosition(u.getY()+1, u.getX());
        }
        if(move.equals("d")){
            neighbor=Board.getTileInPosition(u.getY(), u.getX()-1);
        }
        if(move.equals("a")){
            neighbor=Board.getTileInPosition(u.getY(), u.getX()+1);
        }
        return neighbor;
    }
    private void moveMonsters(GameTiles Board, Monster m, String move) {
        Tile neighbor=getNeighbor(Board,m,move);
        if (neighbor!=null) {
            neighbor.accept(m);
            Board.UpdateLocationOfTile(m);
            Board.UpdateLocationOfTile(neighbor);
        }
    }

    private void moveMonsters(GameTiles Board, Boss b, String move) {
        Tile neighbor=getNeighbor(Board,b,move);
        if (neighbor!=null) {
            neighbor.accept(b);
            Board.UpdateLocationOfTile(b);
            Board.UpdateLocationOfTile(neighbor);
        }
        if(move.equals("e")){
            b.UseSpecialAbility(Board.getEnemies(),Board.getPlayer());
        }
    }
    private boolean contains(String move){
        for (Moves s:Moves.values()) {
            if(s.name().equals(move)){
                return true;
            }
        }
        return false;
    }

}
