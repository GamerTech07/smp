package de.pferd.listener;

import de.pferd.Pferd;
import io.technetwork.base.techdata.TechDatabase;
import io.technetwork.base.timer.TechRunnable;
import io.technetwork.base.timer.Timer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        //Join Message
        event.setJoinMessage(Pferd.getInstance().prefix + "§n" + event.getPlayer().getName() + "§7 hat die Welt betreten.");

        //Playtime
        new Timer(new TechRunnable() {

            @Override
            public void run(Timer timer) {

                if(event.getPlayer().isOnline()) {

                    int playtime = Pferd.getInstance().getConfig().getInt(event.getPlayer().getUniqueId().toString() + ".playtime");

                    Pferd.getInstance().getConfig().set(event.getPlayer().getUniqueId().toString() + ".playtime", playtime + 1);
                    Pferd.getInstance().saveConfig();

                    if(Pferd.getInstance().getConfig().getInt(event.getPlayer().getUniqueId().toString() + ".playtime") == 60) {

                        event.getPlayer().sendTitle("§bSchutzzeit", "Schutzzeit ist nun deaktiviert");

                    }

                } else {
                    timer.cancelTimer();
                }

            }

        }, Timer.TimerType.EVERY_MINUTE);

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        event.setQuitMessage(Pferd.getInstance().prefix + "§n" + event.getPlayer().getName() + "§7 hat die Welt verlassen.");

    }



}
