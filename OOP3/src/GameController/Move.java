package GameController;

import BusinessLayer.Board.GameTiles;
import BusinessLayer.Board.Tile;
import BusinessLayer.Board.Unit;

public class Move {
    public void move(GameTiles Board, Unit unit, String move){
        Tile neighbor;
        if(move.equals("w") || move.equals("W")){
            if(unit.getY()!=Board.getUPPERBOUND()){
                neighbor=Board.getTileInPosition(unit.getY()-1, unit.getX());
                neighbor.accept(unit);
                Board.UpdateLocationOfTile(unit);
                Board.UpdateLocationOfTile(neighbor);
            }
        }
    }
}
