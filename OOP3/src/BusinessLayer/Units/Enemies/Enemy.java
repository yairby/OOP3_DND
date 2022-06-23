package BusinessLayer.Units.Enemies;

import Board.Unit;

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
}
