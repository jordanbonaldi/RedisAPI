package net.neferett.redisapi.DataBase;

import lombok.Data;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Data
public class DataBaseConnector {

    private final String ip;
    private final String password;
    private final int port;
    private final int dbId;

    private JedisPool cachePool;

    private JedisPoolConfig config;

    private void createConfig() {
        this.config = new JedisPoolConfig();

        this.config.setMaxIdle(300);
        this.config.setMaxTotal(500);
        this.config.setMinIdle(200);
        this.config.setMaxWaitMillis(100);
        this.config.setTestOnBorrow(true);
    }

    public Jedis getRessource() {
        //IMPL Reconnection to clean IDLE
        return this.cachePool.getResource();
    }

    public void enable() {
        this.createConfig();
        this.cachePool = new JedisPool(this.config, this.ip, this.port, 5000, this.password, this.dbId);

        this.cachePool.getResource().connect();
    }


    public void disable() {
        this.cachePool.destroy();
    }
}
