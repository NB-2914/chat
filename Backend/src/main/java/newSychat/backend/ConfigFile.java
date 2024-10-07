package newSychat.backend;

import newSychat.backend.entity.ChatMessageEntity;
import newSychat.backend.service.AuthService;
import newSychat.backend.service.ChatService;
import org.bspfsystems.yamlconfiguration.file.FileConfiguration;
import org.bspfsystems.yamlconfiguration.file.YamlConfiguration;

import java.io.*;
import java.nio.file.Files;

public class ConfigFile {

    private static String fileName = "data.yml";
    private static FileConfiguration customFile;
    private static File dataFolder = new File(new File("").getAbsolutePath() + "/data");

    static File targetFile = new File(dataFolder.getPath() + "/" + fileName);


    public static void setup() {
        File configFile = new File(dataFolder, fileName);
        if (dataFolder.exists()) {
            if (!configFile.exists()) {
                createNewFile(configFile);
            }
        } else {
            createNewPath();
            createNewFile(configFile);
        }

        customFile = YamlConfiguration.loadConfiguration(configFile);

        ChatService.refresh();
    }


    public static FileConfiguration getConfig() {
        return customFile;
    }

    public static void save() {
        try {
            customFile.save(new File(dataFolder, fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reload() {
        customFile = YamlConfiguration.loadConfiguration(new File(dataFolder, fileName));
    }
    public static void createNewPath() {
        try {
            Files.createDirectories(dataFolder.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void createNewFile(File configFile) {
        try {
            // Erstellt die Datei
            configFile.createNewFile();

            // Eingabe- und Ausgabestreams initialisieren
            InputStream inputStream = ConfigFile.class.getClassLoader().getResourceAsStream(fileName);
            FileOutputStream outputStream = new FileOutputStream(targetFile);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

            // Puffer für Datenkopie initialisieren
            byte[] buffer = new byte[1024];
            int bytesRead;


            // Daten von der Quelldatei in die Zieldatei kopieren
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, bytesRead);
            }

            // Streams schließen
            bufferedInputStream.close();
            bufferedOutputStream.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
