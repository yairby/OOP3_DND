package BusinessLayer.Board;

import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Health;
import BusinessLayer.Units.Players.Player;
import BusinessLayer.VisitorPattern.Visited;
import BusinessLayer.VisitorPattern.Visitor;


public abstract class Unit extends Tile {
    private String name;
    private Health health;
    private Integer attackPoints;
    private Integer defensePoints;

    public Unit(String name,Integer health, Integer attackPoints,Integer defensePoints){
        this.name=name;
        this.health=new Health(health,health);
        this.attackPoints=attackPoints;
        this.defensePoints=defensePoints;
    }

    public String getName() {
        return name;
    }

    public Health getHealth() {
        return health;
    }

    public Integer getAttackPoints() {
        return attackPoints;
    }

    public Integer getDefensePoints() {
        return defensePoints;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(Health health) {
        this.health = health;
    }
    public void setAmountHealth(int amountHealth){
        this.health.setAmount(amountHealth);
    }

    public void setAttackPoints(Integer attackPoints) {
        this.attackPoints = attackPoints;
    }

    public void setDefensePoints(Integer defensePoints) {
        this.defensePoints = defensePoints;
    }

    public abstract void onTurn();
    public abstract void onGameTick();

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
    @Override
    public void visit(Player playerTile) {
        visit(playerTile);
    }
    @Override
    public void visit(Enemy enemyTile){
        visit(enemyTile);
    }
    @Override
    public void visit(Tile tile){
        tile.visit(this);
    }
}
