package Board;

import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Enemies.Monster;
import BusinessLayer.Units.Players.Player;
import GameController.TileFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GameBoard {

   private Tile[][] BoardController;
   private Player player;
   private List<Enemy> enemies;

    public GameBoard(String s, Player p, List<Enemy> enemies){
        String[] arr=s.split("\\n");
        this.BoardController=new Tile[arr.length][arr[0].length()];
        initBoard(arr);
        this.player=p;
        this.enemies=enemies;
    }
    public GameBoard(Tile[][] boardController) {
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
    public GameBoard(int num) {

        this.BoardController=new Tile[num][num];
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
                BoardController[i][j]=t;
            }
        }
    }
    public String printBoard(){
        String s="";
        for(int i=0;i< getBoardController().length;i++){
            for (int j=0;j< getBoardController()[i].length;j++){
                s=s+BoardController[i][j].getType();
            }
            s=s+"\n";
        }
        return s;
    }
}
