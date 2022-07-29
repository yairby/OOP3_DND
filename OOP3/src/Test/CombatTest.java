package Test;

import BusinessLayer.Board.Dead;
import BusinessLayer.Board.GameTiles;
import BusinessLayer.Board.Unit;
import BusinessLayer.CombatSystem.Combat;
import BusinessLayer.Units.DeathCallBack;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Player;
import DataAccessLayer.LevelLoader;
import GameController.TileFactory;
import org.junit.Assert;

import java.util.stream.Collectors;


class CombatTest {
 private TileFactory tileFactory;
 private String levelMap;
 private GameTiles gameTiles;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        tileFactory=new TileFactory();
        levelMap= LevelLoader.LoadLevel(2).stream().collect(Collectors.joining("\n"));
       gameTiles=new GameTiles(levelMap,tileFactory.listPlayers().get(0),tileFactory.listPlayers().get(0).getCB(),tileFactory);

    }

    @org.junit.jupiter.api.Test
    void attack() {
        Enemy m=tileFactory.produceEnemy('s');
        Player player=tileFactory.listPlayers().get(0);
        int pool=player.getHealth().getPool();
        //enemy attack player
        Combat combat=new Combat(m,player);
        int Attack=combat.Attack();
        int amount=player.getHealth().getAmount();
        Assert.assertEquals(pool==amount+Attack,true);
        int attack=combat.Attack();
        int amount2=player.getHealth().getAmount();
        Assert.assertEquals(amount==amount2+attack,true);
        Enemy m2=tileFactory.produceEnemy('s');
        Player player2=tileFactory.listPlayers().get(0);
        int pool1=m2.getHealth().getPool();
        //player attack enemy
        Combat combat1=new Combat(player2,m2);
        int Attack2=combat1.Attack();
        int amount3=m2.getHealth().getAmount();
        Assert.assertEquals(pool1==amount3+Attack2,true);
        int attack2=combat1.Attack();
        int amount4=m2.getHealth().getAmount();
        Assert.assertEquals(amount3==amount4+attack2,true);
    }

    @org.junit.jupiter.api.Test
    void testAttack() {
        Enemy m=tileFactory.produceEnemy('s');
        Player player=tileFactory.listPlayers().get(0);
        player.setDefensePoints(0);
        Combat combat=new Combat(m,player);
        combat.Attack(50);
        Assert.assertEquals(player.getHealth().getAmount()<player.getHealth().getPool(),true);
        m=gameTiles.getEnemies().get(0);
        int exp= gameTiles.getPlayer().getExperience();


        m.setDefensePoints(0);
        m.setAmountHealth(2);
        gameTiles.getEnemies().add(m);

          gameTiles.getPlayer().setAttackPoints(1000000);
          m.setExperience(100);
         gameTiles.getPlayer().visit(m);
        System.out.println(exp+" "+gameTiles.getPlayer().getExperience());
        Assert.assertEquals(m.getHealth().getAmount()<0,true);
        Assert.assertEquals(exp<gameTiles.getPlayer().getExperience(),true);
    }

    
}