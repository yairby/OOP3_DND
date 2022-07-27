package BusinessLayer.Board;

import BusinessLayer.ObserverPattern.Listener;
import BusinessLayer.Units.DeathCallBack;
import BusinessLayer.Units.Health;
import BusinessLayer.VisitorPattern.Visitor;


public abstract class Unit extends Tile implements Listener,Visitor {
    private String name;
    private Health health;
    private Integer attackPoints;
    private Integer defensePoints;
    private DeathCallBack DCB;

    public Unit(String name, Integer health, Integer attackPoints, Integer defensePoints){
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

    public String toString(){
        String spaces=" ".repeat(5);
        return ""+name+spaces+"Health: "+getHealth().getAmount()+"/"+getHealth().getPool()+spaces+"Attack: "+attackPoints+spaces+"Defense: "+defensePoints+spaces;
    }
    
    public boolean IsAlive(){
        return getHealth().getAmount()>0;
    }

    public void setDCB(DeathCallBack DCB) {
        this.DCB = DCB;
    }

}
