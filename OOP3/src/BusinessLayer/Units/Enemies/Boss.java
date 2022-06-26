package BusinessLayer.Units.Enemies;

import BusinessLayer.Units.HeroicUnit;

public class Boss extends Enemy implements HeroicUnit {


    public Boss(char tileChar, String name, Integer health, Integer attackPoints, Integer defensePoints, int experience) {
        super(tileChar, name, health, attackPoints, defensePoints, experience);
    }

    @Override
    public void onTick() {

    }

    @Override
    public void UseSpecialAbility() {

    }
}
