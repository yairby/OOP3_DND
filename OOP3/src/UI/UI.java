package UI;

import DataAccessLayer.DataAccessLayer;
import Game.GameController;

import java.util.List;

public class UI {
    public static void main(String[] args) {

        GameController gameController=new GameController();
        gameController.play();
    }
}
