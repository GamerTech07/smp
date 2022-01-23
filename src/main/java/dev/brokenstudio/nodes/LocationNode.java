package dev.brokenstudio.nodes;

import dev.brokenstudio.node.annotations.Alias;
import dev.brokenstudio.node.annotations.Command;
import dev.brokenstudio.node.annotations.Node;
import dev.brokenstudio.smp.SMP;
import dev.brokenstudio.smp.locations.JsonLocation;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import redis.clients.jedis.Jedis;

@Node(name = "locaions")
@Alias(alias =  {"loc"})
public class LocationNode {

    @Command
    public void run(CommandSender sender, String[] args){

        Player player = (Player) sender;

        if(args.length == 2){
            String cmd = args[0];

            if(cmd.equals("get")){
                try(Jedis jedis = SMP.getInstance().getDatabaseHandler().getJedisHandler().getPool().getResource()){
                    String result = jedis.get(player.getUniqueId() + "_" + args[1]);
                    if(result == null){
                        sender.sendMessage(SMP.PREFIX + "Diese Location hast du nicht gesetzt.");
                        return;
                    }
                    JsonLocation location = SMP.getInstance().getGson().fromJson(result, JsonLocation.class);
                    Location loc = location.toLocation();
                    player.sendMessage(SMP.PREFIX + args[1] + " %x% %y% %z%".replace("%x%", loc.getBlockX() + "")
                            .replace("%y%", loc.getBlockY() + "")
                            .replace("%z%", loc.getBlockZ() + ""));
                }
            }else if(cmd.equals("set")){
                try(Jedis jedis = SMP.getInstance().getDatabaseHandler().getJedisHandler().getPool().getResource()){
                    JsonLocation location = new JsonLocation(player.getLocation());
                    jedis.set(player.getUniqueId() + "_" + args[1], SMP.getInstance().getGson().toJson(location));
                    player.sendMessage(SMP.PREFIX + "Du hast die Location gesetzt.");
                }
            }else{
                sender.sendMessage(SMP.PREFIX + "/location <get,set> <name>");
            }

        }else{
            sender.sendMessage(SMP.PREFIX + "/location <get,set> <name>");
        }

    }

}
