package BusinessLayer.VisitorPattern;

import BusinessLayer.Board.Empty;
import BusinessLayer.Board.Tile;
import BusinessLayer.Board.Wall;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Player;

public interface Visitor {
    public void visit(Player playerTile);
    public void visit(Enemy enemyTile);
    public void visit(Tile t);
    public void visit(Empty emptyTile);
    public void visit(Wall wallTile);
}
