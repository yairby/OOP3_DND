package Test;

import BusinessLayer.Board.GameTiles;
import BusinessLayer.Board.Position;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Enemies.Monster;
import BusinessLayer.Units.Players.Player;
import BusinessLayer.Units.Players.Rogue;
import DataAccessLayer.LevelLoader;
import GameController.TileFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;

public class MonsterTest {


    private Monster monster;
    private Player rogue;
    private TileFactory tileFactory;
    private GameTiles gameTiles;

    @Before
    public void setup() throws Exception{
        tileFactory=new TileFactory();
        rogue=(Rogue) tileFactory.listPlayers().get(5);

       monster=(Monster) tileFactory.produceEnemy('s');
        String levelMap= LevelLoader.LoadLevel(2).stream().collect(Collectors.joining("\n"));
        gameTiles=new GameTiles(levelMap,tileFactory.listPlayers().get(0),tileFactory.listPlayers().get(0).getCB(),tileFactory);
       rogue.setPosition(new Position(0,0));
       gameTiles.UpdateLocationOfTile(rogue);
    }


    @Test
    public void onMove() {
        monster.setPosition(new Position(2,0));
        gameTiles.UpdateLocationOfTile(monster);
        Position p=monster.getPosition();
        Position p1=monster.onMove(gameTiles.getEnemies(),rogue,"d");


        Assert.assertEquals(p.range(rogue.getPosition())> p1.range(rogue.getPosition()),true);
        monster.setPosition(new Position(0,2));
        gameTiles.UpdateLocationOfTile(monster);
        p=monster.getPosition();
        p1=monster.onMove(gameTiles.getEnemies(),rogue,"d");


        Assert.assertEquals(p.range(rogue.getPosition())> p1.range(rogue.getPosition()),true);

        monster.setPosition(new Position(2,2));
        gameTiles.UpdateLocationOfTile(monster);
      p=monster.getPosition();
      p1=monster.onMove(gameTiles.getEnemies(),rogue,"d");


        Assert.assertEquals(p.range(rogue.getPosition())> p1.range(rogue.getPosition()),true);






    }

}