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

}
