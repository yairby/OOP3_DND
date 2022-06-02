package BusinessLayer.Units;

import Board.Tile;

public class Unit  {
    private Tile Tile;
    private String name;
    private Health health;
    private Integer attackPoints;
    private Integer defensePoints;


    public Unit(String name, int health, Integer attackPoints,Integer defensePoints,Tile tile){
        Tile=tile;
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

    public void setAttackPoints(Integer attackPoints) {
        this.attackPoints = attackPoints;
    }

    public void setDefensePoints(Integer defensePoints) {
        this.defensePoints = defensePoints;
    }
}
