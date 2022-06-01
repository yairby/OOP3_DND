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




    public double range(Tile t1,Tile t2){
        return Math.sqrt(Math.pow(t1.getX()- t2.getX(),2)+Math.pow(t1.getY()- t2.getY(),2));
    }
}
