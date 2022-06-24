package BusinessLayer;

import Board.GameBoard;

public class GameBoard1 {
    private GameBoard Board;

    public GameBoard getBoard() {
        return Board;
    }

    public void setBoard(GameBoard board) {
        Board = board;
    }

    public GameBoard1(GameBoard board) {
        Board = board;
    }
}
