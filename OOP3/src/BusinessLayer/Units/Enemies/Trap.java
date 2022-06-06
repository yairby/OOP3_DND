package BusinessLayer.Units.Enemies;


import Board.Tile;

public class Trap extends Enemy {
    private int Visible;
    private int Invisible;

    public int getVisible() {
        return Visible;
    }

    public void setVisible(int visible) {
        Visible = visible;
    }

    public int getInvisible() {
        return Invisible;
    }

    public void setInvisible(int invisible) {
        Invisible = invisible;
    }

    public Trap( int experience, String name, int health, Integer attackPoints, Integer defensePoints, Board.Tile tile, int visible, int invisible) {
        super(experience, name, health, attackPoints, defensePoints);
        Visible=visible;
        Invisible=invisible;
    }
    public String toString(){
        return "name:"+getName()+"    health:"+getHealth().getPool()+"    attack:"+getAttackPoints()+"    defence:"+getDefensePoints();
    }
}