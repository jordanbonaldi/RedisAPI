package net.neferett.redisapi.Datas;

import lombok.Data;
import lombok.NonNull;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

@Data
public class ManipulateDatas {

    private final Jedis jedis;

    private final String clazzName;

    private final boolean folder;

    @NonNull
    private String name;

    private String checkName() {
        return this.folder ? this.clazzName + ":" + this.name : this.clazzName;
    }

    private Map<String, String> cache = new HashMap<>();

    public void refreshCache() {
        this.cache = this.jedis.hgetAll(this.checkName());
    }

    public String get(final String key) {
        return this.cache.get(key);
    }

    public void set(final String key, final String value) {
        this.jedis.hset(this.checkName(), key, value);
    }

}
