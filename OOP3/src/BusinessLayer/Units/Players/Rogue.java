package BusinessLayer.Units.Players;

public class Rogue extends Player{

    private Integer energy;
    private Integer abilityEnergyCost;

    public Rogue(String name, int health, Integer attackPoints, Integer defensePoints, Integer abilityEnergyCost) {
        super(name,health, attackPoints, defensePoints);
        energy=100;
        this.abilityEnergyCost=abilityEnergyCost;
    }

    @Override
    public void UseSpecialAbility() {

    }

    public void onGameTick() {
        energy=Math.min(energy+10,100);
    }

    @Override
    public void onTick() {

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
}
