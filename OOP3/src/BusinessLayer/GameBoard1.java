package BusinessLayer;

import Board.GameTiles;

public class GameBoard1 {
    private GameTiles Board;

    public GameTiles getBoard() {
        return Board;
    }

    public void setBoard(GameTiles board) {
        Board = board;
    }

    public GameBoard1(GameTiles board) {
        Board = board;
    }
}
