package Test;

import BusinessLayer.Board.GameTiles;
import BusinessLayer.Board.Position;
import BusinessLayer.Board.Unit;
import BusinessLayer.Units.DeathCallBack;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Hunter;
import BusinessLayer.Units.Players.Mage;
import DataAccessLayer.LevelLoader;
import GameController.TileFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class MageTest {


    private Mage mage;
    private TileFactory tileFactory;
    private GameTiles gameTiles;
    private Enemy e;
    @Before
    public void setup() throws Exception{
        tileFactory=new TileFactory();
        mage=(Mage) tileFactory.listPlayers().get(3);

        e=tileFactory.produceEnemy('s');
        String levelMap= LevelLoader.LoadLevel(2).stream().collect(Collectors.joining("\n"));
        gameTiles=new GameTiles(levelMap,tileFactory.listPlayers().get(0),tileFactory.listPlayers().get(0).getCB(),tileFactory);
        mage.setPosition(new Position(0,0));
        gameTiles.UpdateLocationOfTile(mage);
    }
    @Test
    public void levelUp() {
        int manaAmount=mage.getManaAmount();
        int manapool=mage.getManaPool();
        int spellpower=mage.getSpellPower();
        int attack= mage.getAttackPoints();;
        int defence=mage.getDefensePoints();
        mage.setLevel(0);
        mage.levelUp();
        System.out.println(mage.getAttackPoints()+ " "+ attack);
        Assert.assertEquals((spellpower==mage.getSpellPower()-10&&manapool==mage.getManaPool()-25&&manaAmount== mage.getManaAmount()-43)&&(defence== mage.getDefensePoints()-1)&&(attack== mage.getAttackPoints()-4),true);

    }




    @Test
    public void onTick() {
        int mana=mage.getManaAmount();
        mage.onTick();
        Assert.assertEquals(mana==mage.getManaAmount()-1,true);
        mage.setManaAmount(mage.getManaPool());

        mage.onTick();
        System.out.println(mage.getManaAmount()+" "+mage.getManaPool());
        Assert.assertEquals(Objects.equals(mage.getManaPool(), mage.getManaAmount()),true);
    }

    @Test
    public void useSpecialAbility() {
        e.setPosition(new Position(1,0));
        e.getHealth().setAmount(500);
        mage.setAttackPoints(1000000000);
        gameTiles.getEnemies().clear();
        gameTiles.getEnemies().add(e);
        e.setDCB(new DeathCallBack() {
            @Override
            public void call(Unit u) {
                System.out.println("dead");
            }
        });
        mage.UseSpecialAbility(gameTiles.getEnemies(),mage);


        Assert.assertEquals(e.getHealth().getAmount()<500,true);
    }
}