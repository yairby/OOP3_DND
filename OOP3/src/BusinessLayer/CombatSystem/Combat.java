package BusinessLayer.CombatSystem;

import BusinessLayer.Board.Unit;
import BusinessLayer.Units.DeathCallBack;
import UI.Callback;
import UI.MessageCallback;

import java.util.Random;

public class Combat {
    private Unit Attacker;
    private Unit Defender;
    private Callback CB;
    private DeathCallBack DCB;

    public Combat(Unit attacker, Unit defender, Callback CB, DeathCallBack DCB){
        Attacker=attacker;
        Defender=defender;
        this.CB=CB;
        this.DCB=DCB;
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
        CB.call("---------"+attackerName+" engaged in combat with "+defenderName+"---------");
        PrintParticipants(getAttacker().toString(), getDefender().toString());
        Random r=new Random();
        int attackP=r.nextInt(getAttacker().getAttackPoints()+1);
        CB.call(attackerName+" rolled "+attackP+" attack points.");
        int defenceP=r.nextInt(getDefender().getDefensePoints()+1);
        CB.call(defenderName+" rolled "+defenderName+" defense points.\n");
        int damage=attackP-defenceP;
        if(damage>0){
            getDefender().setAmountHealth(getDefender().getHealth().getAmount()-damage);
            CB.call(attackerName+" dealt "+damage+" damage to "+defenderName+".");
            if(!Defender.IsAlive()){
                DCB.call(Defender);
                CB.call(defenderName+" was killed by "+attackerName);
                Attacker.OnKill(Defender);
            }
            return damage;
        }
        CB.call(attackerName+" dealt 0 damage to "+defenderName+".");
        return 0;
    }
    public int Attack(int PreDefinedAttackStrength){
        Random r=new Random();
        int defenceP=r.nextInt(getDefender().getDefensePoints()+1);
        int damage=PreDefinedAttackStrength-defenceP;
        if(damage>0){
            if(!Defender.IsAlive()){
                DCB.call(Defender);
                CB.call(Defender.getName()+" was killed by "+Attacker.getName());
                Attacker.OnKill(Defender);
            }
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
        CB.call(s);
    }

}
