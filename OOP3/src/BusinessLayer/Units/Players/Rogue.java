package BusinessLayer.Units.Players;

import BusinessLayer.CombatSystem.Combat;
import BusinessLayer.Units.Enemies.Enemy;

import java.util.List;

public class Rogue extends Player{

    private Integer energy;
    private Integer abilityEnergyCost;

    public Rogue(String name, int health, Integer attackPoints, Integer defensePoints, Integer abilityEnergyCost) {
        super(name,health, attackPoints, defensePoints);
        energy=100;
        this.abilityEnergyCost=abilityEnergyCost;
    }


    @Override
    public void onTick() {
        energy=Math.min(energy+10,100);
    }

    public void levelUp(){
        int oldHealthPull= getHealth().getPool();
        int oldAttackPoints=getAttackPoints();
        int oldDefensePoints=getDefensePoints();
        int oldEnergy=energy;
        super.levelUp();
        energy=100;
        setAttackPoints(getAttackPoints()+(3*getLevel()));
        call(getName()+" reached level "+getLevel()+": +"+(getHealth().getPool()-oldHealthPull)+" health, +"
                +(getAttackPoints()-oldAttackPoints)+" attack, +"+(getDefensePoints()-oldDefensePoints)+" defense, +"+(energy-oldEnergy)+" energy");
    }

    @Override
    public String Type() {
        return "Rogue";
    }


    public String toString(){
        return super.toString()+"Energy: "+energy;
    }

    @Override
    public void UseSpecialAbility(List<Enemy> enemies, Player player) {
        if(energy<abilityEnergyCost){
            call(""+getName()+"tried to cast Fan of Knives, but there was not enough energy: "+energy+"/"+abilityEnergyCost+".");
        }else {
            call(""+getName()+" cast Fan of Knives.");
            for (Enemy e:enemies) {
                if(this.range(e)<2){
                    Combat combat=new Combat(this,e);
                    int damage=combat.Attack(getAttackPoints());
                    call(""+getName()+" hit "+e.getName()+" for "+damage+" ability damage.");
                    if(!e.IsAlive()){
                        call(e.getName()+" was killed by "+getName()+"\n");
                        increaseExperience(e.getExperience());
                    }
                }
            }
        }
    }
}
