package BusinessLayer.Units.Players;

import BusinessLayer.CombatSystem.Combat;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Health;

import java.util.List;

public class Warrior extends Player {

    private Integer abilityCoolDown;
    private Integer remainingCoolDown;


    public Warrior(String name, int health, int attackPoints, int defensePoints, int abilityCoolDown) {
        super(name,health,attackPoints,defensePoints);
        this.abilityCoolDown=abilityCoolDown;
        this.remainingCoolDown=abilityCoolDown;
    }

    public void levelUp(){
        super.levelUp();
        remainingCoolDown=0;
        Health h=getHealth();
        h.setPool(h.getPool()+(5*getLevel()));
        setAttackPoints(getAttackPoints()+(2*getLevel()));
        setDefensePoints(getDefensePoints()+(getLevel()));
    }

    @Override
    public void UseSpecialAbility() {
        if (remainingCoolDown==0){
            remainingCoolDown=abilityCoolDown;
            call(getName()+" used Avenger's Shield, healing for "+10*getDefensePoints()+".");
            increaseHealth(10*getDefensePoints());
            List<Enemy> enemiesInRange=getEnemiesInRange(3);
            for (Enemy e: enemiesInRange) {
                Combat combat=new Combat(this,e);
                int damage=combat.Attack(getHealth().getPool()/10); //10% of max health
                call(getName()+" hit "+e.getName()+" for "+damage+" ability damage.");
            }
        }else {
            call(getName()+" tried to cast Avenger's Shield, but there is a cooldown: "+remainingCoolDown+".");
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
        return super.toString()+"Cooldown: "+remainingCoolDown+"/"+abilityCoolDown;
    }

    @Override
    public void onTick() {
        remainingCoolDown--;
    }
}
