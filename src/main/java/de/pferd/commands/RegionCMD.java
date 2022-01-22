package de.pferd.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RegionCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;

            // /region - Show if region is claimed and if its claimed from who and how strong
            // /region claim - Claim region
            // /region upgrade - Upgrade normal claim to hard claim
            // /region downgrade - Downgraded region to normal claim and gives half of price back
            // /region clan - shows all clan members
            // /region clan add [player] - adds a player to your region clan
            // /region clan remove [player] - removes a player from your region clan
            // /region alliance - show all alliances
            // /region alliance add [regionclan]
            // /region alliance remove [regionclan]
            // /region clan roles
        }

        return false;
    }
}
