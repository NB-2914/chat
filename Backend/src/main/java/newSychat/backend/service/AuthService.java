package newSychat.backend.service;

import newSychat.backend.ConfigFile;
import newSychat.backend.entity.AuthEntity;
import org.bspfsystems.yamlconfiguration.file.FileConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    static String path = "userData";
    static String dot = ".";


    public void register(AuthEntity entity) {
        String id = entity.getId();
        String displayName = id;
        if (possibleID(id)) {
            if (entity.getPassword() != null) {
                FileConfiguration config = ConfigFile.getConfig();
                entity.setDisplayName(displayName);

                config.createSection(path + dot + id);
                config.set(path + dot + entity.getId() + ".displayName", displayName);
                config.set(path + dot + entity.getId() + ".password", entity.getPassword());
                config.set(path + dot + entity.getId() + ".color", "151A15");
                ConfigFile.save();

                throw new ResponseStatusException(HttpStatus.OK);

            } else{
                // Missing Data
                throw new ResponseStatusException(HttpStatus.NO_CONTENT);
            }

        } else {
            // User schon vorhanden
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public String login(AuthEntity entity) {
        String id = entity.getId();
        if (getDisplayName(id) != null) {
            if (entity.getPassword().equals(getPassword(id))) {
                throw new ResponseStatusException(HttpStatus.OK);

            } else {
                // Password nicht correckt
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }

        } else {
            // User nicht vorhanden
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

    }



    public static String getDisplayName(String id) {
        return ConfigFile.getConfig().getString(path + dot + id + ".displayName");
    }

    private String getPassword(String id) {
        return ConfigFile.getConfig().getString(path + dot + id + ".password");
    }

    public static String getColor(String id) {
        return ConfigFile.getConfig().getString(path + dot + id + ".color");
    }


    public static boolean possibleID(String id) {
        id = id.toLowerCase();

        for (String foridden : ConfigFile.getConfig().getStringList("forbidden")) {
            if (id.contains(foridden)) return false;
        }

        for (String key : ConfigFile.getConfig().getConfigurationSection("userData").getKeys(true)) {
            if (id.equalsIgnoreCase(key)) return false;

        }
        return true;
    }

    public static boolean possibleName(String name) {
        name = name.toLowerCase();
        for (String foridden : ConfigFile.getConfig().getStringList("forbidden")) {
            if (name.contains(foridden)) return false;
        }

        for (String key : ConfigFile.getConfig().getConfigurationSection("userData").getKeys(true)) {
            if (name.equalsIgnoreCase(ConfigFile.getConfig().getString("userData." + key + ".displayName"))) return false;

        }
        return true;
    }

}
