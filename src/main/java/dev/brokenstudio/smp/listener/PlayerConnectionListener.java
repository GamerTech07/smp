package dev.brokenstudio.smp.listener;

import dev.brokenstudio.smp.SMP;
import io.technetwork.base.timer.Timer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        //Join Message
        event.setJoinMessage(SMP.PREFIX +  "§n" + event.getPlayer().getName() + "§7 hat die Welt betreten.");

        //Playtime
        new Timer(timer -> {

            if(event.getPlayer().isOnline()) {

                int playtime = SMP.getInstance().getConfig().getInt(event.getPlayer().getUniqueId().toString() + ".playtime");

                SMP.getInstance().getConfig().set(event.getPlayer().getUniqueId().toString() + ".playtime", playtime + 1);
                SMP.getInstance().saveConfig();

                if(SMP.getInstance().getConfig().getInt(event.getPlayer().getUniqueId().toString() + ".playtime") == 60) {

                    event.getPlayer().sendTitle("§bSchutzzeit", "Schutzzeit ist nun deaktiviert");

                }

            } else {
                timer.cancelTimer();
            }

        }, Timer.TimerType.EVERY_MINUTE);

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        event.setQuitMessage(SMP.PREFIX  + "§n" + event.getPlayer().getName() + "§7 hat die Welt verlassen.");

    }



}
