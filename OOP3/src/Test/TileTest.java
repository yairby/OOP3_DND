package Test;

import BusinessLayer.Board.GameTiles;
import BusinessLayer.Board.Position;
import BusinessLayer.Board.Tile;
import BusinessLayer.CombatSystem.Combat;
import BusinessLayer.Units.Players.Player;
import BusinessLayer.Units.Players.Warrior;
import DataAccessLayer.LevelLoader;
import GameController.TileFactory;
import UI.MessageCallback;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class TileTest {
  private Tile tile1;
  private Tile tile;
  private Position position;

    @Before
    public void setUp() throws Exception {
        tile=new Tile('c',new Position(1,1));
      tile1=new Tile('c',new Position(2,1));
      position=tile.getPosition();
      }

    @Test
    public void range() {
      //check distance on axis x

      Assert.assertEquals(tile.range(tile1)==1,true);
      //check distance on axis y
      tile=new Tile('c',new Position(0,2));
      tile1=new Tile('c',new Position(0,0));
      Assert.assertEquals(tile.range(tile1)==2,true);
      //check distance on axis y and axis x
      tile=new Tile('c',new Position(3,4));
      tile1=new Tile('c',new Position(0,0));
      Assert.assertEquals(tile.range(tile1)==5,true);
    }

    @Test
    public void testRange() {
      tile=new Tile('c',new Position(1,4));

      Assert.assertEquals(tile.range(position)==3,true);
    }

    @Test
    public void checkrange() {
      tile=new Tile('c',new Position(1,1));
      tile1=new Tile('c',new Position(2,1));
      Assert.assertEquals(tile.checkrange(tile1,4),true);
      tile=new Tile('c',new Position(1,1));
      tile1=new Tile('c',new Position(2,1));
      Assert.assertEquals(tile.checkrange(tile1,1),false);
      tile=new Tile('c',new Position(1,1));
      tile1=new Tile('c',new Position(3,1));
      Assert.assertEquals(tile.checkrange(tile1,1),false);

    }


}