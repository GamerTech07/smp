package dev.brokentech.node;

import dev.brokentech.node.annotations.Alias;
import dev.brokentech.node.annotations.Command;
import dev.brokentech.node.annotations.Node;
import dev.brokentech.node.ecxeptions.ClassNoNodeException;
import org.bukkit.command.CommandSender;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class NodeManager {

    private final HashMap<String, Object> nodes;
    private final HashMap<String, String> alias;

    public NodeManager() {
        this.nodes = new HashMap<>();
        this.alias = new HashMap<>();
    }

    public void registerNode(Object obj) throws ClassNoNodeException{
        Class clazz = obj.getClass();

        if(!clazz.isAnnotationPresent(Node.class)){
            throw new ClassNoNodeException();
        }

        String name = ((Node) clazz.getAnnotation(Node.class)).name();

        nodes.put("/" + name,obj);

        if(clazz.isAnnotationPresent(Alias.class)){
            for (String s : ((Alias) clazz.getAnnotation(Alias.class)).alias()) {
                alias.put("/" + s,"/" + name);
            }
        }

    }

    public boolean runCommand(CommandSender sender, String[] message){
        String name = message[0].toLowerCase();
        if(!nodes.containsKey(name)){
            if(!alias.containsKey(name)){
                return false;
            }
            name = alias.get(name);
        }

        Object obj = nodes.get(name);
        Class clazz = obj.getClass();

        String[] args = new String[message.length -1];

        System.arraycopy(message, 1, args, 0, args.length);

        for (Method method : clazz.getMethods()) {
            if(method.isAnnotationPresent(Command.class)){
                try {
                    method.invoke(obj,sender,args);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

}
