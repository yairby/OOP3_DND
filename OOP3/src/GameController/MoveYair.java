package GameController;

import BusinessLayer.Board.GameTiles;
import BusinessLayer.Board.Tile;
import BusinessLayer.Board.Unit;
import BusinessLayer.CombatSystem.Combat;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Enemies.Monster;
import BusinessLayer.Units.Enemies.Trap;
import BusinessLayer.Units.Players.Player;

import java.util.List;
import java.util.Random;

public class MoveYair {
    public Unit move(GameTiles gameTiles, Unit unit, String move){

        if(move.equals("w") || move.equals("W")){
            if(!(unit.getY()-1<0)&&(gameTiles.getBoard()[unit.getY() - 1][unit.getX()].getTileChar()=='.')) {
                gameTiles.getBoard()[unit.getY()][unit.getX()] = new Tile('.', unit.getX(), unit.getY());
                gameTiles.getBoard()[unit.getY() - 1][unit.getX()] = unit;
                unit.setY(unit.getY() - 1);
            }
            else if(gameTiles.getBoard()[unit.getY() - 1][unit.getX()] instanceof Monster){
                if(unit instanceof Player){
                    Monster m=(Monster) gameTiles.getBoard()[unit.getY() - 1][unit.getX()];
                    Combat combatController=new Combat(unit,m);
                    combatController.Attack();
                    if(m.getHealth().getAmount()<=0){
                        gameTiles.getEnemies().remove(m);
                        gameTiles.getBoard()[unit.getY() - 1][unit.getX()]=unit;
                        gameTiles.getBoard()[unit.getY()][unit.getX()]=new Tile('.', unit.getX(), unit.getY());
                        System.out.println(m.getName()+" died."+  unit.getName()+"gained "+m.getExperience()+" experience");
                        ((Player) unit).setExperience(m.getExperience());
                        unit.setY(unit.getY()-1);
                    }
                }
            }
return unit;

        }
        else if(move.equals("s")){
            if(!(unit.getY()+1>gameTiles.getBoard().length)&&(gameTiles.getBoard()[unit.getY() + 1][unit.getX()].getTileChar()=='.')) {

                gameTiles.getBoard()[unit.getY()][unit.getX()] = new Tile('.', unit.getX(), unit.getY());
                gameTiles.getBoard()[unit.getY() + 1][unit.getX()] = unit;
                unit.setY(unit.getY() + 1);
                return unit;
            }
            else if(gameTiles.getBoard()[unit.getY() + 1][unit.getX()] instanceof Monster){
                if(unit instanceof Player){
                    Monster m=(Monster) gameTiles.getBoard()[unit.getY() + 1][unit.getX()];
                    Combat combatController=new Combat(unit,m);
                    combatController.Attack();
                    if(m.getHealth().getAmount()<=0){
                        gameTiles.getEnemies().remove(m);
                        gameTiles.getBoard()[unit.getY() + 1][unit.getX()]=unit;
                        gameTiles.getBoard()[unit.getY()][unit.getX()]=new Tile('.', unit.getX(), unit.getY());
                        System.out.println(m.getName()+" died."+  unit.getName()+"gained "+m.getExperience()+" experience");
                        ((Player) unit).setExperience(m.getExperience());
                        unit.setY(unit.getY()+1);
                    }
                }
            }
            return unit;
        }
        else if(move.equals("a")){
            if(!(unit.getX()-1<0)&&(gameTiles.getBoard()[unit.getY() ][unit.getX()-1].getTileChar()=='.')) {

                    gameTiles.getBoard()[unit.getY()][unit.getX()] = new Tile('.', unit.getX(), unit.getY());
                    gameTiles.getBoard()[unit.getY()][unit.getX() - 1] = unit;
                    unit.setX(unit.getX() - 1);
                    return unit;
                }
            else if(gameTiles.getBoard()[unit.getY() ][unit.getX()-1] instanceof Monster){
                if(unit instanceof Player){
                    Monster m=(Monster) gameTiles.getBoard()[unit.getY() ][unit.getX()-1];
                    Combat combatController=new Combat(unit,m);
                    combatController.Attack();
                    if(m.getHealth().getAmount()<=0){
                        gameTiles.getEnemies().remove(m);
                        gameTiles.getBoard()[unit.getY() ][unit.getX()-1]=unit;
                        gameTiles.getBoard()[unit.getY()][unit.getX()]=new Tile('.', unit.getX(), unit.getY());
                        System.out.println(m.getName()+" died."+  unit.getName()+"gained "+m.getExperience()+" experience");
                        ((Player) unit).setExperience(m.getExperience());
                        unit.setX(unit.getX()-1);
                    }
                }
            }
            return unit;
        }
        else if(move.equals("d")){
            if(!(unit.getX()+1>gameTiles.getBoard()[0].length)&&(gameTiles.getBoard()[unit.getY() ][unit.getX()+1].getTileChar()=='.')) {

                gameTiles.getBoard()[unit.getY()][unit.getX()] = new Tile('.', unit.getX(), unit.getY());
                gameTiles.getBoard()[unit.getY()][unit.getX() + 1] = unit;
                unit.setX(unit.getX() + 1);
                return unit;
            }
            else if(gameTiles.getBoard()[unit.getY() ][unit.getX()+1] instanceof Monster){
                if(unit instanceof Player){
                    Monster m=(Monster) gameTiles.getBoard()[unit.getY()][unit.getX()+1];
                    Combat combatController=new Combat(unit,m);
                    combatController.Attack();
                    if(m.getHealth().getAmount()<=0){
                        gameTiles.getEnemies().remove(m);
                        gameTiles.getBoard()[unit.getY() ][unit.getX()+1]=unit;
                        gameTiles.getBoard()[unit.getY()][unit.getX()]=new Tile('.', unit.getX(), unit.getY());
                        System.out.println(m.getName()+" died."+  unit.getName()+"gained "+m.getExperience()+" experience");
                        ((Player) unit).setExperience(m.getExperience());
                        unit.setX(unit.getX()+1);
                    }
                }
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
        List<Enemy> enemies=gameTiles.getEnemies();
        for(int i=0;i<enemies.size();i++){
           if(enemies.get(i) instanceof Monster){
              moveMonster(gameTiles,(Monster) enemies.get(i),player);
           }
           else if(enemies.get(i) instanceof Trap){
                Trap(gameTiles,player, (Trap) enemies.get(i));
           }
        }
    }
    public void Trap(GameTiles gameTiles,Player player,Trap t){

        if(t.range(player)<2){
            Combat combatController=new Combat(t,player);
            combatController.Attack();
            System.out.println(t.toString());
            System.out.println(player.getHealth().getAmount());
            gameTiles.getEnemies().remove(t);
            gameTiles.getBoard()[t.getY()][t.getY()]=new Tile('.',t.getX(),t.getY());
        }
    }
    public Unit moveMonster(GameTiles gameTiles,Monster m, Player player){
        double range=m.range(player);
        if(range==1.0){
            Combat combatController=new Combat(m,player);
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
