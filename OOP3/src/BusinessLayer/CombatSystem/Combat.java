package BusinessLayer.CombatSystem;

import Board.Unit;

import java.util.Random;

public class Combat {
    private Unit Attacker;
    private Unit Defender;

    public Combat(Unit attacker, Unit defender){
        Attacker=attacker;
        Defender=defender;
    }

    public Unit getDefender() {
        return Defender;
    }

    public void setDefender(Unit defender) {
        Defender = defender;
    }

    public Unit getAttacker() {
        return Attacker;
    }

    public void setAttacker(Unit attacker) {
        Attacker = attacker;
    }
    public int Attack(){
        Random r=new Random();
        int attackP=r.nextInt(getAttacker().getAttackPoints()+1);
        int defenceP=r.nextInt(getDefender().getDefensePoints()+1);
        int damage=attackP-defenceP;
        if(damage>0){
            getDefender().setAmountHealth(getDefender().getHealth().getAmount()-damage);
            return damage;
        }
        return 0;
    }

}
