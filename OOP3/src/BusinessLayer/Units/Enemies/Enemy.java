package BusinessLayer.Units.Enemies;

import Board.Tile;
import BusinessLayer.Units.Unit;

public class Enemy extends Unit {

    private int Experience;

    public Enemy(int experience,String name, int health, Integer attackPoints, Integer defensePoints, Board.Tile tile) {
        super(name, health, attackPoints, defensePoints, tile);
        Experience=experience;
    }


    public int getExperience() {
        return Experience;
    }

    public void setExperience(int experience) {
        Experience = experience;
    }
}
