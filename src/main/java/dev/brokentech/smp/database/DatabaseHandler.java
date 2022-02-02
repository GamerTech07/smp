package dev.brokentech.smp.database;

public class DatabaseHandler {

    private final JedisHandler jedisHandler;

    public DatabaseHandler(){
        this.jedisHandler = new JedisHandler();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public JedisHandler getJedisHandler() {
        return jedisHandler;
    }
}

