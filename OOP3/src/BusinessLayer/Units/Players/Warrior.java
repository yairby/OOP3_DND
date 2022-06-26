package BusinessLayer.Units.Players;

import BusinessLayer.Units.Health;

public class Warrior extends Player {

    private Integer abilityCoolDown;
    private Integer remainingCoolDown;


    public Warrior(String name, int health, int attackPoints, int defensePoints, int abilityCoolDown) {
        super(name,health,attackPoints,defensePoints);
        this.abilityCoolDown=abilityCoolDown;
        this.remainingCoolDown=abilityCoolDown;
    }

    public void levelUp(){
        super.levelUp();
        remainingCoolDown=0;
        Health h=getHealth();
        h.setPool(h.getPool()+(5*getLevel()));
        setAttackPoints(getAttackPoints()+(2*getLevel()));
        setDefensePoints(getDefensePoints()+(getLevel()));
    }

    @Override
    public void UseSpecialAbility() {
        if (remainingCoolDown==0){
            remainingCoolDown=abilityCoolDown;
            increaseHealth(10*getDefensePoints());
            //checking for monsters and attack randomly should be added
            call(getName()+" used Avenger's Shield, healing for 40.");
        }else {
            call(getName()+" tried to cast Avenger's Shield, but there is a cooldown: "+remainingCoolDown+".");
        }
    }


    @Override
    public String Type() {
        return "Warrior";
    }

    public Integer getRemainingCoolDown() {
        return remainingCoolDown;
    }

    public void setRemainingCoolDown(Integer remainingCoolDown) {
        this.remainingCoolDown = remainingCoolDown;
    }

    public Integer getAbilityCoolDown() {
        return abilityCoolDown;
    }

    public void setAbilityCoolDown(Integer abilityCoolDown) {
        this.abilityCoolDown = abilityCoolDown;
    }

    public String toString(){
        return "name:"+getName()+"    health:"+getHealth().getAmount()+"/"+getHealth().getPool()+"    attack:"+getAttackPoints()+"    defence:"+getDefensePoints()+"     level:"+getLevel()+"     Experience"+getExperience()+"    ability cool down:"+getAbilityCoolDown();

    }

    @Override
    public void onTurn() {

    }

    @Override
    public void onTick() {
        remainingCoolDown--;
    }
}
