package Tests;

import BusinessLayer.Board.GameTiles;
import DataAccessLayer.LevelLoader;
import GameController.TileFactory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class GameTilesTest {

    @Test
    void printBoard() {
        TileFactory tileFactory=new TileFactory();
        String levelMap= LevelLoader.LoadLevel(1).stream().collect(Collectors.joining("\n"));
        GameTiles gameTiles=new GameTiles(levelMap,tileFactory.listPlayers().get(0));

        Assert.assertEquals(gameTiles.printBoard().equals(levelMap),true);
    }


}