package me.biieeel.objects;

import me.biieeel.utils.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface IPluginCommand {

    void execute(CommandSender sender, String[] args);

    void executePlayer(Player player, String[] args);

    void executeWithoutPermissions(Player player, String[] args);

    String getPermissionMessage();

    String getPermission();

    Command getInfo();

}