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
        super.levelUp();
        energy=100;
        setAttackPoints(getAttackPoints()+(3*getLevel()));
    }

    @Override
    public String Type() {
        return "Rogue";
    }


    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public Integer getAbilityEnergyCost() {
        return abilityEnergyCost;
    }

    public void setAbilityEnergyCost(Integer abilityEnergyCost) {
        this.abilityEnergyCost = abilityEnergyCost;
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
                }
            }
        }
    }
}
