package DataAccessLayer;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
public class JarFileLoader {

    public static List<String> readTextFromJar(String s) {
        InputStream is = null;
        BufferedReader br = null;
        String line;
        ArrayList<String> list = new ArrayList<String>();

        try {
            is = JarFileLoader.class.getResourceAsStream(s);
            br = new BufferedReader(new InputStreamReader(is));
            while (null != (line = br.readLine())) {
                list.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<String> LoadLevel(int levelNum) {
        List<String> list = JarFileLoader.readTextFromJar("/level"+levelNum+".txt");
        return list;
    }
}
