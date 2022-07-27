package Test;

import BusinessLayer.CombatSystem.Combat;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Player;
import GameController.TileFactory;
import org.junit.Assert;


class CombatTest {
 private TileFactory tileFactory;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        tileFactory=new TileFactory();
    }

    @org.junit.jupiter.api.Test
    void attack() {
        Enemy m=tileFactory.produceEnemy('s');
        Player player=tileFactory.listPlayers().get(0);
        int pool=player.getHealth().getPool();

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
    }

    
}