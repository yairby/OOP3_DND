package BusinessLayer.Units;

import BusinessLayer.Board.Unit;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Player;
import java.util.List;

public interface HeroicUnit {
    public abstract void UseSpecialAbility(List<Enemy> enemies, Player player);

    int getY();

    int getX();
}
