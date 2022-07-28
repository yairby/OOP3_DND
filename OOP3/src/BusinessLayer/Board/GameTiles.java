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
        gameEntities.add(player);
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
                t.setDeathCB((Unit u)->removeFromBoard(u));
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
    //for tests
    public void newEnemies(){
        enemies=new LinkedList<>();
    }
    public void newEntities(){
        gameEntities=new ArrayList<Unit>();
    }
    public void addEntity(Unit u){
        gameEntities.add(u);
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
        List<Unit> gameEntitiesCopy=new ArrayList<>(gameEntities);
        for (Unit u : gameEntitiesCopy) {
            if (u.IsAlive()) {
                Position p = u.onMove(getEnemies(), getPlayer(), move);
                Tile neighbor = getTileInPosition(p);
                neighbor.accept(u);
                if (neighbor.isExist()) { //only if not dead
                    UpdateLocationOfTile(neighbor);
                } else {
                    if (player.IsAlive()) {
                        neighbor = getTileInPosition(p);
                        neighbor.accept(u);
                        UpdateLocationOfTile(neighbor);
                    }
                }
                UpdateLocationOfTile(u);
            }
        }
    }

    public void removeFromBoard(Unit u){
        gameEntities.remove(u);
        enemies.remove(u);
        u.setExist(false);
        Position deadPos=u.getPosition();
        Board[deadPos.getY()][deadPos.getX()] = new Empty('.', deadPos.getY(), deadPos.getX());
    }


}

