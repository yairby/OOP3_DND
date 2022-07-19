package BusinessLayer.Units.Enemies;

import BusinessLayer.Board.Position;
import BusinessLayer.CombatSystem.Combat;
import BusinessLayer.Units.HeroicUnit;
import BusinessLayer.Units.Players.Player;

import java.util.List;
import java.util.Random;

public class Boss extends Monster implements HeroicUnit {

    private Integer abilityFrequency;
    private Integer combatTicks;

    public Boss(char tileChar, String name, Integer health, Integer attackPoints, Integer defensePoints, Integer experience, Integer visionRange, Integer abilityFrequency) {
        super(tileChar, name, health, attackPoints, defensePoints, experience, visionRange);
        this.abilityFrequency=abilityFrequency;
        this.combatTicks=0;
    }

    @Override
    public void onTick() {}

    @Override
    public Position onMove(List<Enemy> enemyList, Player player, String move) {
        if(range(player) < getVisionRange()){
            if(getCombatTicks()==getAbilityFrequency()){
                UseSpecialAbility(enemyList, player);
                return getPosition();
            }
            combatTicks++;
        }else {
            combatTicks=0;
        }
        Position p = getPosition().checkMove(PickMove(player));
        return p;
    }

    @Override
    public void UseSpecialAbility(List<Enemy> enemies, Player player) {
        combatTicks=0;
        call(getName()+ " casted special ability on "+player.getName());
        Combat combat=new Combat(this,player);
        combat.Attack();
    }

    public Integer getCombatTicks() {
        return combatTicks;
    }

    public Integer getAbilityFrequency() {
        return abilityFrequency;
    }
}
