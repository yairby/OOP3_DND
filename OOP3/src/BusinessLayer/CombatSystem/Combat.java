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

    public void PrintParticipants(String par1, String par2){
        String s="";
        int maxLen=Math.max(par1.length(),par2.length());
        for (int i = 0; i < maxLen+10; i++) {
            s+="#";
        }
        s+="\n";
        s+="#    "+par1+"    #";
        s+="\n";
        for (int i = 0; i < maxLen+10; i++) {
            s+="#";
        }
        s+="\n";
        String spaces=" ".repeat(Math.abs(par1.length()-par2.length())/2);
        s+="#"+spaces+""+par2+""+spaces+"#";
        s+="\n";
        for (int i = 0; i < maxLen+10; i++) {
            s+="#";
        }
        msgCB.call(s);
    }

}
