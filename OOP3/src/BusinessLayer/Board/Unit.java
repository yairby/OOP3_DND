package BusinessLayer.Board;

import BusinessLayer.ObserverPattern.Tickable;
import BusinessLayer.Units.Health;
import BusinessLayer.VisitorPattern.Visitor;
import GameController.DeathCallback;


public abstract class Unit extends Tile implements Tickable,Visitor {
    private String name;
    private Health health;
    private Integer attackPoints;
    private Integer defensePoints;
    //private DeathCallback deathCB=new DeathCallback();

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

    public abstract void onTick();

    //public DeathCallback getDeathCB() {
       // return deathCB;
   // }

    public String toString(){
        String spaces=" ".repeat(5);
        return ""+name+spaces+"Health: "+getHealth().getAmount()+"/"+getHealth().getPool()+spaces+"Attack: "+attackPoints+spaces+"Defense: "+defensePoints+spaces;
    }

}
