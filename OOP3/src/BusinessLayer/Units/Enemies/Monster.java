package BusinessLayer.Units.Enemies;

import BusinessLayer.Board.GameTiles;
import BusinessLayer.Board.Position;
import BusinessLayer.Board.Tile;
import BusinessLayer.Units.Players.Player;

import java.util.List;
import java.util.Random;

public class Monster extends Enemy {

    private String [] SimpleMoves={"w","a","s","d","q"};
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
    public Position onMove(List<Enemy> enemyList, Player player, String move) {
        Position p=getPosition().checkMove(PickMove(player));
        return p;
    }

    protected String PickMove(Player player){
        String selectedMove;
        if(range(player)<getVisionRange()){
            selectedMove=Chase(player);
        }else {
            Random rnd = new Random();
            int pickedMove = rnd.nextInt(SimpleMoves.length);
            selectedMove = SimpleMoves[pickedMove];
        }
        return selectedMove;
    }
    protected String Chase(Player p){
        String bestMove="";
        Integer bestRange=Integer.MAX_VALUE;
        for (String move : SimpleMoves) {
            int range=range(p.getPosition().checkMove(move));
                if(bestRange > range){
                    bestRange=range;
                    bestMove=move;
                }
        }
        return bestMove;
    }

}
