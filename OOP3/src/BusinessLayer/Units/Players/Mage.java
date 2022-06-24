package BusinessLayer.Units.Players;

import Board.Tile;

public class Mage extends Player{

    private Integer manaPool;
    private Integer manaAmount;
    private Integer abilityManaCost;
    private Integer spellPower;
    private Integer hitsCount;
    private Integer abilityRange;


    public Mage(String name, int health, Integer attackPoints, Integer defensePoints, Integer manaPool, Integer abilityManaCost,int spellPower,int hitsCount,int abilityRange) {
        super(name, health, attackPoints, defensePoints);
        this.manaPool=manaPool;
        this.manaAmount=manaPool/4;
        this.spellPower=spellPower;
        this.abilityManaCost=abilityManaCost;
        this.hitsCount=hitsCount;
        this.abilityRange=abilityRange;
    }

    public void levelUp(){
        super.levelUp();
        manaPool+=25*getLevel();
        manaAmount=Math.min(manaAmount+(manaPool/4),manaPool);
        spellPower+=10*getLevel();
    }

    @Override
    public String UseSpecialAbility() {
        if(manaAmount<abilityManaCost){
            return getName()+" tried to cast Blizzard, but there was not enough mana: "+manaAmount+"/"+abilityManaCost+".";
        }else {
            //should add the action
            return getName()+" cast Blizzard.";
        }
    }


    public Integer getSpellPower() {
        return spellPower;
    }

    public void setSpellPower(Integer spellPower) {
        this.spellPower = spellPower;
    }

    public Integer getManaPool() {
        return manaPool;
    }

    public void setManaPool(Integer manaPool) {
        this.manaPool = manaPool;
    }

    public Integer getManaAmount() {
        return manaAmount;
    }

    public void setManaAmount(Integer manaAmount) {
        this.manaAmount = manaAmount;
    }

    public Integer getHitsCount() {
        return hitsCount;
    }

    public void setHitsCount(Integer hitsCount) {
        this.hitsCount = hitsCount;
    }



    @Override
    public String Type() {
        return "Mage";
    }

    public Integer getAbilityRange() {
        return abilityRange;
    }

    public void setAbilityRange(Integer abilityRange) {
        this.abilityRange = abilityRange;
    }

    public Integer getAbilityManaCost() {
        return abilityManaCost;
    }

    public void setAbilityManaCost(Integer abilityManaCost) {
        this.abilityManaCost = abilityManaCost;
    }

    public String toString(){
        return "name:"+getName()+"    health:"+getHealth().getPool()+"    attack:"+getAttackPoints()+"    defence:"+getDefensePoints()+"    ability mana cost:"+getAbilityManaCost()+"    ability range:"+getAbilityRange()+"    mana amount:"+getManaAmount()+"    spell power"+getSpellPower();
    }

    @Override
    public void onTurn() {

    }

    @Override
    public void onGameTick() {
        manaAmount=Math.min(manaAmount+(1*getLevel()),manaPool);
    }
}
