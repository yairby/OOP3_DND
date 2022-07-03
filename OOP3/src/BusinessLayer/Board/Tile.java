package BusinessLayer.Board;

import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Player;
import BusinessLayer.VisitorPattern.Visited;
import BusinessLayer.VisitorPattern.Visitor;
import UI.MessageCallback;

import java.util.ArrayList;
import java.util.List;

public class Tile implements Visited {

    private char tileChar;
    private Position position;
    private MessageCallback msgCB=new MessageCallback();
    public Tile(){
        tileChar=' ';
        this.position=new Position(0,0);
    }
    public Tile(char tileChar, int x, int y) {
        this.tileChar = tileChar;
        this.position=new Position(x,y);
    }
    public Tile(char tileChar, Position p) {
        this.tileChar = tileChar;
        this.position=p;
    }

    public char getTileChar() {
        return tileChar;
    }
    public void setTileChar(char tileChar) {
        this.tileChar = tileChar;
    }

    public Position getPosition() {
        return position;
    }

    public int getY(){
        return position.getY();
    }

    public int getX(){
        return position.getX();
    }

    public void setY(int y){
        position.setY(y);
    }

    public void setX(int x){
        position.setX(x);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int range(Tile tile){
        return position.range(tile.getPosition());
    }
    public int range(Position p){
        return position.range(p);
    }
    public boolean checkrange(Tile tile,int r){
        return range(tile) < r;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public void call(String message){
        msgCB.call(message);
    }





}
