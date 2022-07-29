package BusinessLayer.ObserverPattern;

import BusinessLayer.Board.Position;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Player;

import java.util.List;

public interface Listener {
    public void onTick();
    public Position onMove(List<Enemy> enemyList, Player player, String move);

}
