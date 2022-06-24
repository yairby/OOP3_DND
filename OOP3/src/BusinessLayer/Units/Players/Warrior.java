package BusinessLayer.Units.Players;

import Board.Tile;
import BusinessLayer.Units.Health;

public class Warrior extends Player {

    private Integer abilityCoolDown;
    private Integer remainingCoolDown;


    public Warrior(String name, int health, int attackPoints, int defensePoints, int abilityCoolDown) {
        super(name,health,attackPoints,defensePoints);
        this.abilityCoolDown=abilityCoolDown;
        this.remainingCoolDown=abilityCoolDown;
    }

    public void levelUp(){
        //for now we increase be old level
        super.levelUp();
        remainingCoolDown=0;
        Health h=getHealth();
        h.setPool(h.getPool()+(5*getLevel()));
        setAttackPoints(getAttackPoints()+(2*getLevel()));
        setDefensePoints(getDefensePoints()+(getLevel()));
    }

    @Override
    public String UseSpecialAbility() {
        if (remainingCoolDown==0){
            remainingCoolDown=abilityCoolDown;
            increaseHealth(10*getDefensePoints());
            //checking for monsters and attack randomly should be added
            return getName()+" used Avenger's Shield, healing for 40.";
        }else {
            return getName()+" tried to cast Avenger's Shield, but there is a cooldown: "+remainingCoolDown+".";
        }
    }


    @Override
    public String Type() {
        return "Warrior";
    }

    public Integer getRemainingCoolDown() {
        return remainingCoolDown;
    }

    public void setRemainingCoolDown(Integer remainingCoolDown) {
        this.remainingCoolDown = remainingCoolDown;
    }

    public Integer getAbilityCoolDown() {
        return abilityCoolDown;
    }

    public void setAbilityCoolDown(Integer abilityCoolDown) {
        this.abilityCoolDown = abilityCoolDown;
    }

    public String toString(){
        return "name:"+getName()+"    health:"+getHealth().getPool()+"    attack:"+getAttackPoints()+"    defence:"+getDefensePoints()+"     level:"+getLevel()+"     Experience"+getExperience()+"    ability cool down:"+getAbilityCoolDown();

    }

    @Override
    public void onTurn() {

    }

    @Override
    public void onGameTick() {
        remainingCoolDown--;
    }
}
