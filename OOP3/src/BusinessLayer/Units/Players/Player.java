package BusinessLayer.Units.Players;

import BusinessLayer.Board.*;
import BusinessLayer.CombatSystem.Combat;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Health;
import BusinessLayer.Units.HeroicUnit;
import BusinessLayer.VisitorPattern.Visitor;
import java.util.List;

public abstract class Player extends Unit implements HeroicUnit {

    private Integer experienceAmount;
    private Integer experiencePool;
    private Integer level;

    public Player(String name,Integer health, Integer attackPoints, Integer defensePoints) {
        super(name, health, attackPoints, defensePoints);
        this.experienceAmount=0;
        this.level=1;
        this.experiencePool=50*level;
        this.setTileChar('@');
    }

    public void checkForLevelUp(){
        while(getExperience()>=experiencePool){
            levelUp();
        }
    }

    public void increaseExperience(Integer exp){
        this.experienceAmount+=exp;
        getCB().call(getName()+" got "+exp+" experience!");
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
        experienceAmount-=50*level;
        level++;
        experiencePool=50*level;
        Health h=getHealth();
        h.setPool(h.getPool()+(10*level));
        h.setAmount(h.getPool());
        setAttackPoints(getAttackPoints()+(4*level));
        setDefensePoints(getDefensePoints()+(1*level));
    }
    public abstract String Type();

    public Integer getExperience() {
        return experienceAmount;
    }
    public void setExperience(Integer experience) {
        this.experienceAmount = experience;
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
        if(!enemy.IsAlive())
        {
            increaseExperience(enemy.getExperience());
        }
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

    @Override
    public Position onMove(List<Enemy> enemyList, Player player, String move) {
        if(move.equals("e")){
            UseSpecialAbility(enemyList,this);
            return getPosition();
        }
        return getPosition().checkMove(move);
    }

    @Override
    public String toString() {
        String spaces = " ".repeat(5);
        return super.toString() + "Level: " + level + spaces + "Experience: " + experienceAmount + "/" + experiencePool + spaces;
    }



}
