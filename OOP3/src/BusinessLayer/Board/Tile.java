package BusinessLayer.Board;

import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Player;
import BusinessLayer.VisitorPattern.Visited;
import BusinessLayer.VisitorPattern.Visitor;
import UI.MessageCallback;

import java.util.ArrayList;
import java.util.List;

public class Tile implements Visited {
    private final GameTiles gameTiles;
    private char tileChar;
    private Position position;
    private MessageCallback msgCB=new MessageCallback();
    public Tile(GameTiles gameTiles){
        tileChar=' ';
        this.position=new Position(0,0);
        this.gameTiles=gameTiles;
    }
    public Tile(char tileChar, int x, int y,GameTiles gameTiles) {
        this.tileChar = tileChar;
        this.position=new Position(x,y);
        this.gameTiles=gameTiles;
    }
    public Tile(char tileChar, Position p,GameTiles gameTiles) {
        this.tileChar = tileChar;
        this.position=p;
        this.gameTiles=gameTiles;
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

    public List<Enemy> getEnemiesInRange(int range){
        List<Enemy> result=new ArrayList<>();
        List<Enemy> enemies=gameTiles.getEnemies();
        for (Enemy e:enemies) {
            if(this.range(e)<range){
                result.add(e);
            }
        }
        return result;
    }

    public boolean IsPlayerInRange(int range){
        return this.range(gameTiles.getPlayer()) < range;
    }

    public GameTiles getGameTiles(){
        return gameTiles;
    }


}
