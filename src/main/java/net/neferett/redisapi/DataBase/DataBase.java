package net.neferett.redisapi.DataBase;

import lombok.Data;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Data
public class DataBase {

    private final String ip;
    private final String password;
    private final int port;
    private final int db;

    private DataBaseConnector connector;

    // IMPL millis reconnection

    private void createConnector() {
        this.connector = new DataBaseConnector(ip, password, port, db);
    }

    public Jedis getResource() {
        return this.connector.getRessource();
    }

    public void build() {
        this.createConnector();
        this.connector.enable();
    }

    public void close() {
        this.connector.disable();
    }

}
