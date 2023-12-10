package me.biieeel.command;

import me.biieeel.objects.PluginCommand;
import me.biieeel.utils.Command;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

@Command(name = "gamemode", permission = "bgamemode.use")
public class Gamemode extends PluginCommand {

    private final FileConfiguration config = plugin.getConfig();

    @Override
    public void executePlayer(Player player, String[] args) {

        List<String> list = config.getStringList("messages.help-message");

        if(args.length == 0){
            sendListMessage(list, player);
            return;
        }

        if(args[0].equalsIgnoreCase("reload")){
            player.sendMessage(String.valueOf(config.get("messages.reloaded")).replaceAll("&", "§"));
            plugin.reloadConfig();
            plugin.saveConfig();
            return;
        }

        switch (args[0].toLowerCase()){
            case "survival":
            case "0":
            case "s":
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(parse(config.getString("messages.prefix")+" "+config.getString("messages.gamemode-survival")));
                break;
            case "creative":
            case "1":
            case "c":
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage(parse(config.getString("messages.prefix")+" "+config.getString("messages.gamemode-creative")));
                break;
            case "adventure":
            case "2":
            case "a":
                player.setGameMode(GameMode.ADVENTURE);
                player.sendMessage(parse(config.getString("messages.prefix")+" "+config.getString("messages.gamemode-adventure")));
                break;
            case "spectator":
            case "3":
            case "sp":
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(parse(config.getString("messages.prefix")+" "+config.getString("messages.gamemode-spectator")));
                break;
            default:
                player.sendMessage(parse(config.getString("messages.prefix")+" "+config.getString("messages.incorrect-use")));
        }
    }

    @Override
    public void executeWithoutPermissions(Player player, String[] args) {
        player.sendMessage(getPermissionMessage());
    }

    @Override
    public String getPermissionMessage() {
        return parse(config.getString("messages.no-permission"));
    }

    private void sendListMessage(List<String> list, Player player){
        StringBuilder builder = new StringBuilder();
        for ( String line : list ) {
            builder.append(line).append("\n");
        }
        player.sendMessage(builder.toString().replaceAll("&", "§"));
    }

}
