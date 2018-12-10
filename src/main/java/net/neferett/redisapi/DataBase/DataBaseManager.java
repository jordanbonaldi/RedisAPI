package net.neferett.redisapi.DataBase;

import lombok.Data;
import net.neferett.redisapi.Annotations.Redis;
import net.neferett.redisapi.Utils.SerializationUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Data
public class DataBaseManager {

    private final String ip;
    private final String password;
    private final int port;

    List<DataBase> dataBaseConnectorList = new ArrayList<>();

    public DataBase addDataBase(DataBase dataBase) {
        dataBase.build();
        this.dataBaseConnectorList.add(dataBase);

        return dataBase;
    }

    public DataBase getDataBase(int dbId) {
        DataBase db = this.dataBaseConnectorList.stream().filter(e -> e.getDb() == dbId).findFirst().orElse(null);

        if (null == db) {
            db = new DataBase(ip, password, port, dbId);
            this.addDataBase(db);
        }

        return db;
    }

    public void closeAll() {
        this.dataBaseConnectorList.forEach(DataBase::close);
    }

    private boolean arraysContains(String ct, String ... args) {
        AtomicBoolean alpha = new AtomicBoolean(true);

        Arrays.stream(args).forEach(e -> {
            if (!ct.contains(e))
                alpha.set(false);
        });

        return alpha.get();
    }

    public Map<String, Object> contains(Class obj, String ... args) {
        Redis redis = (Redis) obj.getAnnotation(Redis.class);
        DataBase db = this.getDataBase(redis.db());

        Map<String, String> all = db.getConnector().getRessource().hgetAll("name:*");

        return all.entrySet().stream().filter(e -> this.arraysContains(e.getValue(), args))
                .collect(Collectors.toMap(Map.Entry::getKey, e -> SerializationUtils.deSerialize(obj, e.getValue())));
    }
}
