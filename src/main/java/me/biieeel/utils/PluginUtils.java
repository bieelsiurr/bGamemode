package me.biieeel.utils;

import me.biieeel.bGamemode;
import me.biieeel.objects.PluginCommand;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class PluginUtils {

    private static final bGamemode plugin = bGamemode.plugin;

    public static void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, plugin);
        }
    }

    public static void registerCommands(PluginCommand... commands) {
        for (PluginCommand command : commands) {
            Command info = command.getInfo();
            if (info == null) return;
            plugin.getCommand(info.name()).setExecutor(command);
        }
    }

}