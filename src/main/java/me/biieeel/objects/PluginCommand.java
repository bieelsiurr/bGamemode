package me.biieeel.objects;

import me.biieeel.bGamemode;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class PluginCommand implements CommandExecutor, IPluginCommand {

    protected final bGamemode plugin = bGamemode.plugin;

    protected String name;
    protected me.biieeel.utils.Command info;

    public PluginCommand() {
        try {
            info = getClass().getDeclaredAnnotation(me.biieeel.utils.Command.class);
            name = info.name();
        } catch (Exception e) {
            plugin.getLogger().warning("Error trying to get CommandInfo from command! " + getClass().getName());
        }
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        boolean withPermissions = !getPermission().isEmpty();
        if (!withPermissions) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                executePlayer(player, args);
                if (!info.onlyForPlayers()) {
                    execute(player, args);
                }
            } else {
                execute(sender, args);
            }
            return true;
        }

        // WITH PERMISSIONS ->
        // CONSOLE:
        if (!(sender instanceof Player)) {
            if (!info.onlyForPlayers()) {
                execute(sender, args);
            } else {
                sender.sendMessage(ChatColor.RED + "Este comando solo está disponible para jugadores.");
            }
            return true;
        } else {
            // PLAYER WITH PERMISSIONS:
            Player player = (Player) sender;

            if (player.hasPermission(info.permission())) {
                if (!info.onlyForPlayers()) {
                    execute(player, args);
                } else {
                    executePlayer(player, args);
                }
                return true;
            } else {
                executeWithoutPermissions(player, args);
            }
        }
        return true;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {}

    @Override
    public void executePlayer(Player player, String[] args) {
    }

    @Override
    public void executeWithoutPermissions(Player player, String[] args) {
        player.sendMessage(getPermissionMessage());
    }

    @Override
    public String getPermissionMessage() {
        return ChatColor.RED + "No tienes permiso para ejecutar este comando.";
    }

    @Override
    public String getPermission() {
        return info.permission();
    }

    @Override
    public me.biieeel.utils.Command getInfo() {
        return info;
    }

    public String parse(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}