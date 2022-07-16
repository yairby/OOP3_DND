package GameController;

import BusinessLayer.Board.GameTiles;
import BusinessLayer.Board.Tile;
import BusinessLayer.Board.Unit;
import BusinessLayer.Units.Enemies.Boss;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Enemies.Monster;
import BusinessLayer.Units.HeroicUnit;
import BusinessLayer.Units.Players.Player;

import javax.management.monitor.MonitorSettingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Move {
    private String [] SimpleMoves={"w","a","s","d","q"};
    private List<Enemy> enemies;
    private Player player;
    private GameTiles Board;
    public void movePlayer(GameTiles Board, Player player, String move){
        this.player=player;
        this.enemies=Board.getEnemies();
        this.Board=Board;
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
            neighbor=Board.getTileInPosition(u.getY(), u.getX()+1);
        }
        if(move.equals("a")){
            neighbor=Board.getTileInPosition(u.getY(), u.getX()-1);
        }
        return neighbor;
    }
    public void moveMonsters(GameTiles Board, List<Monster> monsters){
        for (Monster m:monsters) {
            moveMonster(Board,m);
        }
    }
    private void moveMonster(GameTiles Board, Monster m) {
        Tile neighbor=getNeighbor(Board,m,PickMove(m,SimpleMoves));
        if (neighbor!=null) {
            neighbor.accept(m);
            Board.UpdateLocationOfTile(m);
            Board.UpdateLocationOfTile(neighbor);
        }
    }

    private void moveMonster(GameTiles Board, Boss b) {
        Tile neighbor=getNeighbor(Board,b,PickMove(b,SimpleMoves));
        if (neighbor!=null) {
            neighbor.accept(b);
            Board.UpdateLocationOfTile(b);
            Board.UpdateLocationOfTile(neighbor);
        }
    }

    private String PickMove(Monster m, String [] moves){
        String selectedMove;
        if(m.range(player)<m.getVisionRange()){
            selectedMove=Chase(m);
        }else {
            Random rnd = new Random();
            int pickedMove = rnd.nextInt(moves.length);
            selectedMove = moves[pickedMove];
        }
        return selectedMove;
    }

    private String PickMove(Boss b, String [] moves){
        String selectedMove="";
        if(b.range(player)<b.getVisionRange()){
            if(b.getCombatTicks()==b.getAbilityFrequency()){
                b.UseSpecialAbility(enemies,player);
            }else {
                selectedMove=Chase(b);
            }
        }else {
            Random rnd = new Random();
            int pickedMove = rnd.nextInt(moves.length);
            selectedMove = moves[pickedMove];
        }
        return selectedMove;
    }

    private String Chase(Monster m){
        String bestMove="";
        double bestRange=Integer.MAX_VALUE;
        for (String move : SimpleMoves) {
            Tile t=getNeighbor(Board,m,move);
            if(t!=null){
                if(bestRange > t.range(m)){
                    bestRange=t.range(m);
                    bestMove=move;
                }
            }
        }
        return bestMove;
    }


}
