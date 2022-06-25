package BusinessLayer.Board;

import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Player;

public class Wall extends Tile{
    private char type;

    public Wall(char type, int x, int y){
        super(type,x,y);
    }
    @Override
    public void visit(Empty emptyTile){}
    @Override
    public void visit(Wall wallTile){}
    @Override
    public void visit(Player playerTile){
        System.out.println("Just For Testing: YOU BUMPED INTO A WALL!");
    }
    @Override
    public void visit(Enemy enemyTile){
        System.out.println("Just For Testing: "+enemyTile.getTileChar()+" BUMPED INTO A WALL!");
    }
    @Override
    public void visit(Tile tile){}
}
