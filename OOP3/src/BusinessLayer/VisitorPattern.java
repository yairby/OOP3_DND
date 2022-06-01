package BusinessLayer;

import Board.Tile;
import BusinessLayer.Units.Unit;

public class VisitorPattern {

    public String toString(GameBoard gameBoard){
        String s="";
        for(int i=0;i<gameBoard.getBoard().getBoardController().length;i++){
            for(int j=0;j<gameBoard.getBoard().getBoardController()[i].length;j++){
                  s=s+String.valueOf(gameBoard.getBoard().getBoardController()[i][j]);
            }
            s=s+"\n";
        }
        return s;
    }
    public String GetName(Tile tile,GameBoard gameBoard){
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
