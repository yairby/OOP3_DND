package Test;

import BusinessLayer.Board.Unit;
import BusinessLayer.Units.Players.Warrior;
import GameController.TileFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitTest {

    private Unit unit;
    private TileFactory tileFactory;
    @Before
    public void setUp() throws Exception {
        tileFactory=new TileFactory();
        unit= tileFactory.produceEnemy('s');
    }

    @Test
    public void isAlive() {
        Assert.assertEquals(unit.IsAlive(),true);
        unit.setAmountHealth(0);
        Assert.assertEquals(unit.IsAlive(),false);
    }
}