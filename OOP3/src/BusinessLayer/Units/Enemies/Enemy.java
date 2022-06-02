package BusinessLayer.Units.Enemies;

import Board.Tile;
import BusinessLayer.Units.Unit;

public class Enemy extends Unit {

    private int Experience;

    public Enemy(char c,int experience,String name, int health, Integer attackPoints, Integer defensePoints, Board.Tile tile) {
        super(c,health, name, attackPoints, defensePoints, tile);
        Experience=experience;
    }


    public int getExperience() {
        return Experience;
    }

    public void setExperience(int experience) {
        Experience = experience;
    }

    public String toString(){
        return "name:"+getName()+"    health:"+getHealth()+"    attack:"+getAttackPoints()+"    defence:"+getDefensePoints()+"    experience"+getExperience();

    }
}
