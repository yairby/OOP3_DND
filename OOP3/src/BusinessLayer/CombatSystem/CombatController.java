package BusinessLayer.CombatSystem;

import BusinessLayer.Units.Unit;

import java.util.Random;

public class CombatController extends Combat{

    public CombatController(Unit attacker, Unit defender) {
        super(attacker, defender);
    }
    public void Attack(){
        Random r=new Random();
        int attack=r.nextInt(getAttacker().getAttackPoints()+1);
        int defence=r.nextInt(getDefender().getDefensePoints()+1);

        if(attack>defence){
            //getDefender().setAmountHealth(getDefender().getHealth().getAmount()+defence-attack);
            getDefender().setAmountHealth(0);
        }

    }

}
