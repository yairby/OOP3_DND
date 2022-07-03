package BusinessLayer.Units.Enemies;

import BusinessLayer.Board.GameTiles;
import BusinessLayer.Board.Tile;

import java.util.Random;

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
        return super.toString()+"Vision Range: "+visionRange;
    }


    @Override
    public void onTick() {

    }

    @Override
    public void Move() {

    }

    private String PickMove(GameTiles gameTiles, String [] moves){
        String selectedMove;
        if(m.range(player)<m.getVisionRange()){
            selectedMove=Chase(m);
        }else {
            Random rnd = new Random();
            int pickedMove = rnd.nextInt(moves.length);
            selectedMove = moves[pickedMove];
        }
        return selectedMove;
    }
    private String Chase(Monster m){
        String bestMove="";
        Integer bestRange=Integer.MAX_VALUE;
        for (String move : SimpleMoves) {
            Tile t=getNeighbor(Board,m,move);
            if(t!=null){
                if(bestRange > t.range(m)){
                    bestRange=t.range(m);
                    bestMove=move;
                }
            }
        }
        return bestMove;
    }
}
