package me.yarocks.fortuneblocks;/**
 * Created by USER on 8/25/2014.
 */


        import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    private FortuneBlocks c;

    public Commands(FortuneBlocks core) {
        this.c = core;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("fb")) {
                if (!player.hasPermission("fb.reload")) { // Reload is the only  command needed
                    player.sendMessage(ChatColor.RED + "You do not have permissions to use this command.");
                    return true;
                }
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "/fb reload - It's the only command!");
                    player.sendMessage(ChatColor.AQUA + "Made by yarocks");
                    return true;
                }
                String sub = args[0];
                if (sub.equalsIgnoreCase("reload")) {
                    c.reloadConfig();
                    player.sendMessage(ChatColor.GREEN + "[" + c.getDescription().getName() + "] Config reloaded!");
                }
            }
        }
        return true;
    }
}
