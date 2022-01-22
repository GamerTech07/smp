package de.pferd;

import de.pferd.listener.EntityDamageByEntityListener;
import de.pferd.listener.EntityExplodeListener;
import de.pferd.listener.PlayerBedEnterListener;
import de.pferd.listener.PlayerConnectionListener;
import org.bukkit.Bukkit;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Pferd extends JavaPlugin {

    private static Pferd instance;

    public String prefix = "§b§l1§3§l2§b§l3§3§l4§b§lP§3§lf§b§le§3§lr§b§ld §8› §7";

    @Override
    public void onEnable() {
        instance = this;



        registerCommands();
        registerListener();
    }

    private void registerCommands() {

    }

    private void registerListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerConnectionListener(), this);
        pluginManager.registerEvents(new EntityDamageByEntityListener(), this);
        pluginManager.registerEvents(new EntityExplodeListener(), this);
        pluginManager.registerEvents(new PlayerBedEnterListener(), this);
    }

    @Override
    public void onDisable() {

    }

    public static Pferd getInstance() {
        return instance;
    }


}
