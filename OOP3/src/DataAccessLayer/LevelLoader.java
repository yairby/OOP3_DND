package DataAccessLayer;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class LevelLoader {
    public static List<String> LoadLevel(int levelNum, String levelPath) {
        //String currentPath = System.getProperty("user.dir");
        //String levelsFile=currentPath+"\\OOP3\\src\\DataAccessLayer\\levels_dir\\level"+levelNum+".txt";
        String levelsFile=levelPath+"\\level"+levelNum+".txt";
        List<String> lines=new LinkedList<>();
        try {
            lines = Files.readAllLines(Paths.get(levelsFile), StandardCharsets.UTF_8);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return lines;
    }

}
