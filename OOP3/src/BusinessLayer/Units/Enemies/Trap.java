package BusinessLayer.Units.Enemies;


import BusinessLayer.Board.Position;
import BusinessLayer.CombatSystem.Combat;
import BusinessLayer.Units.Players.Player;

import java.util.List;

public class Trap extends Enemy {
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;
    private char tileChar;


    public Trap(char c, String name, int health, Integer attackPoints, Integer defensePoints,int experience, int visibilityTime, int invisibilityTime) {
        super(c, name, health, attackPoints, defensePoints, experience);
        this.visibilityTime=visibilityTime;
        this.invisibilityTime=invisibilityTime;
        ticksCount=0;
        visible=true;
        tileChar=c;
    }

    public String toString(){
        return super.toString();
    }

    @Override
    public void onTick() {
        visible = ticksCount < visibilityTime;
        if(visible){
            setTileChar(tileChar);
        }else {
            setTileChar('.');
        }
        if (ticksCount == (visibilityTime + invisibilityTime)) {
            ticksCount=0;
        }
        else {
            ticksCount++;
        }
    }

    @Override
    public Position onMove(List<Enemy> enemyList, Player player, String move) {
        if (range(player) < 2) {
            Combat combat=new Combat(this,player);
            combat.Attack();
        }
        return getPosition();
    }


}