package UI;

import GameController.GameManager;
import GameController.TileFactory;

public class UI {
    public static void main(String[] args) {
        GameManager game=new GameManager(new MessageCallback());
        game.StartGame();

    }
}
