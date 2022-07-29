package Test;

import BusinessLayer.Board.GameTiles;
import BusinessLayer.Board.Position;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Enemies.Trap;
import BusinessLayer.Units.Players.Rogue;
import DataAccessLayer.LevelLoader;
import GameController.TileFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class TrapTest {
    private Trap trap;
    private Rogue rogue;
    private TileFactory tileFactory;
    private GameTiles gameTiles;
    private Enemy e;
    @Before
    public void setup() throws Exception{
        tileFactory=new TileFactory();
        rogue=(Rogue) tileFactory.listPlayers().get(5);

        e=tileFactory.produceEnemy('Q');
        trap=(Trap)e;
        String levelMap= LevelLoader.LoadLevel(2).stream().collect(Collectors.joining("\n"));
        gameTiles=new GameTiles(levelMap,tileFactory.listPlayers().get(0),tileFactory.listPlayers().get(0).getCB(),tileFactory);
        e.setPosition(new Position(0,0));
        gameTiles.UpdateLocationOfTile(e);
    }

    @Test
    public void onTick(){

        trap.setInvisibilityTime(2);
        trap.setVisibilityTime(2);
        int tick=trap.getTicksCount();
        trap.setTicksCount(0);
        trap.onTick();
        Assert.assertEquals(gameTiles.getBoard()[trap.getY()][trap.getX()].getTileChar()==trap.getTileChar(),true);
        Assert.assertEquals(trap.getTicksCount()==1,true);
        trap.setTicksCount(4);
        trap.onTick();
        Assert.assertEquals(gameTiles.getBoard()[trap.getY()][trap.getX()].getTileChar()=='.',true);
        Assert.assertEquals(trap.getTicksCount()==0,true);
    }
}