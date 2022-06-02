package BusinessLayer.CombatSystem;

import BusinessLayer.Units.Unit;

public class Combat {
    private Unit Attacker;
    private Unit Defender;

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

    public Combat(Unit attacker, Unit defender){
        Attacker=attacker;
        Defender=defender;
    }

}
