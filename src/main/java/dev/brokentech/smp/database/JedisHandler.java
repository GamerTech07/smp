package dev.brokentech.smp.database;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisHandler {

    private final JedisPool pool;

    public JedisHandler(){
        pool = new JedisPool(new JedisPoolConfig(), "locaLhost", 6379, 5000);
    }

    public JedisPool getPool() {
        return pool;
    }
}
