package Board;

import BusinessLayer.Units.Players.Player;
import BusinessLayer.Units.Players.Warrior;
import BusinessLayer.Units.Unit;

public class GameTiles {

   private Tile[][] BoardController;

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
    }
    public void initBoard(String[] arr, Player player){


        for(int i=0;i<arr.length;i++){
            for(int j=0;j< arr[i].length();j++){
                if(arr[i].charAt(j)=='s'||arr[i].charAt(j)=='k'||arr[i].charAt(j)=='q'||arr[i].charAt(j)=='z'||arr[i].charAt(j)=='b'||arr[i].charAt(j)=='g'||arr[i].charAt(j)=='w'||arr[i].charAt(j)=='M'||arr[i].charAt(j)=='C'||arr[i].charAt(j)=='K'){
                    BoardController[i][j] = new Tile(arr[i].charAt(j), j, i);
                }
                else if(arr[i].charAt(j)=='@'){
                    BoardController[i][j]=player;
                    System.out.println(i+" "+j);
                    player.setY(i);
                    player.setX(j);
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
