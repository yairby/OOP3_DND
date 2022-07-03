package BusinessLayer.Board;

import java.util.List;

public class Position {
    private int x;
    private int y;



    public Position(int y, int x){
        this.x=x;
        this.y=y;
    }

    public int range(Position p){
       return (int) Math.sqrt(Math.pow(getX()- p.getX(),2)+Math.pow(getY()-p.getY(),2));
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Position Left(){
        return new Position(getY(),getX()-1);
    }
    public Position Right(){
        return new Position(getY(),getX()+1);
    }
    public Position Down(){
        return new Position(getY()+1,getX());
    }
    public Position Up(){
        return new Position(getY()-1,getX());
    }

    public Position checkMove(String move){
        if(move.equals("w")){
            return Up();
        }
        if(move.equals("s")){
            return Down();
        }
        if(move.equals("d")){
            return Right();
        }
        if(move.equals("a")){
            return Left();
        }
        return this;
    }

}
