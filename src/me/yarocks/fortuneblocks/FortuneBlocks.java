package me.yarocks.fortuneblocks;/**
 * Created by USER on 8/25/2014.
 */


import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class FortuneBlocks extends JavaPlugin {

    private PluginManager pm = Bukkit.getPluginManager();

    public  WorldGuardPlugin getWorldGuard() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

        // WorldGuard may not be loaded
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            return null; // Maybe you want throw an exception instead
        }

        return (WorldGuardPlugin) plugin;
    }

    public void onEnable() {
        getConfig().addDefault("fortuned", "DIAMOND_BLOCK");
        getConfig().addDefault("fortuned", "GOLD_BLOCK");
        getConfig().addDefault("fortuned", "STONE");
        getConfig().addDefault("fortuned", "COAL_BLOCK");
        getConfig().addDefault("fortuned", "IRON_BLOCK");
        getConfig().options().copyDefaults(true);
        saveConfig();

        for (String cmdname : getDescription().getCommands().keySet()) {
            getCommand(cmdname).setExecutor(new Commands(this));
        }
        pm.registerEvents(new BlockBreakHandler(this), this);
        Output.print(ChatColor.GREEN + "[" + getDescription().getName() + "] V" + getDescription().getVersion() + " enabled");
    }

    public void onDisable() {
        Output.print(ChatColor.RED + "[" + getDescription().getName() + "] V" + getDescription().getVersion() + " diasbled");
    }
}
