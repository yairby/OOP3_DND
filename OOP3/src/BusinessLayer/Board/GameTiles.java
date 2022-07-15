package BusinessLayer.Board;

import BusinessLayer.ObserverPattern.Notifier;
import BusinessLayer.Units.DeathCallBack;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Players.Player;
import GameController.TileFactory;
import UI.Callback;

import java.util.*;

public class GameTiles implements Notifier {

    private Tile[][] Board;
    private Player player;
    private List<Enemy> enemies;

    private List<Unit> gameEntities;
    private Callback CB;
    private DeathCallBack DCB;
    private TileFactory tileFactory;

    public GameTiles(String levelMap, Player p, Callback CB, TileFactory tileFactory) {
        String[] arr = levelMap.split("\\n");
        this.tileFactory=tileFactory;
        this.Board = new Tile[arr.length][arr[0].length()];
        this.enemies = new ArrayList<>();
        this.player = p;
        this.gameEntities = new LinkedList<>();
        this.CB=CB;
        DeathCallBack DCB=(Unit u) -> removeFromBoard(u);
        this.DCB=DCB;
        initBoard(arr);
    }

    public Tile[][] getBoard() {
        return Board;
    }


    public void initBoard(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length(); j++) {
                Tile t;
                char type = arr[i].charAt(j);
                switch (type) {
                    case '@':
                        t = player;
                        gameEntities.add(player);
                        break;
                    case '#':
                        t = new Wall(type, i, j);
                        break;
                    case '.':
                        t = new Empty(type, i, j);
                        break;
                    default:
                        Enemy enemy = tileFactory.produceEnemy(type);
                        enemies.add(enemy);
                        gameEntities.add(enemy);
                        t = enemy;
                }
                Board[i][j] = t;
                t.setPosition(new Position(i, j));
                t.setCB(CB);
                //setDeathCallback
            }
        }
    }

    public void SetTileInPosition(Position p, Tile newTile) {
        Board[p.getY()][p.getX()] = newTile;

    }

    public Tile getTileInPosition(Position p) {
        return Board[p.getY()][p.getX()];
    }

    public Tile getTileInPosition(int y, int x) {
        return Board[y][x];
    }

    public String printBoard() {
        String s = "";
        for (int i = 0; i < getBoard().length; i++) {
            for (int j = 0; j < getBoard()[i].length; j++) {
                s = s + Board[i][j].getTileChar();
            }
            s = s + "\n";
        }
        return s;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Player getPlayer() {
        return player;
    }


    public void UpdateLocationOfTile(Tile t) {
        Board[t.getY()][t.getX()] = t;
    }

    @Override
    public void notifyTickables() {
        for (Unit u : gameEntities) {
            u.onTick();
        }
    }
    @Override
    public void moveAll(String move) {
        for (Unit u : gameEntities) {
            Position p=u.onMove(getEnemies(),getPlayer(),move);
            Tile neighbor=getTileInPosition(p);
            neighbor.accept(u);
            UpdateLocationOfTile(u);
            UpdateLocationOfTile(neighbor);
        }
    }

    //    public Tile getNeighbor(char c, Position p) {
//        Map<Character, Supplier<Tile>> neighbors = new HashMap<>() {
//            {
//                put('a', () -> getTileInPosition(p.Left()));
//                put('d', () -> getTileInPosition(p.Right()));
//                put('s', () -> getTileInPosition(p.Down()));
//                put('w', () -> getTileInPosition(p.Up()));
//                put('q', () -> getTileInPosition(p));
//            }
//        };
//        return neighbors.get(c).get();
//    }
    public Tile getNeighbor(Unit u, String move) {
        Tile neighbor = null;
        if (move.equals("w")) {
            neighbor = getTileInPosition(u.getY() - 1, u.getX());
        }
        if (move.equals("s")) {
            neighbor = getTileInPosition(u.getY() + 1, u.getX());
        }
        if (move.equals("d")) {
            neighbor = getTileInPosition(u.getY(), u.getX() + 1);
        }
        if (move.equals("a")) {
            neighbor = getTileInPosition(u.getY(), u.getX() - 1);
        }
        return neighbor;
    }

    public void removeFromBoard(Unit u){
        Position p=u.getPosition();
        Board[p.getY()][p.getX()]=new Empty('.',p.getY(),p.getX());
        gameEntities.remove(u);
    }


}

