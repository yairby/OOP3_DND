package BusinessLayer.Units;

import Board.Tile;

public class Wall extends Tile {

    public Wall(){
        this.setType('#');
        this.setX(-1);
        this.setY(-1);
    }

}
