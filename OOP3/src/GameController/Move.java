package GameController;

import BusinessLayer.Board.GameTiles;
import BusinessLayer.Board.Tile;
import BusinessLayer.Board.Unit;

public class Move {
    public void move(GameTiles Board, Unit movingUnit, String move){
        Tile neighbor;
        if(move.equals("w") || move.equals("W")){
            if(movingUnit.getY()!=Board.getUPPERBOUND()){
                neighbor=Board.getTileInPosition(movingUnit.getY()-1, movingUnit.getX());
                neighbor.accept(movingUnit);
                Board.UpdateLocationOfTile(movingUnit);
                Board.UpdateLocationOfTile(neighbor);
            }
        }
    }
}
