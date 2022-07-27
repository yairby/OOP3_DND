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
        gameTiles=new GameTiles(levelMap,tileFactory.listPlayers().get(0),tileFactory.listPlayers().get(0).getCB(),tileFactory);
        hunter.setPosition(new Position(0,0));
        gameTiles.UpdateLocationOfTile(hunter);
    }

      @Test
      public void levelup(){
             int arrows=hunter.getArrowsCount();
             int attack= hunter.getAttackPoints();;
             int defence=hunter.getDefensePoints();
             hunter.setLevel(0);
             hunter.levelUp();
          System.out.println(hunter.getAttackPoints()+ " "+ attack);
             Assert.assertEquals((arrows== hunter.getArrowsCount()-10)&&(defence== hunter.getDefensePoints()-2)&&(attack== hunter.getAttackPoints()-6),true);
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
        e.getHealth().setAmount(500);
        hunter.setAttackPoints(1000000000);
        gameTiles.getEnemies().clear();
        gameTiles.getEnemies().add(e);
        e.setDCB(new DeathCallBack() {
            @Override
            public void call(Unit u) {
                System.out.println("dead");
            }
        });
        hunter.UseSpecialAbility(gameTiles.getEnemies(),hunter);


        Assert.assertEquals(e.getHealth().getAmount()<500,true);
    }

}