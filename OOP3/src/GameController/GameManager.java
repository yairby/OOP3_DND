package GameController;

import Board.GameTiles;
import Board.Tile;
import BusinessLayer.Units.Players.Player;
import DataAccessLayer.LevelLoader;
import UI.MessageCallback;

import java.util.*;
import java.util.stream.Collectors;

public class GameManager {
    private Scanner scanner=new Scanner(System.in);
    private static final int maxLevel=4;
    private MessageCallback msgCB;
    public GameManager(MessageCallback msgCB){
        this.msgCB=msgCB;
    }
    public void StartGame(){
        TileFactory tileFactory=new TileFactory();

        //loading the players
        List<Player> players= tileFactory.listPlayers();
        for (Player t: players) {
            msgCB.call((players.indexOf(t)+1)+"."+t.toString()+"     ");
        }

        Player player=ChoosePlayer(players);

        //start game action
        boolean stop=false;
        String status="";
        int currentLevel=1;
        while(!stop){
            boolean passed = PlayLevel(currentLevel,player);
            if(passed){
                msgCB.call("You passed level: "+currentLevel+"!!!");
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
        msgCB.call("Choose a hero from the list:");
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
                System.out.println("Invalid input, please choose a hero number from the list.");
                scanner.next();
            }
        }

        //inform the user about the selected player
        Player player= playerList.get(choose-1);
        msgCB.call("You have selected: "+player.getName());
        return player;
    }
    public void AskAboutNewGame(String status)
    {
        msgCB.call("You "+status+"! \n Do you want to start a new game? \n Press 'Y' to restart or any other key to exit");
        String startNew=scanner.nextLine();
        if(startNew.equals("Y")||startNew.equals("y")){
            msgCB.call("Starting new game!");
            StartGame();
        }else{
            msgCB.call("Thanks for playing our game! Goodbye!");
            System.exit(0);
        }
    }
    public boolean PlayLevel(int level, Player player) {
        msgCB.call("-------Level "+level+" Started-------");
        String levelMap= LevelLoader.LoadLevel(level).stream().collect(Collectors.joining("\n"));
        GameTiles gameTiles=new GameTiles(levelMap,player);
        msgCB.call(gameTiles.printBoard());
        msgCB.call(player.toString());

        boolean passLevel=false,death=false;
        while (!passLevel && !death) {
            String s = scanner.next();
            Move m = new Move();
            m.move(gameTiles, player, s);
            m.moveMonsters(gameTiles, player);
            msgCB.call(gameTiles.printBoard());
            msgCB.call(player.toString());
            if (player.getHealth().getAmount() == 0 || player.getHealth().getAmount() < 0) {
                death = true;
                gameTiles.getBoardController()[player.getY()][player.getX()]=new Tile('X',player.getX(),player.getY());
                msgCB.call(gameTiles.printBoard());
            } else if (gameTiles.getEnemies().isEmpty()) {
                passLevel = true;
            }
        }
        return passLevel && !death;
    }


}
