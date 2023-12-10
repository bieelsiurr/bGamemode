package me.biieeel;

import me.biieeel.command.Gamemode;
import me.biieeel.utils.PluginUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class bGamemode extends JavaPlugin {

    public static bGamemode plugin;
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        plugin = this;
        plugin.getDataFolder().mkdirs();

        registerConfig();
        registerCommands();

        getLogger().info("bGamemode » Enabling plugin.");
        getLogger().info("bGamemode » Enabling plugin..");
        getLogger().info("bGamemode » Enabling plugin...");
        getLogger().info("bGamemode » Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("bGamemode » Disabling plugin.");
        getLogger().info("bGamemode » Disabling plugin..");
        getLogger().info("bGamemode » Disabling plugin...");
        getLogger().info("bGamemode » Disabled!");
    }

    private void registerCommands() {
        PluginUtils.registerCommands(
            new Gamemode()
        );
    }

    private void registerConfig() {
        saveDefaultConfig();
        config.addDefault("messages.prefix", "&b&lGamemode &8»");
        config.addDefault("messages.no-permission", "&cYou don't have permission to execute this command!");

        ArrayList<String> helpMessage = new ArrayList<>();
        helpMessage.add("&b");
        helpMessage.add("&3&lbGamemode &8- &fHelp message");
        helpMessage.add("&1");
        helpMessage.add("&e/gamemode survival &8| &fSet your gamemode to Survival");
        helpMessage.add("&e/gamemode creative &8| &fSet your gamemode to Creative");
        helpMessage.add("&e/gamemode adventure &8| &fSet your gamemode to Adventure");
        helpMessage.add("&e/gamemode spectator &8| &fSet your gamemode to Spectator");
        helpMessage.add("&a");
        helpMessage.add("&bYou can also use &e&lgm&b for the first argument and a number or the abbreviation for each gamemode.");
        helpMessage.add("&3");

        config.addDefault("messages.help-message", helpMessage);
        config.addDefault("messages.gamemode-survival", "&aYour gamemode has updated to Survival");
        config.addDefault("messages.gamemode-creative", "&aYour gamemode has updated to Creative");
        config.addDefault("messages.gamemode-adventure", "&aYour gamemode has updated to Adventure");
        config.addDefault("messages.gamemode-spectator", "&aYour gamemode has updated to Spectator");
        config.addDefault("messages.incorrect-use", "&cUse /gamemode <Gamemode>");
        config.addDefault("messages.reload", "&aPlugin has been reloaded!");
        config.options().copyDefaults(true);
        saveConfig();
    }
}