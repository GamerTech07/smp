package dev.brokenstudio.smp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.brokenstudio.node.NodeManager;
import dev.brokenstudio.node.ecxeptions.ClassNoNodeException;
import dev.brokenstudio.nodes.LocationNode;
import dev.brokenstudio.smp.database.DatabaseHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import dev.brokenstudio.smp.listener.*;

public class SMP extends JavaPlugin {

    private static SMP instance;

    public static final String PREFIX = "§8•● §dSMP §8●• §7";

    private DatabaseHandler databaseHandler;
    private NodeManager nodeManager;
    private Gson gson;

    @Override
    public void onEnable() {
        instance = this;
        gson = new GsonBuilder().create();
        databaseHandler = new DatabaseHandler();
        nodeManager = new NodeManager();
        try {
            registerCommands();
        } catch (ClassNoNodeException e) {
            e.printStackTrace();
        }
        registerListener();
    }

    private void registerCommands() throws ClassNoNodeException {
        nodeManager.registerNode(new LocationNode());
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

    public static SMP getInstance() {
        return instance;
    }

    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    public NodeManager getNodeManager() {
        return nodeManager;
    }

    public Gson getGson() {
        return gson;
    }
}
