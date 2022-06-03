package Board;

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
    public void initBoard(String s){
        String[] arr=s.split("\\n");

        for(int i=0;i<arr.length;i++){
            for(int j=0;j< arr.length;j++){
                BoardController[i][j]=new Tile(arr[i].charAt(i),j,i);
            }
        }
    }
    public String printBoard(){
        String s="";
        for(int i=0;i< getBoardController().length;i++){
            for (int j=0;j< getBoardController().length;j++){
                s=s+BoardController[i][j];
            }
        }
        return s;
    }
}
