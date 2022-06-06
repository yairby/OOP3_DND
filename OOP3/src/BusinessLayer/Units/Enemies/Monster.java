package BusinessLayer.Units.Enemies;


import Board.Tile;

public class Monster extends Enemy {
    public int getVisionRange() {
        return visionRange;
    }

    public void setVisionRange(int visionRange) {
        this.visionRange = visionRange;
    }

    private int visionRange;
    public Monster(char c, String name,int experience, int health, Integer attackPoints, Integer defensePoints,int visionrange) {
        super(experience, name, health, attackPoints, defensePoints);
        this.setType(c);
        this.setX(-1);
        this.setY(-1);
        this.visionRange=visionrange;
    }
    public String toString(){
        return "name:"+getName()+"    health:"+getHealth().getPool()+"    attack:"+getAttackPoints()+"    defence:"+getDefensePoints();

    }


}
