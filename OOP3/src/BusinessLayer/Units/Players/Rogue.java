package BusinessLayer.Units.Players;

import Board.Tile;
import BusinessLayer.Units.Health;

public class Rogue extends Player{

    private Integer energy;
    private Integer abilityEnergyCost;

    public Rogue(String name, int health, Integer attackPoints, Integer defensePoints, Integer abilityEnergyCost) {
        super(name,health, attackPoints, defensePoints);
        energy=100;
        this.abilityEnergyCost=abilityEnergyCost;
    }

    @Override
    public String UseSpecialAbility() {
        return null;
    }

    public void onGameTick() {
        energy=Math.min(energy+10,100);
    }

    @Override
    public void onTurn() {

    }

    public void levelUp(){
        super.levelUp();
        energy=100;
        setAttackPoints(getAttackPoints()+(3*getLevel()));
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
        return "name:"+getName()+"    health:"+getHealth().getPool()+"    attack:"+getAttackPoints()+"    defence:"+getDefensePoints()+"    ability energy cost:"+getAbilityEnergyCost()+"    ability energy:"+getEnergy();

    }
}
