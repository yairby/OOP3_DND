package Game;

import Board.GameTiles;
import Board.Tile;
import Board.TileFactory;
import BusinessLayer.CombatSystem.Combat;
import BusinessLayer.CombatSystem.CombatController;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Enemies.Monster;
import BusinessLayer.Units.Players.Player;
import BusinessLayer.Units.Unit;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public class Move {
    public Unit move(GameTiles gameTiles, Unit unit,String move){

        if(move.equals("w")){
            if(!(unit.getY()-1<0)&&(gameTiles.getBoardController()[unit.getY() - 1][unit.getX()].getType()=='.')) {
                gameTiles.getBoardController()[unit.getY()][unit.getX()] = new Tile('.', unit.getX(), unit.getY());
                gameTiles.getBoardController()[unit.getY() - 1][unit.getX()] = unit;
                unit.setY(unit.getY() - 1);

            }
return unit;

        }
        else if(move.equals("s")){
            if(!(unit.getY()+1>gameTiles.getBoardController().length)&&(gameTiles.getBoardController()[unit.getY() + 1][unit.getX()].getType()=='.')) {

                gameTiles.getBoardController()[unit.getY()][unit.getX()] = new Tile('.', unit.getX(), unit.getY());
                gameTiles.getBoardController()[unit.getY() + 1][unit.getX()] = unit;
                unit.setY(unit.getY() + 1);
                return unit;
            }
            return unit;
        }
        else if(move.equals("a")){
            if(!(unit.getX()-1<0)&&(gameTiles.getBoardController()[unit.getY() ][unit.getX()-1].getType()=='.')) {

                    gameTiles.getBoardController()[unit.getY()][unit.getX()] = new Tile('.', unit.getX(), unit.getY());
                    gameTiles.getBoardController()[unit.getY()][unit.getX() - 1] = unit;
                    unit.setX(unit.getX() - 1);
                    return unit;
                }
            return unit;
        }
        else if(move.equals("d")){
            if(!(unit.getX()+1>gameTiles.getBoardController()[0].length)&&(gameTiles.getBoardController()[unit.getY() ][unit.getX()+1].getType()=='.')) {

                gameTiles.getBoardController()[unit.getY()][unit.getX()] = new Tile('.', unit.getX(), unit.getY());
                gameTiles.getBoardController()[unit.getY()][unit.getX() + 1] = unit;
                unit.setX(unit.getX() + 1);
                return unit;
            }
            return unit;
        }
        else if(move.equals("q")){
     return unit;
        }
        else if(move.equals("e")){
            return unit;
        }
        else {
            System.out.println("invalid move");
            return null;
        }

    }
    public void moveMonsters(GameTiles gameTiles,Player player){
        ArrayList<Enemy> enemies=gameTiles.getEnemies();
        for(int i=0;i<enemies.size();i++){
           if(enemies.get(i) instanceof Monster){
              moveMonster(gameTiles,(Monster) enemies.get(i),player);
           }

        }
      /*  ArrayList<Tile> tiles=new ArrayList<Tile>();
       for(int i=0;i<gameTiles.getBoardController().length;i++){
            for(int j=0;j<gameTiles.getBoardController()[i].length;j++){

                boolean hasMove=false;
                for(int h=0;h<tiles.size()&&!hasMove;h++){
                    if(tiles.get(h).getX()==j&&tiles.get(h).getY()==i)
                        hasMove=true;
                }
                if(!hasMove) {
                    Tile tile = gameTiles.getBoardController()[i][j];
                    if (tile.getType() == 's' || tile.getType() == 'k' || tile.getType() == 'q' || tile.getType() == 'z' || tile.getType() == 'b' || tile.getType() == 'g' || tile.getType() == 'w' || tile.getType() == 'M' || tile.getType() == 'C' || tile.getType() == 'K') {
                        TileFactory tileFactory = new TileFactory();
                        Map<Character, Supplier<Enemy>> map = tileFactory.listEnemies();
                        Enemy enemy = map.get(tile.getType()).get();
                        enemy.setY(tile.getY());
                        enemy.setX(tile.getX());
                        Unit unit=moveMonster(gameTiles, (Monster) enemy, player);
                        tiles.add(unit);
                    }
                }
            }


        }

      */








    }
    public Unit moveMonster(GameTiles gameTiles,Monster m, Player player){
        double range=m.range(player);
        if(range==1.0){
            CombatController combatController=new CombatController(m,player);
            combatController.Attack();
            System.out.println(player.getHealth().getAmount());
        }
        if(range<m.getVisionRange()){

                  Tile tile=new Tile('$', player.getX(), player.getY());
                  tile.setX(player.getX()+1);
                  double left= m.range(tile);
             tile=new Tile('$', player.getX(), player.getY());
            tile.setX(player.getX()-1);
            double right= m.range(tile);
            tile=new Tile('$', player.getX(), player.getY());
            tile.setY(player.getY()-1);
            double up= m.range(tile);
            tile=new Tile('$', player.getX(), player.getY());
            tile.setY(player.getY()+1);
            double down= m.range(tile);
            double bestRange=Math.min(up,Math.min(down,Math.min(right,left)));
            if(bestRange==up){
                return move(gameTiles,m,"s");
            }
            if(bestRange==down){
                return move(gameTiles,m,"w");
            }
            if(bestRange==right){
                return move(gameTiles,m,"d");
            }
            if(bestRange==left){
               return move(gameTiles,m,"a");
            }
            else{
                return null;
            }

        }
        else{
            Random r=new Random();

            int i=r.nextInt(4);
            if(i==0){
               return move(gameTiles,m,"w");
            }
            else if(i==1){
               return move(gameTiles,m,"a");
            }
            else if(i==2){
                return move(gameTiles,m,"d");
            }
            else{
                return move(gameTiles,m,"s");
            }
        }

    }

}
