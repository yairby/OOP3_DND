package Board;

import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Player;
import BusinessLayer.Units.Wall;
import GameController.TileFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class GameTiles {

   private Tile[][] BoardController;

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    private ArrayList<Enemy> enemies;


    public GameTiles(Tile[][] boardController) {
        this.BoardController=boardController;
    }

    public Tile[][] getBoardController() {
        return BoardController;
    }

    public void setBoardController(Tile[][] boardController) {
        BoardController = boardController;
    }
    public void setTile(Tile t){

    }
    public GameTiles(int num) {

        this.BoardController=new Tile[num][num];

    }
    public GameTiles(String s,Player heroType){
        String[] arr=s.split("\\n");
        this.BoardController=new Tile[arr.length][arr[0].length()];
        initBoard(arr,heroType);
        enemies=initEnemies(BoardController);
    }
    public ArrayList<Enemy> initEnemies(Tile[][] boardController){
        ArrayList<Enemy> enemies=new ArrayList<Enemy>();
        for(int i=0;i<boardController.length;i++){
            for(int j=0;j<boardController[i].length;j++){
                if(boardController[i][j] instanceof Enemy){
                    Enemy e=(Enemy) boardController[i][j];
                    enemies.add(e);
                }
            }

        }
        return enemies;
    }
    public void initBoard(String[] arr, Player player){
        TileFactory tileFactory=new TileFactory();
        Map<Character, Supplier<Enemy>> enemiesMap= tileFactory.listEnemies();
        for(int i=0;i<arr.length;i++){
            for(int j=0;j< arr[i].length();j++){
                if(arr[i].charAt(j)=='s'||arr[i].charAt(j)=='k'||arr[i].charAt(j)=='q'||arr[i].charAt(j)=='z'||arr[i].charAt(j)=='b'||arr[i].charAt(j)=='g'||arr[i].charAt(j)=='w'||arr[i].charAt(j)=='M'||arr[i].charAt(j)=='C'||arr[i].charAt(j)=='K'||arr[i].charAt(j)=='B'||arr[i].charAt(j)=='Q'||arr[i].charAt(j)=='D'){
                    Supplier<Enemy> e= map.get(arr[i].charAt(j));
                    BoardController[i][j]=e.get();
                    BoardController[i][j].setX(j);
                    BoardController[i][j].setY(i);
                }
                else if(arr[i].charAt(j)=='@'){
                    BoardController[i][j]=player;
                    player.setY(i);
                    player.setX(j);
                }
                else if(arr[i].charAt(j)=='#'){

                    Wall wall=new Wall();

                    wall.setY(i);
                    wall.setX(j);
                    BoardController[i][j]=wall;
                }
                else {
                    BoardController[i][j] = new Tile(arr[i].charAt(j), j, i);
                }
            }
        }
    }

    public String printBoard(){
        String s="";
        for(int i=0;i< getBoardController().length;i++){
            for (int j=0;j< getBoardController()[i].length;j++){
                s=s+String.valueOf(BoardController[i][j].getType());

            }
            s=s+"\n";
        }
        return s;
    }
}
