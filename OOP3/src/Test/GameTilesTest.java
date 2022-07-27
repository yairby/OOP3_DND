package Test;

import BusinessLayer.Board.GameTiles;
import BusinessLayer.Board.Position;
import BusinessLayer.Board.Tile;
import BusinessLayer.Units.Enemies.Enemy;
import DataAccessLayer.LevelLoader;
import GameController.TileFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
}