package BusinessLayer.Units.Players;

import BusinessLayer.Units.Health;

public class Rogue extends Player{

    private Integer energy;
    private Integer abilityEnergyCost;

    public Rogue(String name, Health health, Integer attackPoints, Integer defensePoints,Integer abilityEnergyCost) {
        super(name, health, attackPoints, defensePoints);
        energy=100;
        this.abilityEnergyCost=abilityEnergyCost;
    }

    @Override
    public String UseSpecialAbility() {
        return null;
    }

    @Override
    public void OnGameTick() {
        energy=Math.min(energy+10,100);
    }

    public void levelUp(){
        energy=100;
        setAttackPoints(getAttackPoints()+(3*getLevel()));
    }
}
