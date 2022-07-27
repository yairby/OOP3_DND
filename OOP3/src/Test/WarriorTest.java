package Test;

import BusinessLayer.Board.GameTiles;
import BusinessLayer.Board.Position;
import BusinessLayer.Board.Unit;
import BusinessLayer.Units.DeathCallBack;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Mage;
import BusinessLayer.Units.Players.Warrior;
import DataAccessLayer.LevelLoader;
import GameController.TileFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;
import java.util.stream.Collectors;


public class WarriorTest {


    private Warrior warrior;
    private TileFactory tileFactory;
    private GameTiles gameTiles;
    private Enemy e;
    @Before
    public void setup() throws Exception{
        tileFactory=new TileFactory();
        warrior=(Warrior) tileFactory.listPlayers().get(1);

        e=tileFactory.produceEnemy('s');
        String levelMap= LevelLoader.LoadLevel(2).stream().collect(Collectors.joining("\n"));
        gameTiles=new GameTiles(levelMap,tileFactory.listPlayers().get(0),tileFactory.listPlayers().get(0).getCB(),tileFactory);
        warrior.setPosition(new Position(0,0));
        gameTiles.UpdateLocationOfTile(warrior);
    }
    @Test
    public void levelUp() {

        int healthpoll=warrior.getHealth().getPool();
        int attack= warrior.getAttackPoints();;
        int defence=warrior.getDefensePoints();
        warrior.setLevel(0);
        warrior.levelUp();
        System.out.println(warrior.getAttackPoints()+ " "+ attack);
        Assert.assertEquals((healthpoll== warrior.getHealth().getPool()-5)&&(defence== warrior.getDefensePoints()-2)&&(attack== warrior.getAttackPoints()-6),true);

    }






    @Test
    public void useSpecialAbility() {

        e.setPosition(new Position(1,0));
        e.getHealth().setAmount(500);
        warrior.setAttackPoints(1000000000);
        gameTiles.getEnemies().clear();
        gameTiles.getEnemies().add(e);
        e.setDCB(new DeathCallBack() {
            @Override
            public void call(Unit u) {
                System.out.println("dead");
            }
        });
        warrior.UseSpecialAbility(gameTiles.getEnemies(),warrior);


        Assert.assertEquals(e.getHealth().getAmount()<500,false);
        for(int i=0;i<5;i++){
            warrior.onTick();
        }
        warrior.UseSpecialAbility(gameTiles.getEnemies(),warrior);
        Assert.assertEquals(e.getHealth().getAmount()<500,true);
    }
}