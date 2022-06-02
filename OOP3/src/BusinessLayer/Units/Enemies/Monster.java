package BusinessLayer.Units.Enemies;


import Board.Tile;

public class Monster extends Enemy {

    public Monster(int experience, String name, int health, Integer attackPoints, Integer defensePoints, Board.Tile tile) {
        super(experience, name, health, attackPoints, defensePoints, tile);
    }


}
