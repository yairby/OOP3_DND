package BusinessLayer.Board;

import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Player;

public class Dead extends Tile{
    public Dead(char type, int x, int y){
        super(type,x,y);
    }
    public Dead(char type, Position p){
        super(type,p);
    }

    @Override
    public void visit(Empty emptyTile){}
    @Override
    public void visit(Wall wallTile){}
    @Override
    public void visit(Player playerTile){}
    @Override
    public void visit(Enemy enemyTile){}
    @Override
    public void visit(Tile tile){}
}
