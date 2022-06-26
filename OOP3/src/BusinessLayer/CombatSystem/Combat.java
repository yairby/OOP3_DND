package BusinessLayer.CombatSystem;

import BusinessLayer.Board.Unit;
import UI.MessageCallback;

import java.util.Random;

public class Combat {
    private Unit Attacker;
    private Unit Defender;
    private MessageCallback msgCB=new MessageCallback();

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
        String attackerName= getAttacker().getName();
        String defenderName= getDefender().getName();
        msgCB.call("---------"+attackerName+" engaged in combat with "+defenderName+"---------");
        PrintParticipants(getAttacker().toString(), getDefender().toString());
        Random r=new Random();
        int attackP=r.nextInt(getAttacker().getAttackPoints()+1);
        msgCB.call(attackerName+"");
        int defenceP=r.nextInt(getDefender().getDefensePoints()+1);
        int damage=attackP-defenceP;
        if(damage>0){
            getDefender().setAmountHealth(getDefender().getHealth().getAmount()-damage);
            return damage;
        }
        return 0;
    }

    public void PrintParticipants(String attacker, String defender){
        String s="";
        int maxLen=Math.max(attacker.length(),defender.length());
        for (int i = 0; i < maxLen+12; i++) {
            s+="#";
        }
        s+="\n";
        s+="|Attacker| "+attacker;
        s+="\n";
        for (int i = 0; i < maxLen+12; i++) {
            s+="#";
        }
        s+="\n";
        s+="|Defender| "+defender;
        s+="\n";
        for (int i = 0; i < maxLen+12; i++) {
            s+="#";
        }
        msgCB.call(s);
    }

}
