package BusinessLayer.Units.Players;

import BusinessLayer.Units.Health;
import Board.Unit;

public abstract class Player extends Unit {

    private Integer experience;
    private Integer level;

    public Player(String name,Integer health, Integer attackPoints, Integer defensePoints) {
        super(name, health, attackPoints, defensePoints);
        this.experience=0;
        this.level=1;
        this.setType('@');
    }

    public void checkForLevelUp(){
        if(getExperience()>=50*level){
            levelUp();
        }
    }

    public void increaseExperience(Integer exp){
        this.experience+=exp;
        checkForLevelUp();
    }

    public void increaseHealth(Integer h){
        if(h < 0)
            throw new IllegalArgumentException("Negative health value can't be added!");
        Health health=getHealth();
        if(health.getAmount()+h>=health.getPool())
            health.setAmount(health.getPool());
        else
            health.setAmount(health.getAmount()+h);
    }

    public void levelUp(){
        experience-=50*level;
        level++;
        Health h=getHealth();
        h.setPool(h.getPool()+(10*level));
        h.setAmount(h.getPool());
        setAttackPoints(getAttackPoints()+(4*level));
        setDefensePoints(getDefensePoints()+(1*level));
    }
    abstract public String Type();
    abstract public String UseSpecialAbility();

    public Integer getExperience() {
        return experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
