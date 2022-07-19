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

    public String toString(){
        return super.toString()+"Vision Range: "+visionRange;
    }


    @Override
    public void onTick() {}

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
        double bestRange=Integer.MAX_VALUE;
        for (String move : SimpleMoves) {
            double range=p.range(getPosition().checkMove(move));
                if(bestRange > range){
                    bestRange=range;
                    bestMove=move;
                }
        }
        return bestMove;
    }

}
