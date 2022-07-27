package BusinessLayer.Units.Players;

import BusinessLayer.CombatSystem.Combat;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Health;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hunter extends Player {

    private Integer range;



    private Integer arrowsCount;



    private Integer ticksCount;

    public Hunter(String name, int health, int attackPoints, int defensePoints, int range) {
        super(name,health,attackPoints,defensePoints);
        this.range=range;
        this.arrowsCount=10*getLevel();
        this.ticksCount=0;
    }

    public void levelUp(){
        int oldHealthPull= getHealth().getPool();
        int oldAttackPoints=getAttackPoints();
        int oldDefensePoints=getDefensePoints();
        int oldArrowsCount=arrowsCount;
        super.levelUp();
        setAttackPoints(getAttackPoints()+(2*getLevel()));
        setDefensePoints(getDefensePoints()+(getLevel()));
        arrowsCount+=10*getLevel();
        call(getName()+" reached level "+getLevel()+": +"+(getHealth().getPool()-oldHealthPull)+" health, +"+(getAttackPoints()-oldAttackPoints)+
                " attack, +"+(getDefensePoints()-oldDefensePoints)+" defense, +"+(arrowsCount-oldArrowsCount)+" arrows");
    }


    @Override
    public String Type() {
        return "Hunter";
    }

    public String toString(){
        String spaces=" ".repeat(5);
        return super.toString()+"Arrows: "+arrowsCount+spaces+"Range: "+range;
    }

    @Override
    public void onTick() {
        if (ticksCount == 10) {
            arrowsCount = arrowsCount + getLevel();
            ticksCount = 0;
        }
        else {
           ticksCount++;
        }
    }

    @Override
    public void UseSpecialAbility(List<Enemy> enemies, Player player) {
        if (arrowsCount > 0) {
            Enemy chosenEnemy = chooseClosestEnemy(enemies);
            if(chosenEnemy!=null) {
                arrowsCount--;
                call(getName() + " fired an arrow at "+chosenEnemy.getName()+".");
                Combat combat = new Combat(this, chosenEnemy);
                int damage = combat.Attack(getAttackPoints());
                call(getName() + " hit " + chosenEnemy.getName() + " for " + damage + " ability damage.\n");
                if(!chosenEnemy.IsAlive()){
                    call(chosenEnemy.getName()+" was killed by "+getName()+"\n");
                    increaseExperience(chosenEnemy.getExperience());
                }
            }else {
                call(getName()+" tried to Shoot enemy with arrows, but there is not enemy in range.");
            }
        }else {
            call(getName()+" tried to Shoot enemy with arrows, but he doesn't have arrows.");
        }
    }

    private Enemy chooseClosestEnemy(List<Enemy> enemies){
        Enemy closestEnemy=null;
        int closetRange=Integer.MAX_VALUE;
        for (Enemy e:enemies) {
            double rangeFromEnemy=this.range(e);
            if(rangeFromEnemy < range && rangeFromEnemy < closetRange){
                closestEnemy=e;
            }
        }
        return closestEnemy;
    }
    public Integer getTicksCount() {
        return ticksCount;
    }

    public void setTicksCount(Integer ticksCount) {
        this.ticksCount = ticksCount;
    }
    public Integer getArrowsCount() {
        return arrowsCount;
    }

    public void setArrowsCount(Integer arrowsCount) {
        this.arrowsCount = arrowsCount;
    }
}
