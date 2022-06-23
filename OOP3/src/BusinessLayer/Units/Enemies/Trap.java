package BusinessLayer.Units.Enemies;


import Board.Tile;

public class Trap extends Enemy {
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;

    public int getVisibilityTime() {
        return visibilityTime;
    }

    public void setVisibilityTime(int visible) {
        visibilityTime = visible;
    }

    public int getInvisibilityTime() {
        return invisibilityTime;
    }

    public void setInvisibilityTime(int invisible) {
        invisibilityTime = invisible;
    }

    public Trap(char c, String name, int health, Integer attackPoints, Integer defensePoints,int experience, int visibilityTime, int invisibilityTime) {
        super(c, name, health, attackPoints, defensePoints, experience);
        this.visibilityTime=visibilityTime;
        this.invisibilityTime=invisibilityTime;
        ticksCount=0;
        visible=true;
    }

    public String toString(){
        return "name:"+getName()+"    health:"+getHealth().getPool()+"    attack:"+getAttackPoints()+"    defence:"+getDefensePoints();
    }

    @Override
    public void onTurn() {

    }

    @Override
    public void onGameTick() {

    }
}