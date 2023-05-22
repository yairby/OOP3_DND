package UI;

import GameController.GameManager;

public class UI {
    public static void main(String[] args) {
        GameManager game = new GameManager(new MessageCallback());
        game.StartGame("OOP3\\src\\DataAccessLayer\\");
    }
}
