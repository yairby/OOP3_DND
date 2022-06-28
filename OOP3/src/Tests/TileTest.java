package Tests;

import BusinessLayer.Board.Position;
import BusinessLayer.Board.Tile;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    void range() {
        //check distance on axis x
        Tile tile=new Tile('c',new Position(1,0));
        Tile tile2=new Tile('c',new Position(0,0));
        Assert.assertEquals(tile.range(tile2)==1,true);
        //check distance on axis y
     tile=new Tile('c',new Position(0,2));
         tile2=new Tile('c',new Position(0,0));
        Assert.assertEquals(tile.range(tile2)==2,true);
        //check distance on axis y and axis x
        tile=new Tile('c',new Position(3,4));
        tile2=new Tile('c',new Position(0,0));
        Assert.assertEquals(tile.range(tile2)==5,true);
    }


}