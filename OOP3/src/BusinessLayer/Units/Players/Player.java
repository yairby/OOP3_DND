package BusinessLayer.Units.Players;

import BusinessLayer.Board.*;
import BusinessLayer.CombatSystem.Combat;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Health;
import BusinessLayer.Units.HeroicUnit;
import BusinessLayer.VisitorPattern.Visitor;

public abstract class Player extends Unit implements HeroicUnit {

    private Integer experience;
    private Integer level;

    public Player(String name,Integer health, Integer attackPoints, Integer defensePoints) {
        super(name, health, attackPoints, defensePoints);
        this.experience=0;
        this.level=1;
        this.setTileChar('@');
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
    public abstract String Type();
    public abstract void UseSpecialAbility();

    public Integer getExperience() {
        return experience;
    }
    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }


    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
    @Override
    public void visit(Enemy enemy) {
        Combat combat=new Combat(this,enemy);
        combat.Attack();
    }
    @Override
    public void visit(Player player) {}
    @Override
    public void visit(Wall wall) {}
    @Override
    public void visit(Empty empty) {
        Position emptyPos=empty.getPosition();
        empty.setPosition(getPosition());
        setPosition(emptyPos);
    }
    @Override
    public void visit(Tile tile) {
        tile.accept(this);
    }




}
