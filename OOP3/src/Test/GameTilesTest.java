package Test;

import BusinessLayer.Board.GameTiles;
import BusinessLayer.Board.Position;
import BusinessLayer.Board.Tile;
import BusinessLayer.Board.Unit;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Mage;
import DataAccessLayer.LevelLoader;
import GameController.TileFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class GameTilesTest {
    private GameTiles gameTiles;
    private TileFactory tileFactory;
    private Tile tile;
    private Enemy enemy;
   @Before
   public void setup() throws Exception{
       tileFactory=new TileFactory();

       String levelMap= LevelLoader.LoadLevel(1).stream().collect(Collectors.joining("\n"));
    gameTiles=new GameTiles(levelMap,tileFactory.listPlayers().get(0),tileFactory.listPlayers().get(0).getCB(),tileFactory);
    tile=gameTiles.getPlayer();
    enemy=gameTiles.getEnemies().get(0);
  }
    @Test
    public void updateLocationOfTile() {

       tile.setPosition(new Position(3,4));
       gameTiles.UpdateLocationOfTile(tile);
        Assert.assertEquals(gameTiles.getPlayer().getPosition().range(new Position(3,4))==0,true);
    }

    @Test
    public void removeFromBoard() {
    gameTiles.removeFromBoard(enemy);
    Assert.assertEquals(gameTiles.getEnemies().contains(enemy),false);

    }
    @Test
    public void notifyTickables() {
        enemy.setPosition(new Position(2,2));
        gameTiles.getPlayer().setPosition(new Position(10,10));
        gameTiles.UpdateLocationOfTile(enemy);
        Mage mage=(Mage) tileFactory.listPlayers().get(3);
        gameTiles.UpdateLocationOfTile(gameTiles.getPlayer());
        gameTiles.newEntities();


        gameTiles.addEntity(mage);

        int mana=mage.getManaAmount();
        gameTiles.notifyTickables();
        Assert.assertEquals(mana==mage.getManaAmount()-1,true);
        mage.setManaAmount(mage.getManaPool());

        gameTiles.notifyTickables();
        System.out.println(mage.getManaAmount()+" "+mage.getManaPool());
        Assert.assertEquals(Objects.equals(mage.getManaPool(), mage.getManaAmount()),true);

    }
    @Test
    public void moveAll() {
       //without chase
        enemy.setPosition(new Position(2,2));
        gameTiles.getPlayer().setPosition(new Position(10,10));
        Position p=enemy.getPosition();
        Position p1=gameTiles.getPlayer().getPosition();
        gameTiles.UpdateLocationOfTile(enemy);
        gameTiles.UpdateLocationOfTile(gameTiles.getPlayer());
       gameTiles.newEntities();
       gameTiles.addEntity(enemy);

       gameTiles.addEntity(gameTiles.getPlayer());
       gameTiles.moveAll("w");
       Assert.assertEquals(gameTiles.getPlayer().getX()==p1.getX(),true);
        Assert.assertEquals(gameTiles.getPlayer().getY()==p1.getY()-1,true);
      //with chase
        enemy.setPosition(new Position(2,2));
        gameTiles.getPlayer().setPosition(new Position(3,3));
     p=enemy.getPosition();
        p1=gameTiles.getPlayer().getPosition();
        gameTiles.UpdateLocationOfTile(enemy);
        gameTiles.UpdateLocationOfTile(gameTiles.getPlayer());
        gameTiles.newEntities();
        gameTiles.addEntity(enemy);

        gameTiles.addEntity(gameTiles.getPlayer());
        gameTiles.moveAll("w");
        Assert.assertEquals(gameTiles.getPlayer().getX()==p1.getX(),true);
        Assert.assertEquals(gameTiles.getPlayer().getY()==p1.getY()-1,true);
        Assert.assertEquals(enemy.range(gameTiles.getPlayer())>p.range(gameTiles.getPlayer().getPosition()),true);
    }
}