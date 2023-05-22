package BusinessLayer.Units.Players;

import BusinessLayer.CombatSystem.Combat;
import BusinessLayer.Units.Enemies.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        int oldHealthPull= getHealth().getPool();
        int oldAttackPoints=getAttackPoints();
        int oldDefensePoints=getDefensePoints();
        int oldManaAmount=manaAmount;
        int oldSpellPower=spellPower;
        super.levelUp();
        manaPool+=25*getLevel();
        manaAmount=Math.min(manaAmount+(manaPool/4),manaPool);
        spellPower+=10*getLevel();
        call(getName()+" reached level "+getLevel()+": +"+(getHealth().getPool()-oldHealthPull)+" health, +"
                +(getAttackPoints()-oldAttackPoints)+" attack, +"+(getDefensePoints()-oldDefensePoints)+" defense, +"
                +(manaAmount-oldManaAmount)+" mana, +"+(spellPower-oldSpellPower)+" spell power");
    }


    @Override
    public String Type() {
        return "Mage";
    }



    public String toString() {
        String spaces=" ".repeat(5);
        return super.toString()+"Mana: "+manaAmount+"/"+manaPool+spaces+"Spell Power: "+spellPower;
    }

    @Override
    public void onTick() {
        manaAmount=Math.min(manaAmount+(1*getLevel()),manaPool);
    }

    @Override
    public void UseSpecialAbility(List<Enemy> enemies, Player player) {

        if(manaAmount<abilityManaCost){
            call(getName()+" tried to cast Blizzard, but there was not enough mana: "+manaAmount+"/"+abilityManaCost+".");
        }else {
            call(getName()+" cast Blizzard.");
            manaAmount-=abilityManaCost;
            int hits=0;
            while (hits < hitsCount){
                Enemy chosenEnemy=chooseEnemyInRange(enemies);
                if(chosenEnemy!=null){
                    Combat combat=new Combat(this,chosenEnemy);
                    int damage=combat.Attack(spellPower);
                    call(getName()+" hit "+chosenEnemy.getName()+" for "+damage+" ability damage.\n");
                    if(!chosenEnemy.IsAlive()){
                        call(chosenEnemy.getName()+" was killed by "+getName()+"\n");
                        increaseExperience(chosenEnemy.getExperience());
                    }
                }
                hits++;
            }
        }
    }

    private Enemy chooseEnemyInRange(List<Enemy> enemies){
        List<Enemy> InRange=new ArrayList<>();
        for (Enemy e:enemies) {
            if(this.range(e) < abilityRange && e.IsAlive()){ //checks if enemy is alive
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

    public Integer getSpellPower() {
        return spellPower;
    }

    public void setSpellPower(Integer spellPower) {
        this.spellPower = spellPower;
    }

}
