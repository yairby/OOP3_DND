package Board;

public class Tile {
    private char type;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    private int x;
    private int y;

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }
    public Tile(char c){
        x=-1;
        y=-1;
        type=c;
    }
 public Tile(){
        type=' ';
        x=0;
        y=0;
 }
    public Tile(char type, int x, int y) {
        this.type = type;
        this.x=x;
        this.y=y;
    }
    public double range(Tile tile){
        return Math.sqrt(Math.pow(tile.getX()- getX(),2)+Math.pow(tile.getY()-getY(),2));
    }
    public boolean checkrange(Tile tile,int r){
        return range(tile)<r;
    }
}
