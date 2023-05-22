package Test;

import BusinessLayer.Board.GameTiles;
import BusinessLayer.Board.Position;
import BusinessLayer.Board.Unit;
import BusinessLayer.Units.DeathCallBack;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Rogue;
import BusinessLayer.Units.Players.Warrior;
import DataAccessLayer.LevelLoader;
import GameController.TileFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;

public class RogueTest {


    private Rogue rogue;
    private TileFactory tileFactory;
    private GameTiles gameTiles;
    private Enemy e;
    @Before
    public void setup() throws Exception{
        tileFactory=new TileFactory();
        rogue=(Rogue) tileFactory.listPlayers().get(5);

        e=tileFactory.produceEnemy('s');
        String levelMap= LevelLoader.LoadLevel(2).stream().collect(Collectors.joining("\n"));
        gameTiles=new GameTiles(levelMap,tileFactory.listPlayers().get(0),tileFactory.listPlayers().get(0).getCB(),tileFactory);
        rogue.setPosition(new Position(0,0));
        gameTiles.UpdateLocationOfTile(rogue);
    }
    @Test
    public void levelUp() {

        int energy=rogue.getEnergy();
        int attack= rogue.getAttackPoints();;
        int defence=rogue.getDefensePoints();
        rogue.setLevel(1);
        rogue.levelUp();

        Assert.assertEquals((energy== 100)&&(defence== rogue.getDefensePoints()-2)&&(attack== rogue.getAttackPoints()-14),true);

    }






    @Test
    public void useSpecialAbility() {

        e.setPosition(new Position(1,0));
        e.getHealth().setAmount(500);
        rogue.setAttackPoints(100);
        gameTiles.getEnemies().clear();
        gameTiles.getEnemies().add(e);
        e.setDCB(new DeathCallBack() {
            @Override
            public void call(Unit u) {
                System.out.println("dead");
            }
        });
        rogue.UseSpecialAbility(gameTiles.getEnemies(),rogue);


        Assert.assertEquals(e.getHealth().getAmount()<500,true);
        rogue.setEnergy(0);
        e.setPosition(new Position(1,0));
        e.getHealth().setAmount(500);
        rogue.setAttackPoints(100);
        gameTiles.getEnemies().clear();
        gameTiles.getEnemies().add(e);
        e.setDCB(new DeathCallBack() {
            @Override
            public void call(Unit u) {
                System.out.println("dead");
            }
        });
        rogue.UseSpecialAbility(gameTiles.getEnemies(),rogue);


        Assert.assertEquals(e.getHealth().getAmount()<500,false);

    }
}