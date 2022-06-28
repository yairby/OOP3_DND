package BusinessLayer.Board;

import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Player;
import BusinessLayer.VisitorPattern.Visitor;

public class Empty extends Tile{
    private char type;

    public Empty(char type, int x, int y){
        super(type,x,y);
    }

    public void accept(Visitor v){
        v.visit(this);
    }

}
