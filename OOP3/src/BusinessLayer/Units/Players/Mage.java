package BusinessLayer.Units.Players;

import Board.Tile;

public class Mage extends Player{

    private Integer manaPool;
    private Integer manaAmount;
    private Integer abilityManaCost;
    private Integer spellPower;
    private Integer hitsCount;
    private Integer abilityRange;


    public Mage(String name, int health, Integer attackPoints, Integer defensePoints, Integer manaPool, Integer spellPower, int i, int i1, int i2, Board.Tile tile) {
        super(name, health, attackPoints, defensePoints,tile);
        this.manaPool=manaPool;
        this.manaAmount=manaPool/4;
        this.spellPower=spellPower;
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

    @Override
    public void OnGameTick() {
        manaAmount=Math.min(manaAmount+(1*getLevel()),manaPool);
    }

    public void levelUp(){
        manaPool+=25*getLevel();
        manaAmount=Math.min(manaAmount+(manaPool/4),manaPool);
        spellPower+=10*getLevel();
        super.levelUp();
    }
}
