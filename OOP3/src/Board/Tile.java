package Board;

public class Tile {
    private char type;
    private Position position;
    public Tile(){
        type=' ';
        this.position=new Position(0,0);
    }
    public Tile(char type, int x, int y) {
        this.type = type;
        this.position=new Position(x,y);
    }
    public Tile(char type, Position p) {
        this.type = type;
        this.position=p;
    }

    public char getType() {
        return type;
    }
    public void setType(char type) {
        this.type = type;
    }

    public Position getPosition() {
        return position;
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
}
