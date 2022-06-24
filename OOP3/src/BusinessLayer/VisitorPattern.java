package BusinessLayer;

import Board.Tile;

public class VisitorPattern {

    public String toString(GameBoard1 gameBoard1){
        String s="";
        for(int i = 0; i< gameBoard1.getBoard().getBoardController().length; i++){
            for(int j = 0; j< gameBoard1.getBoard().getBoardController()[i].length; j++){
                  s=s+String.valueOf(gameBoard1.getBoard().getBoardController()[i][j]);
            }
            s=s+"\n";
        }
        return s;
    }
    public String GetName(Tile tile, GameBoard1 gameBoard1){
        /*
        if( gameBoard.getBoard().getBoardController()[tile.getY()][tile.getX()].equals('@')){
            return "player";
        }
        if( gameBoard.getBoard().getBoardController()[tile.getY()][tile.getX()].equals('.')){
            return "dot";
        }
        if( gameBoard.getBoard().getBoardController()[tile.getY()][tile.getX()].equals('#')){
            return "wall";
        }
        //add enemies
       return "";

         */
        return "";
    }



}
