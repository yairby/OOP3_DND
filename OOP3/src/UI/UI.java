package UI;

import DataAccessLayer.LevelLoader;
import GameController.GameManager;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class UI {
    public static void main(String[] args) {
        GameManager game=new GameManager(new MessageCallback());
        game.StartGame(1);
    }
}
