package GameController;

import BusinessLayer.Board.Dead;
import BusinessLayer.Board.GameTiles;
import BusinessLayer.Units.Players.Player;
import DataAccessLayer.LevelLoader;
import UI.Callback;
import UI.MessageCallback;

import java.util.*;
import java.util.stream.Collectors;

public class GameManager {
    private Scanner scanner=new Scanner(System.in);
    private static final int maxLevel=4;
    private Callback CB;
    private TileFactory tileFactory;
    public GameManager(Callback CB){
        this.CB=CB;
    }
    public void StartGame(){
        TileFactory tileFactory=new TileFactory();
        this.tileFactory=tileFactory;

        //loading the players
        List<Player> players= tileFactory.listPlayers();
        for (Player t: players) {
            CB.call((players.indexOf(t)+1)+"."+t.toString()+"     ");
        }

        Player player=ChoosePlayer(players);

        //start game action
        boolean stop=false;
        String status="";
        int currentLevel=1;
        while(!stop){
            boolean passed = PlayLevel(currentLevel,player);
            if(passed){
                CB.call("You passed level: "+currentLevel+"!!!");
                if(currentLevel==maxLevel){
                    stop=true;
                    status="Win";
                }else {
                    currentLevel++;
                }
            }else {
                stop=true;
                status="Dead";
            }
        }
        AskAboutNewGame(status);
    }

    public Player ChoosePlayer(List<Player> playerList){
        //choosing the player
        CB.call("Choose a hero from the list:");
        boolean isChosen=false;
        int choose=-1;
        while (!isChosen){
            try {
                choose = scanner.nextInt();
                if(choose > 0 && choose <= playerList.size()) {
                    isChosen = true;
                }else {
                    throw new Exception();
                }
            } catch (Exception e) {
                CB.call("Invalid input, please choose a hero number from the list.");
                scanner.next();
            }
        }

        //inform the user about the selected player
        Player player= playerList.get(choose-1);
        CB.call("You have selected: "+player.getName());
        return player;
    }
    public void AskAboutNewGame(String status)
    {
        CB.call("You "+status+"! \nDo you want to start a new game? \nPress 'Y' to restart or any other key to exit");
        String startNew=scanner.next();
        if(startNew.equals("Y")||startNew.equals("y")){
            CB.call("Starting new game!");
            StartGame();
        }else{
            CB.call("Thanks for playing our game! Goodbye!");
            System.exit(0);
        }
    }
    public boolean PlayLevel(int level, Player player) {
        CB.call("-------Level "+level+" Started-------");
        String levelMap= LevelLoader.LoadLevel(level).stream().collect(Collectors.joining("\n"));
        GameTiles gameTiles=new GameTiles(levelMap,player,CB, tileFactory);
        CB.call(gameTiles.printBoard());
        CB.call(player.toString());
        boolean passLevel=false,death=false;
        while (!passLevel && !death) {
            String s = scanner.next();
            while (!s.equals("w") && !s.equals("s") && !s.equals("a") && !s.equals("d") && !s.equals("q") && !s.equals("e")){
               s = scanner.next();
            }
            gameTiles.moveAll(s);
            gameTiles.notifyTickables();
            if (!player.IsAlive()) {
                death = true;
                gameTiles.SetTileInPosition(player.getPosition(),new Dead('X',player.getPosition()));
                CB.call(gameTiles.printBoard());
            } else if (gameTiles.getEnemies().isEmpty()) {
                CB.call(gameTiles.printBoard());
                CB.call(player.toString());
                passLevel = true;
            }else {
                CB.call(gameTiles.printBoard());
                CB.call(player.toString());
            }
        }
        return passLevel && !death;
    }
}
