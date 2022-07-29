package Test;

import BusinessLayer.Board.GameTiles;
import BusinessLayer.Board.Position;
import BusinessLayer.Board.Unit;
import BusinessLayer.Units.DeathCallBack;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Hunter;
import DataAccessLayer.LevelLoader;
import GameController.TileFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class HunterTest {

    private Hunter hunter;
    private TileFactory tileFactory;
    private GameTiles gameTiles;
    private Enemy e;
    @Before
    public void setup() throws Exception{
        tileFactory=new TileFactory();
        hunter=(Hunter) tileFactory.listPlayers().get(6);

        e=tileFactory.produceEnemy('s');
        String levelMap= LevelLoader.LoadLevel(2).stream().collect(Collectors.joining("\n"));
        gameTiles=new GameTiles(levelMap,tileFactory.listPlayers().get(6),tileFactory.listPlayers().get(6).getCB(),tileFactory);
        gameTiles.getPlayer().setPosition(new Position(0,0));
        gameTiles.UpdateLocationOfTile(gameTiles.getPlayer());
    }

      @Test
      public void levelup(){

             int arrows=hunter.getArrowsCount();
             int attack= hunter.getAttackPoints();;
             int defence=hunter.getDefensePoints();

             hunter.levelUp();
          System.out.println(hunter.getAttackPoints()+ " "+ attack);
             Assert.assertEquals((arrows== hunter.getArrowsCount()-20)&&(defence== hunter.getDefensePoints()-4)&&(attack== hunter.getAttackPoints()-12),true);
       }
    @Test
    public void onTick() {
        hunter.setTicksCount(0);
        int tick=hunter.getTicksCount();
        int arrow=hunter.getArrowsCount();
        hunter.onTick();
        Assert.assertEquals(hunter.getArrowsCount().equals(arrow),true);
        hunter.setTicksCount(10);
       tick=hunter.getTicksCount();
       arrow=hunter.getArrowsCount();
        hunter.onTick();
        Assert.assertEquals(hunter.getArrowsCount().equals(arrow),false);
        Assert.assertEquals(hunter.getTicksCount().equals(0),true);
    }



    @Test
    public void useSpecialAbility() {
        e.setPosition(new Position(1,0));
        e.getHealth().setAmount(500000000);
        gameTiles.getPlayer().setAttackPoints(10000000);
        gameTiles.newEnemies();
        gameTiles.getEnemies().add(e);
        gameTiles.getPlayer().UseSpecialAbility(gameTiles.getEnemies(),gameTiles.getPlayer());
        Assert.assertEquals(e.getHealth().getAmount()<500000000,true);
    }

}