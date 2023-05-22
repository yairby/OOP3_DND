package BusinessLayer.Board;

import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Player;
import BusinessLayer.VisitorPattern.Visitor;

public class Dead extends Tile{
    public Dead(char type, int y, int x){
        super(type,y,x);
    }
    public Dead(char type, Position p){
        super(type,p);
    }

}
