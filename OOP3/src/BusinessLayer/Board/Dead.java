package BusinessLayer.Board;

import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Player;
import BusinessLayer.VisitorPattern.Visitor;

public class Dead extends Tile{
    public Dead(char type, int x, int y,GameTiles gameTiles){
        super(type,x,y,gameTiles);
    }
    public Dead(char type, Position p,GameTiles gameTiles){
        super(type,p,gameTiles);
    }

}
