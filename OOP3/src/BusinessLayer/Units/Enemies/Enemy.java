package BusinessLayer.Units.Enemies;

import BusinessLayer.Board.Empty;
import BusinessLayer.Board.Unit;
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
        return "name:"+getName()+"    health:"+getHealth()+"    attack:"+getAttackPoints()+"    defence:"+getDefensePoints()+"    experience"+getExperience();

    }

    public char getTileChar() {
       return tileChar;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
    public void visit(Player player) {
        Combat combat=new Combat(this,player);
        combat.Attack();
    }

}
