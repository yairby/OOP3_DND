package BusinessLayer.Board;

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

//    public Position Left(){
//
//    }
//    public Position Right(){
//
//    }
//    public Position Down(){
//
//    }
//    public Position Up(){
//
//    }

}
