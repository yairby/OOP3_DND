package UI;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class UI {
    public static void main(String[] args) {
       // GameController gameController=new GameController();
      //  gameController.play();
        String currentPath = System.getProperty("user.dir");
        String levelsFile=currentPath+"\\OOP3\\src\\DataAccessLayer\\level1.txt";
        try {
            List<String> lines = Files.readAllLines(Paths.get(levelsFile), StandardCharsets.UTF_8);
            lines.stream().forEach(System.out::println);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }
}
