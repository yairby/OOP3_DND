package BusinessLayer.Units.Players;

import BusinessLayer.CombatSystem.Combat;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Health;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Warrior extends Player {

    private Integer abilityCoolDown;
    private Integer remainingCoolDown;


    public Warrior(String name, int health, int attackPoints, int defensePoints, int abilityCoolDown) {
        super(name,health,attackPoints,defensePoints);
        this.abilityCoolDown=abilityCoolDown;
        this.remainingCoolDown=abilityCoolDown;
    }

    public void levelUp(){
        int oldHealthPull= getHealth().getPool();
        int oldAttackPoints=getAttackPoints();
        int oldDefensePoints=getDefensePoints();
        super.levelUp();
        remainingCoolDown=0;
        Health h=getHealth();
        h.setPool(h.getPool()+(5*getLevel()));
        setAttackPoints(getAttackPoints()+(2*getLevel()));
        setDefensePoints(getDefensePoints()+(getLevel()));
        call(getName()+" reached level "+getLevel()+": +"+(getHealth().getPool()-oldHealthPull)+", +"+(getAttackPoints()-oldAttackPoints)+", +"+(getDefensePoints()-oldDefensePoints));
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
        if(remainingCoolDown!=0){
            remainingCoolDown--;
        }
    }

    @Override
    public void UseSpecialAbility(List<Enemy> enemies, Player player) {
        if (remainingCoolDown==0) {
            remainingCoolDown = abilityCoolDown;
            call(getName() + " used Avenger's Shield, healing for " + 10 * getDefensePoints() + ".");
            increaseHealth(10 * getDefensePoints());
            Enemy chosenEnemy = chooseEnemyInRange( enemies);
            if(chosenEnemy!=null) {
                Combat combat = new Combat(this, chosenEnemy);
                int damage = combat.Attack(getHealth().getPool() / 10); //10% of max health
                call(getName() + " hit " + chosenEnemy.getName() + " for " + damage + " ability damage.\n");
            }
        }else {
            call(getName()+" tried to cast Avenger's Shield, but there is a cooldown: "+remainingCoolDown+".");
        }
    }

    private Enemy chooseEnemyInRange(List<Enemy> enemies){
        List<Enemy> InRange=new ArrayList<>();
        for (Enemy e:enemies) {
            if(this.range(e)<3){
                InRange.add(e);
            }
        }
        if(InRange.size()>0) {
            Random rnd = new Random();
            int chosenEnemy = rnd.nextInt(InRange.size());
            return InRange.get(chosenEnemy);
        }else {
            return null;
        }
    }
}
