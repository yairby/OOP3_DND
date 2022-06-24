package BusinessLayer.Units.Enemies;

public class Monster extends Enemy {
    public int getVisionRange() {
        return visionRange;
    }


    public void setVisionRange(int visionRange) {
        this.visionRange = visionRange;
    }

    private int visionRange;

    public Monster(char tileChar, String name, Integer health, Integer attackPoints, Integer defensePoints,Integer experience, Integer visionRange ) {
        super(tileChar, name, health, attackPoints, defensePoints, experience);
        this.visionRange=visionRange;
    }

    public void findNearBy() {

    }



//    public void Move(){
//        if(range(Tile ))
//        if range(monster, player) < vision range then
//        dx ← enemyX − playerX
//        dy ← enemyY − playerY
//        if |dx| > |dy| then
//        if dx > 0 then
//        Move left
//else
//        Move right
//else
//        if dy > 0 then
//        Move up
//else
//        Move down
//else
//        Perform a random movement action: left, right, up, down or stay at the same place.

//    }
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
