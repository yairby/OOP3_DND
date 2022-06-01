package BusinessLayer;

import Board.GameTiles;

public class GameBoard {
    private GameTiles Board;

    public GameTiles getBoard() {
        return Board;
    }

    public void setBoard(GameTiles board) {
        Board = board;
    }

    public GameBoard(GameTiles board) {
        Board = board;
    }
}
