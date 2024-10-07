package newSychat.backend.service;

import newSychat.backend.ConfigFile;
import newSychat.backend.entity.ChatMessageEntity;
import org.bspfsystems.yamlconfiguration.file.FileConfiguration;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class ChatService {
    public static ArrayList<ChatMessageEntity> messages = new ArrayList<>();

    public String commandSymbol = "%";

    public ArrayList<ChatMessageEntity> messagesReturn() {
//        ArrayList temp = messages;
//        Collections.reverse(temp);
//        //return temp;
        return messages;
    }


    public void addMesssages(ChatMessageEntity entity) {
        String message = entity.getMessage();

        if (AuthService.getDisplayName(entity.getAuthor()) != null) {
            if (message.startsWith(commandSymbol)) {
                String[] args = message.split(" ");
                if (args[0].equals(commandSymbol + "nick")) {
                    if (args[1] != null) {
                        String newName = "";
                        int i = 1;
                        while (true) {
                            try {
                                if (newName == "") newName = args[i];
                                else newName = newName + " " + args[i];
                                i++;
                            } catch (ArrayIndexOutOfBoundsException e) {
                                break;
                            }
                        }

                        if (AuthService.possibleName(newName)) {
                            ConfigFile.getConfig().set("userData." + entity.getAuthor() + ".displayName", newName);
                            ConfigFile.save();
                            refresh();
                        }
                    }
                } else if (args[0].equals(commandSymbol + "color")) {
                    try {
                        Color.decode(args[1]);
                        ConfigFile.getConfig().set("userData." + entity.getAuthor() + ".color", args[1].replace("#", ""));
                        ConfigFile.save();
                        refresh();

                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {

                    }
                } else if (args[0].equals(commandSymbol + "ban")) {
                    if (hasPerms(entity.getAuthor(), "ban")) {
                        String id = null;

                        String newName = "";
                        int i = 1;
                        while (true) {
                            try {
                                if (newName == "") newName = args[i];
                                else newName = newName + " " + args[i];
                                i++;
                            } catch (ArrayIndexOutOfBoundsException e) {
                                break;
                            }
                        }

                        try {
                            String searchedUser = newName;
                            for (String user : ConfigFile.getConfig().getConfigurationSection("userData").getKeys(true)) {
                                if (user.contains(".")) continue;

                                if (ConfigFile.getConfig().getString("userData." + user + ".displayName").equalsIgnoreCase(searchedUser)) {
                                    id = user;
                                    break;
                                }

                            }

                        } catch (ArrayIndexOutOfBoundsException e) {

                        }

                        if (id != null) {
                            if (!(hasPerms(id, "ban"))) {
                                ConfigFile.getConfig().set("userData." + id, null);
                                ConfigFile.save();
                            }
                        }
                    }
                }

            } else {
                if (entity.getMessage() != "") {
                    int i = nextInt();

                    ConfigFile.getConfig().createSection("messages." + i);
                    ConfigFile.getConfig().set("messages." + i + ".author", entity.getAuthor());
                    ConfigFile.getConfig().set("messages." + i + ".message", entity.getMessage());
                    ConfigFile.save();

                    entity.setColor(AuthService.getColor(entity.getAuthor()));
                    entity.setAuthor(AuthService.getDisplayName(entity.getAuthor()));
                    messages.add(entity);
                }
            }
        }
    }

    public void clear() {
        messages.clear();
        int i = 1;
        while (true) {
            if (ConfigFile.getConfig().getString("messages." + i + ".message") == null) break;
            ConfigFile.getConfig().set("messages." + i, null);
            i++;
        }
        ConfigFile.save();
    }

    public static void refresh() {
        messages.clear();
        int i = 0;
        while (true) {
            FileConfiguration config = ConfigFile.getConfig();
            if (config.getString("messages." + i + ".message") != null) {
                ChatMessageEntity entity = new ChatMessageEntity();
                entity.setMessage(config.getString("messages." + i + ".message"));
                entity.setColor(AuthService.getColor(config.getString("messages." + i + ".author")));
                entity.setAuthor(AuthService.getDisplayName(config.getString("messages." + i + ".author")));

                if (entity.getAuthor() == null) entity.setAuthor("[DELETED USER]");

                ChatService.messages.add(entity);
                i++;
            } else break;
        }
    }


    private int nextInt() {
        int i = 0;
        while (true) {
            if (ConfigFile.getConfig().getString("messages." + i + ".message") == null ) return i;
            i++;
        }
    }

    private boolean hasPerms(String id, String perm) {
        if (ConfigFile.getConfig().getString("userData." + id + ".permission") != null) return ConfigFile.getConfig().getString("userData." + id + ".permission").contains(perm);
        else return false;
    }


}
