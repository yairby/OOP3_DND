package Test;

import BusinessLayer.Board.GameTiles;
import BusinessLayer.Board.Position;
import BusinessLayer.Board.Tile;
import BusinessLayer.Units.Players.Player;
import BusinessLayer.Units.Players.Rogue;
import DataAccessLayer.LevelLoader;
import GameController.TileFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;

public class PositionTest {

    private Player rogue;
    private Position p1;
    private Position p2;
    private TileFactory tileFactory;
    private GameTiles gameTiles;

    @Before
    public void setup() throws Exception{
        tileFactory=new TileFactory();
        rogue=(Rogue) tileFactory.listPlayers().get(5);
        p1=new Position(2,2);
        p2=new Position(2,2);

        String levelMap= LevelLoader.LoadLevel(2).stream().collect(Collectors.joining("\n"));
        gameTiles=new GameTiles(levelMap,tileFactory.listPlayers().get(0),tileFactory.listPlayers().get(0).getCB(),tileFactory);
        rogue.setPosition(new Position(5,5));
        gameTiles.UpdateLocationOfTile(rogue);
    }

    @Test
    public void checkMove() {
        Position p=rogue.getPosition();
        Position p1=rogue.getPosition().checkMove("w");
        Assert.assertEquals(p1.getY()==p.getY()-1,true);
        Assert.assertEquals(p.getX()== p1.getX(),true);
         p=rogue.getPosition();
      p1=rogue.getPosition().checkMove("s");
        Assert.assertEquals(p1.getY()==p.getY()+1,true);
        Assert.assertEquals(p.getX()== p1.getX(),true);
        p=rogue.getPosition();
        p1=rogue.getPosition().checkMove("a");
        Assert.assertEquals(p1.getY()==p.getY(),true);
        Assert.assertEquals(p.getX()== p1.getX()+1,true);
        p=rogue.getPosition();
        p1=rogue.getPosition().checkMove("d");
        Assert.assertEquals(p1.getY()==p.getY(),true);
        Assert.assertEquals(p.getX()== p1.getX()-1,true);
        p=rogue.getPosition();
        p1=rogue.getPosition().checkMove("wa");
        Assert.assertEquals(p1.getY()==p.getY(),true);
        Assert.assertEquals(p.getX()== p1.getX(),true);
    }



    @Test
    public void range() {
        Assert.assertEquals(p1.range(p2)==0,true);
        //check distance on axis y
        p1=new Position(0,2);
        p2=new Position(2,2);
        Assert.assertEquals(p1.range(p2)==2,true);
        //check distance on axis y and axis x
        p1=new Position(3,4);
        p2=new Position(0,0);
        Assert.assertEquals(p1.range(p2)==5,true);
    }


}