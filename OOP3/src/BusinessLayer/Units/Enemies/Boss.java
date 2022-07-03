package BusinessLayer.Units.Enemies;

import BusinessLayer.Board.Position;
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
    }

    @Override
    public void onTick() {

    }

    @Override
    public Position onMove(List<Enemy> enemyList, Player player, String move) {
        if(range(player)<getVisionRange() && getCombatTicks()==getAbilityFrequency()){
            UseSpecialAbility(enemyList,player);
            return getPosition();
        }else {
            Position p = getPosition().checkMove(PickMove(player));
            return p;
        }
    }

    @Override
    public void UseSpecialAbility(List<Enemy> enemies, Player player) {

    }

    public Integer getCombatTicks() {
        return combatTicks;
    }

    public Integer getAbilityFrequency() {
        return abilityFrequency;
    }
}
