package BusinessLayer.Units.Enemies;

import BusinessLayer.Board.*;
import BusinessLayer.CombatSystem.Combat;
import BusinessLayer.Units.Players.Player;
import BusinessLayer.VisitorPattern.Visitor;

public abstract class Enemy extends Unit {

    private int experience;
    private char tileChar;


    private char enemyType;

    public Enemy(char tileChar, String name, Integer health, Integer attackPoints, Integer defensePoints, int experience) {
        super(name, health, attackPoints, defensePoints);
        this.experience=experience;
        this.tileChar=tileChar;
    }


    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String toString(){
        return "name:"+getName()+"    health:"+getHealth().getAmount()+"/"+getHealth().getPool()+"    attack:"+getAttackPoints()+"    defence:"+getDefensePoints()+"    experience"+getExperience();

    }

    public char getTileChar() {
       return tileChar;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
    @Override
    public void visit(Enemy enemy) {}
    @Override
    public void visit(Player player) {
        Combat combat=new Combat(this,player);
        combat.Attack();
    }
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
