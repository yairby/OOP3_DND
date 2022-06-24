package BusinessLayer.Board;

import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Player;
import GameController.TileFactory;

import java.util.ArrayList;
import java.util.List;

public class GameTiles {

   private Tile[][] Board;
   private Player player;
   private List<Enemy> enemies;

   private final int UPPERBOUND=0;
   private final int LOWERBOUND;
   private final int RIGHTBOUND;
   private final int LEFTBOUND=0;

    public GameTiles(String levelMap, Player p){
        String[] arr=levelMap.split("\\n");
        this.Board=new Tile[arr.length][arr[0].length()];
        this.enemies=new ArrayList<>();
        this.player=p;
        initBoard(arr);
        LOWERBOUND=arr.length-1;
        RIGHTBOUND=arr[0].length()-1;
    }

    public Tile[][] getBoard() {
        return Board;
    }


    public void initBoard(String[] arr){
        TileFactory factory=new TileFactory();
        for(int i=0;i<arr.length;i++){
            for(int j=0;j< arr[0].length();j++){
                Tile t;
                char type=arr[i].charAt(j);
                switch (type) {
                    case '@':
                        t = player;
                        break;
                    case '#':
                        t = new Wall(type,i, j);
                        break;
                    case '.':
                        t = new Empty(type,i, j);
                        break;
                    default:
                        Enemy enemy = factory.produceEnemy(type);
                        enemies.add(enemy);
                        t=enemy;
                }
                Board[i][j]=t;
                t.setPosition(new Position(i,j));
            }
        }
    }

    public void SetTileInPosition(Position p, Tile newTile){
        Board[p.getY()][p.getX()]=newTile;

    }
    public Tile getTileInPosition(Position p){
        return Board[p.getY()][p.getX()];
    }
    public Tile getTileInPosition(int y,int x){
        return Board[y][x];
    }
    public String printBoard(){
        String s="";
        for(int i = 0; i< getBoard().length; i++){
            for (int j = 0; j< getBoard()[i].length; j++){
                s=s+Board[i][j].getTileChar();
            }
            s=s+"\n";
        }
        return s;
    }

    public List<Enemy> getEnemies(){
        return enemies;
    }

    public int getUPPERBOUND() {
        return UPPERBOUND;
    }

    public int getLOWERBOUND() {
        return LOWERBOUND;
    }

    public int getLEFTBOUND() {
        return LEFTBOUND;
    }

    public int getRIGHTBOUND() {
        return RIGHTBOUND;
    }
}
