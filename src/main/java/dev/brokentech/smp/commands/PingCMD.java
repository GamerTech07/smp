package dev.brokentech.smp.commands;

import dev.brokentech.smp.SMP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PingCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(strings.length == 0) {

                player.sendMessage(SMP.PREFIX + "§a" + player.getPing() + "ms");

            }

        }

        return false;
    }
}
