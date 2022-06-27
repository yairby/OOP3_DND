package GameController;

import BusinessLayer.Board.Unit;
import UI.MessageCallback;

public class DeathCallback {

    private MessageCallback msgCB;
    public DeathCallback(MessageCallback msgCB){
        this.msgCB=msgCB;
    }
    public void notifyAboutDeath(Unit u) {

    }
}
