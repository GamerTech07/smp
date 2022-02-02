package dev.brokentech.smp.commands;

import dev.brokentech.smp.SMP;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LocationCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if(strings.length == 3) {

                if(strings[0].equalsIgnoreCase("player")) {

                    if(Bukkit.getOnlinePlayers().contains(strings[1])) {

                        Player target = Bukkit.getPlayer(strings[1]);

                        target.sendMessage(SMP.PREFIX + "§a" + player.getName() + " §7hat sich deine Position anzeigen lassen.");

                        player.sendMessage(
                                SMP.PREFIX + "a" + target.getName() + " §7ist bei:\n" +
                                SMP.PREFIX + "§cX§8: §7" + (int) target.getLocation().getX() + "\n" +
                                SMP.PREFIX + "§2Y§8: §7" + (int) target.getLocation().getY() + "\n" +
                                SMP.PREFIX + "§9Z§8: §7" + (int) target.getLocation().getZ()
                        );


                    }

                }

            }

        }

        return false;
    }
}
