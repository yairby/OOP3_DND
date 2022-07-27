package Test;

import BusinessLayer.Board.GameTiles;
import BusinessLayer.Board.Position;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Health;
import BusinessLayer.Units.Players.Player;
import BusinessLayer.Units.Players.Rogue;
import DataAccessLayer.LevelLoader;
import GameController.TileFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;

public class PlayerTest {

    private Player rogue;
    private TileFactory tileFactory;
    private GameTiles gameTiles;

    @Before
    public void setup() throws Exception{
        tileFactory=new TileFactory();
        rogue=(Rogue) tileFactory.listPlayers().get(5);


        String levelMap= LevelLoader.LoadLevel(2).stream().collect(Collectors.joining("\n"));
        gameTiles=new GameTiles(levelMap,tileFactory.listPlayers().get(0),tileFactory.listPlayers().get(0).getCB(),tileFactory);
        rogue.setPosition(new Position(0,0));
        gameTiles.UpdateLocationOfTile(rogue);
    }

@Test
    public void increaseHealth(){
       int health=rogue.getHealth().getAmount();
       try{
            rogue.increaseHealth(-1);
            Assert.assertEquals(false,true);
       }
       catch (Exception e){
           Assert.assertEquals(true,true);
       }
       rogue.increaseHealth(rogue.getHealth().getPool()/2);
    Assert.assertEquals(health==rogue.getHealth().getAmount(),true);
    rogue.getHealth().setAmount(0);
    rogue.increaseHealth(rogue.getHealth().getPool()/2);
    Assert.assertEquals(health==rogue.getHealth().getAmount(),false);
    Assert.assertEquals(rogue.getHealth().getPool()/2==rogue.getHealth().getAmount(),true);
}

    @Test
    public void levelUp() {
        Health health=new Health(rogue.getHealth().getPool(),rogue.getHealth().getAmount());
        int attack= rogue.getAttackPoints();;
        int defence=rogue.getDefensePoints();
        rogue.setLevel(0);
        rogue.levelUp();

        Assert.assertEquals((!health.equals(rogue.getHealth()))&&(defence== rogue.getDefensePoints()-1)&&(attack== rogue.getAttackPoints()-7),true);

    }



    @Test
    public void onMove() {
        rogue.setPosition(new Position(3,3));
        gameTiles.UpdateLocationOfTile(rogue);
        Position p=rogue.getPosition();


        Position p1=rogue.onMove(gameTiles.getEnemies(),rogue,"w");
        Assert.assertEquals(p1.getY()==p.getY()-1,true);
        Assert.assertEquals(p1.getX()==p.getX(),true);
      p=rogue.getPosition();


        p1=rogue.onMove(gameTiles.getEnemies(),rogue,"s");
        Assert.assertEquals(p1.getY()==p.getY()+1,true);
        Assert.assertEquals(p1.getX()==p.getX(),true);
        p=rogue.getPosition();


        p1=rogue.onMove(gameTiles.getEnemies(),rogue,"a");
        Assert.assertEquals(p1.getY()==p.getY(),true);
        Assert.assertEquals(p1.getX()==p.getX()-1,true);
        p=rogue.getPosition();


        p1=rogue.onMove(gameTiles.getEnemies(),rogue,"d");
        Assert.assertEquals(p1.getY()==p.getY(),true);
        Assert.assertEquals(p1.getX()==p.getX()+1,true);
        p=rogue.getPosition();


        p1=rogue.onMove(gameTiles.getEnemies(),rogue,"e");
        Assert.assertEquals(p1.getY()==p.getY(),true);
        Assert.assertEquals(p1.getX()==p.getX(),true);
    }


}