package Test;

import BusinessLayer.Board.GameTiles;
import BusinessLayer.Board.Position;
import BusinessLayer.Units.Enemies.Boss;
import BusinessLayer.Units.Enemies.Enemy;
import DataAccessLayer.LevelLoader;
import GameController.TileFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class BossTest {
    private TileFactory tileFactory;
    private Boss boss;
    private GameTiles gameTiles;

    @Before
    public void setUp() throws Exception {
        tileFactory=new TileFactory();
       Enemy bos=tileFactory.produceEnemy('M');
        tileFactory=new TileFactory();

        String levelMap= LevelLoader.LoadLevel(2).stream().collect(Collectors.joining("\n"));
        gameTiles=new GameTiles(levelMap,tileFactory.listPlayers().get(0),tileFactory.listPlayers().get(0).getCB(),tileFactory);

        boss=(Boss) bos;
        boss.setPosition(new Position(0,0));
        gameTiles.UpdateLocationOfTile(boss);
    }



    @Test
    public void onMove() {
        Position p=gameTiles.getPlayer().getPosition();
        Position positon=boss.getPosition();
        System.out.println(p.getX()+" "+p.getY());//12 19
        Assert.assertEquals(boss.onMove(gameTiles.getEnemies(),gameTiles.getPlayer(),"w").range(positon)==0,false);
        boss.setPosition(new Position(11,19));
        positon=boss.getPosition();
        gameTiles.UpdateLocationOfTile(boss);
        Assert.assertEquals(boss.onMove(gameTiles.getEnemies(),gameTiles.getPlayer(),"w").range(positon)==0,true);

    }






    @Test
    public void useSpecialAbility() {
        // the probability of this test to failed is very low
        gameTiles.getPlayer().getHealth().setAmount(500);
        gameTiles.getPlayer().setDefensePoints(0);
        boss.setAttackPoints(10000000);
        boss.UseSpecialAbility(gameTiles.getEnemies(), gameTiles.getPlayer());
        Assert.assertEquals(gameTiles.getPlayer().getHealth().getAmount()<500,true);
    }
}