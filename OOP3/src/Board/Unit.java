package Board;

import Board.Tile;
import BusinessLayer.Units.Health;


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
}
