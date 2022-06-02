package BusinessLayer.Units.Players;

import Board.Tile;
import BusinessLayer.Units.Health;

public class Warrior extends Player {

    private Integer abilityCoolDown;
    private Integer remainingCoolDown;

    public Warrior(Board.Tile tile, String name, int health, Integer attackPoints, Integer defensePoints, Integer abilityCoolDown) {
        super(name, health, attackPoints, defensePoints,tile);
        this.abilityCoolDown=abilityCoolDown;
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
    public void OnGameTick() {
        remainingCoolDown--;
    }

    public void levelUp(){
        //for now we increase be old level
        remainingCoolDown=0;
        Health h=getHealth();
        h.setPool(h.getPool()+(5*getLevel()));
        setAttackPoints(getAttackPoints()+(2*getLevel()));
        setDefensePoints(getDefensePoints()+(getLevel()));
        super.levelUp();
    }
}
