package BusinessLayer.Units.Enemies;

import Board.Tile;

public class Boss extends Monster{

    public Boss(char c,int experience, String name, int health, Integer attackPoints, Integer defensePoints,int vision) {
        super(c, name,experience, health, attackPoints, defensePoints,vision);
    }


}
