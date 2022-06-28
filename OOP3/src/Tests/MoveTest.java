package Tests;

import BusinessLayer.Board.GameTiles;
import DataAccessLayer.LevelLoader;
import GameController.Move;
import GameController.TileFactory;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

class MoveTest {
    private TileFactory tileFactory;
    private Move move;
    String levelMap= LevelLoader.LoadLevel(1).stream().collect(Collectors.joining("\n"));
    private GameTiles gameTiles;
    @BeforeEach
    public void init(){
      tileFactory=new TileFactory();
       move=new Move();
       gameTiles=new GameTiles(levelMap,tileFactory.listPlayers().get(0));
    }
    @Test
    void movePlayer() {
        init();
        int yOriginal=gameTiles.getPlayer().getY();
        int xOriginal=gameTiles.getPlayer().getX();
        move.movePlayer(gameTiles,gameTiles.getPlayer(),"w");
        int yCurrent=gameTiles.getPlayer().getY();
        int xCurrent=gameTiles.getPlayer().getX();
        Assert.assertEquals(yOriginal==yCurrent-1&&xOriginal==xCurrent,true);
        yOriginal=gameTiles.getPlayer().getY();
        xOriginal=gameTiles.getPlayer().getX();
        move.movePlayer(gameTiles,gameTiles.getPlayer(),"a");
        yCurrent=gameTiles.getPlayer().getY();
        xCurrent=gameTiles.getPlayer().getX();
        System.out.println(xCurrent);
        System.out.println(xOriginal);
        Assert.assertEquals(yOriginal==yCurrent&&xOriginal==xCurrent,true);
        yOriginal=gameTiles.getPlayer().getY();
        xOriginal=gameTiles.getPlayer().getX();
        move.movePlayer(gameTiles,gameTiles.getPlayer(),"d");
        yCurrent=gameTiles.getPlayer().getY();
        xCurrent=gameTiles.getPlayer().getX();
        System.out.println(xCurrent);
        System.out.println(xOriginal);
        Assert.assertEquals(yOriginal==yCurrent&&xOriginal==xCurrent-1,true);
        yOriginal=gameTiles.getPlayer().getY();
        xOriginal=gameTiles.getPlayer().getX();
        move.movePlayer(gameTiles,gameTiles.getPlayer(),"s");
        yCurrent=gameTiles.getPlayer().getY();
        xCurrent=gameTiles.getPlayer().getX();
        System.out.println(xCurrent);
        System.out.println(xOriginal);
        Assert.assertEquals(yOriginal==yCurrent+1&&xOriginal==xCurrent,true);
    }

    @Test
    void moveMonsters() {
    }
}