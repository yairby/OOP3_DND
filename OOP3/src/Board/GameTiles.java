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
    public GameTiles(String s){
        String[] arr=s.split("\\n");
        this.BoardController=new Tile[arr.length][arr[0].length()];
        initBoard(arr);
    }
    public void initBoard(String[] arr){


        for(int i=0;i<arr.length;i++){
            for(int j=0;j< arr[0].length();j++){
                BoardController[i][j]=new Tile(arr[i].charAt(j),j,i);
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
