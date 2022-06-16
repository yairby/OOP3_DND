package BusinessLayer.CombatSystem;

import BusinessLayer.Units.Unit;

import java.util.Random;

public class CombatController extends Combat{

    public CombatController(Unit attacker, Unit defender) {
        super(attacker, defender);
    }
    public void Attack(){
        System.out.println(getAttacker().getName()+" engaged in combat with "+getDefender().getName());
        Random r=new Random();
        System.out.println(getAttacker().toString());
        System.out.println(getDefender().toString());
        int attack=r.nextInt(getAttacker().getAttackPoints()+1);
        int defence=r.nextInt(getDefender().getDefensePoints()+1);
        System.out.println(getAttacker().getName()+" rolled "+attack+"  attack points.");
        System.out.println(getDefender().getName()+" rolled "+defence+"  defence points.");
        if(attack>defence){
            System.out.println(getAttacker().getName()+" dealt "+ String.valueOf(attack-defence)+ " damage to Lannister Solider.");
            getDefender().setAmountHealth(getDefender().getHealth().getAmount()+defence-attack);

        }

    }

}
