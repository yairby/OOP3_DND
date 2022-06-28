package BusinessLayer.Board;

import BusinessLayer.ObserverPattern.Tickable;
import BusinessLayer.ObserverPattern.Ticker;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Player;
import GameController.TileFactory;

import java.util.*;
import java.util.function.Supplier;

public class GameTiles implements Ticker {

   private Tile[][] Board;
   private Player player;
   private List<Enemy> enemies;

   private List<Tickable> gameEntities;

    public GameTiles(String levelMap, Player p){
        String[] arr=levelMap.split("\\n");
        this.Board=new Tile[arr.length][arr[0].length()];
        this.enemies=new ArrayList<>();
        this.player=p;
        this.gameEntities=new LinkedList<>();
        initBoard(arr);
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
                        gameEntities.add(player);
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
                        gameEntities.add(enemy);
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

    public Player getPlayer(){
        return player;
    }


    public void UpdateLocationOfTile(Tile t) {
        Board[t.getY()][t.getX()]=t;
    }

    @Override
    public void notifyTickables() {
        for (Tickable t: gameEntities) {
            t.onTick();
        }
    }

//    public Tile getNeighbor(char c, Position p) {
//        Map<Character, Supplier<Tile>> tails = new HashMap<>() {
//            {
//                put('a', () -> gameBoard.getTileInPosition(p.Left()));
//                put('d', () -> gameBoard.getTileInPosition(p.Right()));
//                put('s', () -> gameBoard.getTileInPosition(p.Down()));
//                put('w', () -> gameBoard.getTileInPosition(p.Up()));
//                put('q', () -> gameBoard.getTileInPosition(p));
//            }
//        };
//        return tails.get(c).get();
//    }




}
