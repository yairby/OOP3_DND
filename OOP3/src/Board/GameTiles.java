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
        int j=0;
        int t=0;
        for(int i=0;i<s.length();i++){
            while (!(s.substring(i, i + 1).equals("\n"))&&i< Math.pow(getBoardController().length, 2)+2*j){
                BoardController[j][t]=new Tile(s.substring(i,i+1),i,j);
                t++;
            }
            j++;
            i=i+1;
            t=0;

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
