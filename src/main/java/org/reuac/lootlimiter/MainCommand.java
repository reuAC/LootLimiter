package org.reuac.lootlimiter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public class MainCommand implements CommandExecutor {
    public static boolean hasError = false;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        LootLimiter.main.reloadConfig();
        LootLimiter.main.loadConfig();
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (hasError){
                player.sendMessage(LootLimiter.PluginName + "The reload is complete, but an error was encountered, go to the console and check.");
                hasError = false;
                return true;
            }
            player.sendMessage(LootLimiter.PluginName + "Reload Completion");
            return true;
        }else {
            if (hasError){
                getLogger().severe("The reload is complete, but an error was encountered carrying it out.");
                hasError = false;
                return true;
            }
            getLogger().info("Reload Completion");
            return true;
        }

    }
}
