package dev.brokenstudio.smp.listener;

import dev.brokenstudio.smp.SMP;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandPreProcessListener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e){
        if(e.isCancelled())
            return;
        String[] message = e.getMessage().split(" ");
        if(SMP.getInstance().getNodeManager().runCommand(e.getPlayer(),message)){
            e.setCancelled(true);
        }

    }

}
