package BusinessLayer.Board;

import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Player;
import BusinessLayer.VisitorPattern.Visitor;

public class Wall extends Tile{
    private char type;

    public Wall(char type, int x, int y){
        super(type,x,y);
    }
    @Override
    public void accept(Visitor v){
        call("Just For Testing: BUMPED INTO A WALL!");
    }
}
