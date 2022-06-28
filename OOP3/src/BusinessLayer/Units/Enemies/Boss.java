package BusinessLayer.Units.Enemies;

import BusinessLayer.Units.HeroicUnit;
import BusinessLayer.Units.Players.Player;

import java.util.List;

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
    public void UseSpecialAbility(List<Enemy> enemies, Player player) {

    }

    public Integer getCombatTicks() {
        return combatTicks;
    }

    public Integer getAbilityFrequency() {
        return abilityFrequency;
    }
}
