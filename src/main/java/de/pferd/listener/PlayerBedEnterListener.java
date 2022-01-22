package de.pferd.listener;

import de.pferd.Pferd;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerBedEnterListener implements Listener {

    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {

        new BukkitRunnable() {

            @Override
            public void run() {
                if(event.getPlayer().isSleeping()) {
                    event.getPlayer().getWorld().setTime(1000);

                    Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(Pferd.getInstance().prefix + "Guten Morgen!"));
                }
            }

        }.runTaskLater(Pferd.getInstance(), 40);

    }

}
