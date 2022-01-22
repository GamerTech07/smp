package de.pferd.listener;

import de.pferd.Pferd;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        Entity damager = event.getDamager();
        Entity target = event.getEntity();

        //Schutzzeit Cancel
        if(target instanceof Player) {
            if(damager instanceof Player) {
                if(Pferd.getInstance().getConfig().getInt(target.getUniqueId().toString() + ".playtime") <= 60) {
                    event.setCancelled(true);
                }
            }
        }


    }

}
