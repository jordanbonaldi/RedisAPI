package net.neferett.redisapi.Datas;

import lombok.Data;
import net.neferett.redisapi.Annotations.Redis;
import net.neferett.redisapi.DataBase.DataBase;
import net.neferett.redisapi.DataBase.DataBaseManager;
import net.neferett.redisapi.DataBase.DataBaseSerialization;
import net.neferett.redisapi.Utils.SerializationUtils;

@Data
public class SerializableObject {

    private final Object object;

    private final DataBaseManager dataBaseManager;

    private DataBase db;

    private Redis redis;

    private String id;

    private Class<?> clazz;

    private DataBaseSerialization serialization;

    public SerializableObject(Object o, String id, DataBaseManager manager) {
        this.object = o;
        this.dataBaseManager = manager;
        this.id = id;
        this.clazz = o instanceof Class ? (Class) o : o.getClass();

        this.loadRedisObject();
        this.selectDB();
        this.initSerialization();
    }



    private void loadRedisObject() {
        this.redis = this.clazz.getAnnotation(Redis.class);

        if (null == this.redis)
            System.out.println("No Redis annotation found for " + this.clazz.getSimpleName());
    }

    private void selectDB() {
        this.db = this.dataBaseManager.getDataBase(this.redis.db());
    }

    private void initSerialization() {
        this.serialization = new DataBaseSerialization(this.object, this.db.getConnector(), this.id, this.clazz);
    }

    public void serialize() {
        this.serialization.serialize();
    }

    public Object deSerialize() {
        this.serialization.setId(this.id);
        this.serialization.getManipulateDatas().setName(this.id);
        return SerializationUtils.deSerialize(this.clazz, this.serialization.deserialize());
    }
}
